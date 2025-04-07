import './assets/global.css'
import router from '@/router/router'

import { createApp } from 'vue'
import App from './App.vue'
import VueTailwindDatepicker from 'vue-tailwind-datepicker'

createApp(App).use(router).use(VueTailwindDatepicker).mount('#app')
