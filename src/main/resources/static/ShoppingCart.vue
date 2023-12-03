<!-- ShoppingCart.vue -->
<template>
  <div>
    <span v-if="cartItems.length > 0">Twój koszyk ({{ cartItems.length }})</span>
    <span v-else>Koszyk jest pusty</span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cartItems: [],
    };
  },
  mounted() {
    // Pobierz zawartość koszyka po załadowaniu komponentu
    this.fetchCartItems();
  },
  methods: {
    fetchCartItems() {
      // Wywołaj API lub inne źródło danych
      // Przykład użycia axios do pobrania danych
      // Załóżmy, że masz endpoint API do pobierania zawartości koszyka

    },
  },
};
</script>
