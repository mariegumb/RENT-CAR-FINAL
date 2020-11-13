import Vue from 'vue'
import App from './App.vue'
import 'tailwindcss/dist/tailwind.min.css'
import '@fortawesome/fontawesome-free/css/all.min.css'
import router from './routeur'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
