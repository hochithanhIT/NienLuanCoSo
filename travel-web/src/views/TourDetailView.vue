<script setup>
import axios from 'axios'
import { onBeforeMount, reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
import moment from 'moment/moment'

const route = useRoute()
const tour = ref(null)

onBeforeMount(async () => {
  try {
    const response = await axios.get(`http://localhost:8090/dulich/api/tour/${route.params.tourid}`)
    if (response) {
      tour.value = response.data
    }
  } catch (error) {
    console.error(`Error fetching data: ${error}`)
  }
})

const bookingForm = reactive({
  booking_quantity: ''
})

const onBooking = async () => {
  try {
    const username = localStorage.getItem('username')
    if (!username) {
      return
    }
    const bookingData = {
      booking_time: moment(moment().format(), moment.ISO_8601), // Lấy ngày giờ hiện tại
      booking_quantity: bookingForm.booking_quantity, // Lấy số lượng người từ form
      booking_totalMoney: bookingForm.booking_quantity * tour.value.tour_unitPrice,
      us_name: username, // Sử dụng tên người dùng từ localStorage
      tour_id: tour.value.tour_id
    }

    // Gửi yêu cầu POST để tạo booking mới
    confirm('Bạn có chắc chắn muốn đặt tour này không?')
    await axios.post('http://localhost:8090/dulich/api/booking', bookingData)

    // Xử lý kết quả trả về (nếu cần)
    alert('Đặt tour thành công')

    // Nếu thành công, bạn có thể thực hiện các hành động khác như hiển thị thông báo, điều hướng người dùng, v.v.
  } catch (error) {
    console.error('Error booking tour:', error)
    alert('Bạn cần đăng nhập trước!')
  }
}
</script>

<template>
  <div class="tour-detail-container container mx-auto">
    <div class="tour-image w-full h-[30rem]">
      <img src="@/images/slider-image-1.jpg" class="h-full w-full objective-cover" />
    </div>

    <div class="tour-info-booking flex justify-between pb-10">
      <div class="tour-info-container w-[70%]">
        <div class="tour-info mt-10 text-xl font-bold mb-2">
          <div class="tour-title">{{ tour.tour_title }}</div>
        </div>

        <div class="tour-destination flex space-x-1 text-gray-500 mb-4">
          <span class="material-symbols-outlined"> location_on </span>
          <span>{{ tour.destination.des_name }}</span>
        </div>

        <div class="tour-detail flex space-x-10">
          <div class="tour-duration flex justify-between items-center space-x-4">
            <span class="material-symbols-outlined text-red-400"> timer </span>
            <div class="title-time flex flex-col justify-center">
              <span class="title mb-2">Thời gian</span>
              <span class="time text-gray-400">{{ tour.tour_day }}</span>
            </div>
          </div>

          <div class="tour-type flex justify-between items-center space-x-4">
            <span class="material-symbols-outlined text-red-400"> border_color </span>
            <div class="title-type flex flex-col justify-center">
              <span class="title mb-2">Loại</span>
              <span class="type text-gray-400">{{ tour.tour_type.tt_typename }}</span>
            </div>
          </div>

          <div class="tour-slot flex justify-between items-center space-x-4">
            <span class="material-symbols-outlined text-red-400"> group </span>
            <div class="title-slot flex flex-col justify-center">
              <span class="title mb-2">Số lượng</span>
              <span class="type text-gray-400">{{ tour.tour_slot }}</span>
            </div>
          </div>

          <div class="tour-price flex justify-between items-center space-x-4">
            <span class="material-symbols-outlined text-red-400"> payments </span>
            <div class="title-price flex flex-col justify-center">
              <span class="title mb-2">Giá</span>
              <span class="type text-gray-400">{{ tour.tour_unitPrice }} VNĐ / người</span>
            </div>
          </div>
        </div>

        <div class="tour-description mt-5 text">
          <span class="title text-lg font-bold">Mô tả: </span>
          <div class="description mt-3 text-gray-500">
            {{ tour.tour_description }}
          </div>
        </div>
      </div>

      <div class="tour-booking mt-10 w-[30%] p-5 border rounded-xl shadow-md ml-5 h-fit">
        <div class="tour-departure-day flex flex-col mb-3">
          <span class="title text-lg font-bold">Ngày khởi hành:</span>
          <span class="departure-day italic text-gray-500">{{
            moment(tour.tour_departureDay).format('DD/MM/YYYY')
          }}</span>
        </div>

        <div class="tour-back-day flex flex-col mb-3">
          <span class="title text-lg font-bold">Ngày về:</span>
          <span class="back-day italic text-gray-500">{{
            moment(tour.tour_backDay).format('DD/MM/YYYY')
          }}</span>
        </div>

        <div class="tour-quantity flex flex-col mb-3">
          <span class="title text-lg font-bold">Số người: </span>
          <input
            v-model="bookingForm.booking_quantity"
            type="number"
            required
            class="bg-white border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-1/2 p-1 outline-none"
          />
        </div>

        <div class="booking mt-5">
          <button
            @submit.prevent="onBooking"
            @click="onBooking"
            class="p-2 bg-blue-500 hover:bg-blue-700 text-white text-lg rounded-lg"
          >
            Đặt tour
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
