// ShoppingCart
export default {
    el: '#shopping-Cart',
    data() {
        return {
            cartItems: [],
            getCartUrl: '',
            addCartUrl: '',
            orderUrl: '',
            orderSummary: {
                totalItems: 0,
                totalPrice: 0,
            },
        };
    },
    template: `
      <div>
        <div v-if="cartItems.length > 0">
          <span>Twój koszyk ({{ orderSummary.totalItems }} produktów)</span>
          <ul>
            <li v-for="product in cartItems" :key="product.id">
              <span> {{ product.nazwa }} - {{ formatPrice(product.cena) }} zł </span>
              <span> Ilość: {{ product.ilosc }} - {{ formatPrice(product.cena * product.ilosc) }} zł </span>
              <button @click="increaseQuantity(product)">+</button>
              <button @click="decreaseQuantity(product)">-</button>
              <button @click="deleteQuantity(product)">X</button>
            </li>
          </ul>
          <div>Podsumowanie: {{ formatPrice(orderSummary.totalPrice) }} zł</div>
          <button class="button-kup" @click="placeOrder">Złóż zamówienie</button>
        </div>
        <div v-else>
          <span>Koszyk jest pusty</span>
        </div>
      </div>
  `,
    mounted() {
        this.getCartUrl = document.getElementById('shoppingCart').getAttribute('getCartUrl');
        this.addCartUrl = document.getElementById('shoppingCart').getAttribute('addCartUrl');
        this.orderUrl = this.$el.getAttribute('orderUrl');
        // Pobierz zawartość koszyka po załadowaniu komponentu
        this.fetchCartItems();
    },
    methods: {
        fetchCartItems() {
            fetch(this.getCartUrl)
                .then(response => response.json())
                .then(data => {
                    this.cartItems = data;
                    console.log(data);
                    this.calculateOrderSummary();
                })
        },
        addCartItems(produkt) {
            return fetch(this.addCartUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(produkt),
            })
                .then(response => {
                    if (response.ok) {
                        // response.text().then(t => alert(t));
                    } else {
                        console.error('Błąd dodawania do koszyka:', response.statusText);
                    }
                })
                .catch(error => console.error('Błąd dodawania do koszyka:', error));
        },
        calculateOrderSummary() {
            this.orderSummary.totalItems = this.cartItems.reduce((total, product) => total + product.ilosc, 0);
            this.orderSummary.totalPrice = this.cartItems.reduce((total, product) => total + (product.cena * product.ilosc), 0);
        },
        deleteQuantity(product) {
            product.ilosc = 0;
            this.addCartItems(product).then(value => this.fetchCartItems());
        },
        increaseQuantity(product) {
            if (product.ilosc < 10) {
                product.ilosc++;
                this.addCartItems(product);
                this.calculateOrderSummary();
            }
        },
        decreaseQuantity(product) {
            if (product.ilosc > 1) {
                product.ilosc--;
                this.addCartItems(product);
                this.calculateOrderSummary();
            }
        },
        formatPrice(price) {
            return price.toFixed(2); // Sformatuj cenę do dwóch miejsc po przecinku
        },
        placeOrder(price) {
            if (this.orderUrl != '')
                window.location.href = this.orderUrl;
        }
    },
};
