<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios'
import moment from 'moment'
import ModalComponent from './ModalComponent.vue'

const tours = ref([])
const isShowModal = ref(false)
const isUpdate = ref(false)
const newTour = ref({
  tour_title: '',
  tour_description: '',
  tour_day: '',
  tour_slot: '',
  tour_departureDay: '',
  tour_backDay: '',
  tour_type: {
    tt_id: ''
  },
  destination: {
    des_id: ''
  },
  tour_unitPrice: ''
})
const tourDetail = ref({})

const getTourDetail = async (tourId) => {
  try {
    const response = await axios.get(`http://localhost:8090/dulich/api/tour/${tourId}`)
    
    tourDetail.value = response.data
  } catch (error) {
    console.error(`Error fetching tour detail: ${error}`)
  }
}

onBeforeMount(async () => {
  try {
    const response = await axios.get('http://localhost:8090/dulich/api/tour')
    console.log(response.data)
    tours.value = response.data
  } catch (error) {
    console.error(`Error fetching data: ${error}`)
  }
})

const fetchTour = async () => {
  try {
    const response = await axios.get('http://localhost:8090/dulich/api/tour')

    tours.value = response.data
  } catch (error) {
    console.error(`Error fetching data: ${error}`)
  }
}

const deleteTour = async (tourId) => {
  try {
    confirm('Bạn có chắc chắn muốn xóa tour này không?')
    await axios.delete(`http://localhost:8090/dulich/api/tour/${tourId}`)
    alert('Xóa tour thành công')
    fetchTour()
  } catch (error) {
    console.error('Error deleting tour:', error)
    alert('Xóa tour không thành công')
  }
}

const submitForm = async () => {
  try {
    confirm('Bạn có chắc chắn muốn thêm tour mới không?')
    await axios.post('http://localhost:8090/dulich/api/tour', newTour.value)
    alert('Thêm tour mới thành công')
    isShowModal.value = false
    fetchTour()
  } catch (error) {
    console.error('Error adding new tour:', error)
    alert('Thêm tour mới không thành công')
  }
}

const submitUpdate = async () => {
  try {
    confirm('Bạn có chắc chắn muốn cập nhật tour này không?')
    await axios.put(`http://localhost:8090/dulich/api/tour/${tourDetail.value.tour_id}`, tourDetail.value)
    alert('Cập nhật tour thành công')
    isUpdate.value = false
    fetchTour()
  } catch (error) {
    console.error('Error updating tour:', error)
    alert('Cập nhật tour không thành công')
  }
}


const openModal = () => {
  isShowModal.value = true
}
const openUpdateModal = (tourId) => {
  isUpdate.value = true
  getTourDetail(tourId)
}
const closeModal = () => {
  isShowModal.value = false
  isUpdate.value = false
}
</script>

<template>
  <main class="ml-64 h-screen">
    <div class="tool-bar p-3 bg-gray-200 text-xl flex justify-between">
      <div class="title ml-4 font-bold">Quản lý Tour</div>
      <button
        @click="openModal"
        class="p-2 bg-green-500 text-white rounded-md text-base hover:bg-green-700"
      >
        Thêm tour mới
      </button>
    </div>

    <div class="relative overflow-x-auto shadow-md mt-10">
      <table class="w-full text-sm text-left text-gray-400">
        <thead class="text-xs text-gray-900 uppercase bg-green-300">
          <tr>
            <th scope="col" class="px-4 py-3">Mã Tour</th>
            <th scope="col" class="px-4 py-3">Tên Tour</th>
            <th scope="col" class="px-4 py-3">Mô tả</th>
            <th scope="col" class="px-4 py-3">Thời gian</th>
            <th scope="col" class="px-4 py-3">Số lượng</th>
            <th scope="col" class="px-4 py-3">Ngày đi</th>
            <th scope="col" class="px-4 py-3">Ngày về</th>
            <th scope="col" class="px-4 py-3">Loại Tour</th>
            <th scope="col" class="px-4 py-3">Điểm đến</th>
            <th scope="col" class="px-4 py-3">Đơn giá</th>
            <th scope="col" class="px-4 py-3">Thao tác</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="tour in tours" :key="tour.tour_id" class="bg-gray-100 border-b">
            <th scope="row" class="px-4 py-4 font-medium text-gray-900 w-2">{{ tour.tour_id }}</th>
            <td class="px-4 py-4">{{ tour.tour_title }}</td>
            <td class="px-4 pt-4 mb-4 description w-[20rem]">
              {{ tour.tour_description }}
            </td>
            <td class="px-4 py-4 w-32">{{ tour.tour_day }}</td>
            <td class="px-4 py-4 w-5">{{ tour.tour_slot }}</td>
            <td class="px-4 py-4 w-28">
              {{ moment(tour.tour_departureDay).format('DD/MM/YYYY') }}
            </td>
            <td class="px-4 py-4 w-28">{{ moment(tour.tour_backDay).format('DD/MM/YYYY') }}</td>
            <td class="px-4 py-4">{{ tour.tour_type.tt_typename }}</td>
            <td class="px-4 py-4">{{ tour.destination.des_name }}</td>
            <td class="px-4 py-4">{{ tour.tour_unitPrice }} VNĐ</td>
            <td class="px-4 py-4">
              <button @click="openUpdateModal(tour.tour_id)" class="font-medium text-white bg-blue-500 p-1 rounded-md"> Sửa </button>
              <button @click="deleteTour(tour.tour_id)" class="font-medium text-white bg-red-500 p-1 rounded-md"> Xóa </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </main>

  <ModalComponent @closeModal="closeModal" v-if="isUpdate">
    <template #modal-header>
      <span class="text-2xl font-bold">Tour</span>
    </template>
    <template #modal-content>
      <form @submit.prevent="submitUpdate">
        <div>
          <label for="tour_title" class="block mb-2 text-sm font-medium text-gray-900">Mã Tour:</label>
          <input
            id="tour_id"
            v-model="tourDetail.tour_id"
            type="text"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1"
          />
        </div>
        <div>
          <label for="tour_title" class="block mb-2 text-sm font-medium text-gray-900">Tên Tour:</label>
          <input
            id="tour_title"
            v-model="tourDetail.tour_title"
            type="text"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1"
          />
        </div>
        <div>
          <label for="tour_description" class="block mb-2 text-sm font-medium text-gray-900">Mô tả:</label>
          <textarea id="tour_description" rows="5" v-model="tourDetail.tour_description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1"></textarea>
        </div>
        <div>
          <label for="tour_day" class="block mb-2 text-sm font-medium text-gray-900">Thời gian:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_day" v-model="tourDetail.tour_day" type="text" />
        </div>
        <div>
          <label for="tour_slot" class="block mb-2 text-sm font-medium text-gray-900">Số lượng:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_slot" v-model="tourDetail.tour_slot" type="number" />
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_departureDay">Tour Departure Day:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_departureDay" v-model="tourDetail.tour_departureDay" type="date" />
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_backDay">Tour Back Day:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_backDay" v-model="tourDetail.tour_backDay" type="date" />
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_type">Mã Loại:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_type" v-model="tourDetail.tour_type.tt_id" type="number" />
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="destination">Mã điểm đến:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="destination" v-model="tourDetail.destination.des_id" type="text" />
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_unitPrice">Đơn giá:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_unitPrice" v-model="tourDetail.tour_unitPrice" type="number" />
        </div>
        <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mt-4">Submit</button>
      </form>
    </template>
  </ModalComponent>

  <ModalComponent @closeModal="closeModal" v-if="isShowModal">
    <template #modal-header>
      <span class="text-2xl font-bold">Tour Mới</span>
    </template>
    <template #modal-content>
      <form @submit.prevent="submitForm">
        <div>
          <label for="tour_title" class="block mb-2 text-sm font-medium text-gray-900">Mã Tour:</label>
          <input
            id="tour_id"
            v-model="newTour.tour_id"
            type="text"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1"
            required
          />
        </div>
        <div>
          <label for="tour_title" class="block mb-2 text-sm font-medium text-gray-900">Tên Tour:</label>
          <input
            id="tour_title"
            v-model="newTour.tour_title"
            type="text"
            class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1"
            required
          />
        </div>
        <div>
          <label for="tour_description" class="block mb-2 text-sm font-medium text-gray-900">Mô tả:</label>
          <textarea id="tour_description" rows="5" v-model="newTour.tour_description" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" required></textarea>
        </div>
        <div>
          <label for="tour_day" class="block mb-2 text-sm font-medium text-gray-900">Thời gian:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_day" v-model="newTour.tour_day" type="text" required/>
        </div>
        <div>
          <label for="tour_slot" class="block mb-2 text-sm font-medium text-gray-900">Số lượng:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_slot" v-model="newTour.tour_slot" type="number" required/>
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_departureDay">Tour Departure Day:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_departureDay" v-model="newTour.tour_departureDay" type="date" required/>
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_backDay">Tour Back Day:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_backDay" v-model="newTour.tour_backDay" type="date" required/>
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_type">Mã Loại:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_type" v-model="newTour.tour_type.tt_id" type="number" required/>
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="destination">Mã điểm đến:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="destination" v-model="newTour.destination.des_id" type="text" required/>
        </div>
        <div>
          <label class="block mb-2 text-sm font-medium text-gray-900" for="tour_unitPrice">Đơn giá:</label>
          <input class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg w-full p-1" id="tour_unitPrice" v-model="newTour.tour_unitPrice" type="number" required/>
        </div>
        <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mt-4">Submit</button>
      </form>
    </template>
  </ModalComponent>


</template>

<style scoped>
.description {
  display: -webkit-box;
  -webkit-line-clamp: 5; /* Số dòng tối đa bạn muốn hiển thị */
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
