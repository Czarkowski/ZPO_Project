import Vue from 'vue';
import App from './App.vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import Home from './views/Home.vue';
import VueRouter from 'vue-router';

Vue.config.productionTip = false;

Vue.use(VueRouter);
Vue.use(VueAxios, axios);

const routes = [
    { path: '/', component: Home }
];

const router = new VueRouter({
    routes
});

new Vue({
    render: h => h(App),
    router
}).$mount('#app');