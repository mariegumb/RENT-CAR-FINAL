import Vue from 'vue' // Include Vue
import Router from 'vue-router' // Include Vue Router libary
import CarForm from './views/CarForm.vue' // <--- We'll look at these in a moment
import Cars from './views/Cars.vue'

Vue.use(Router)


export default new Router({
    routes: [
        {
            path: '/',
            name: 'cars',
            component: Cars
        },
        {
            path: '/car-form',
            name: 'carForm',
            component: CarForm
        }
    ]
})