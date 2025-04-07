import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import TourDetailView from '@/views/TourDetailView.vue'
import ContactView from '@/views/ContactView.vue'
import AdminView from '@/views/AdminView.vue'
import LoginAdmin from '@/views/LoginAdmin.vue'
import ManageTour from '@/components/ManageTour.vue'
import ManageBooking from '@/components/ManageBooking.vue'

const routes = [
  {
    path: '/',
    component: HomeView
  },
  {
    path: '/:tourid',
    component: TourDetailView
  },
  {
    path: '/contact',
    component: ContactView
  },
  {
    path: '/admin',
    component: AdminView,
    beforeEnter: (to, from, next) => {
      if (!localStorage.getItem('isAdmin')) {
        next('/loginAdmin')
      } else {
        next()
      }
    },
    children: [
      {
        path: 'manageTours',
        component: ManageTour,
        name: 'ManageTours'
      },
      {
        path: 'manageBookings',
        component: ManageBooking,
        name: 'ManageBookings'
      }
    ],
    meta: {
      hideNavbar: true
    }
  },
  {
    path: '/loginAdmin',
    component: LoginAdmin,
    meta: {
      hideNavbar: true
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
