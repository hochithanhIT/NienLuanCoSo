<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios'
import moment from 'moment';

const bookings = ref([])

onBeforeMount(async () => {
  try {
    const response = await axios.get('http://localhost:8090/dulich/api/booking')

    bookings.value = response.data
  } catch (error) {
    console.error(`Error fetching data: ${error}`)
  }
})
</script>

<template>
<main class="ml-64 h-screen">
    <div class="tool-bar p-3 bg-gray-200 text-xl">
      <div class="title ml-4 font-bold">Quản lý Booking</div>
    </div>

    <div class="relative overflow-x-auto shadow-md mt-10">
      <table class="w-full text-sm text-left text-gray-400">
        <thead class="text-xs text-gray-900 uppercase bg-green-300">
          <tr>
            <th scope="col" class="px-4 py-3">Thời gian Booking</th>
            <th scope="col" class="px-4 py-3">Số lượng</th>
            <th scope="col" class="px-4 py-3">Tổng tiền</th>
            <th scope="col" class="px-4 py-3">Người đặt</th>
            <th scope="col" class="px-4 py-3">Mã Tour</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="booking in bookings" :key="booking.booking_id" class="bg-gray-100 border-b">
            <td class="px-4 py-4">{{ moment(booking.booking_time).format('DD/MM/YYYY hh:mm') }}</td>
            <td class="px-4 pt-4">
              {{ booking.booking_quantity }}
            </td>
            <td class="px-4 py-4">{{ booking.booking_totalMoney }}</td>
            <td class="px-4 py-4">{{ booking.us_name }}</td>
            <td class="px-4 py-4 ">{{ booking.tour_id }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>
</template>

<style scoped> 
.description {
  display: -webkit-box;
  -webkit-line-clamp: 5; /* Số dòng tối đa bạn muốn hiển thị */
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>