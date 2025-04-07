<script setup>
import axios from 'axios'
import { ref, onBeforeMount } from 'vue'
import VueTailwindDatepicker from 'vue-tailwind-datepicker'

const destinations = ref(null)
onBeforeMount(async () => {
  try {
    const response = await axios.get('http://localhost:8090/dulich/api/destination')
    destinations.value = response.data
  } catch (error) {
    console.log(`Error fetching data: ${error}`)
  }
})

const selectedDestination = ref('')
const selectedDate = ref('')

const emits = defineEmits(['filterTours'])

const filterTours = () => {
  emits('filterTours', {
    date: selectedDate.value,
    destination: selectedDestination.value
  })
}

const dateFormatter = ref({
  date: 'DD/MM/YYYY',
  month: 'MM'
})
</script>

<template>
  <div
    class="filter-field container mx-auto bg-blue-500 -mt-9 z-[2] relative mb-20 shadow-xl rounded-xl flex justify-between"
  >
    <div
      class="destination-filter bg-white w-[45%] p-3 flex justify-between items-center rounded-l-xl"
    >
      <label class="font-bold">Điểm đến: </label>
      <select v-model="selectedDestination">
        <option value="" disabled>Hãy chọn điểm đến</option>
        <option>Tất cả</option>
        <option v-for="destination in destinations" :key="destination.des_id">
          {{ destination.des_name }}
        </option>
      </select>
    </div>

    <div class="date-filter p-3 w-[45%]">
      <VueTailwindDatepicker
        :formatter="dateFormatter"
        v-model="selectedDate"
        as-single
        class="rounded-none"
      />
    </div>

    <div class="search-button w-10% flex items-cente rounded-xlr">
      <button @click="filterTours" class="bg-red-500 text-white p-4 rounded-r-xl hover:bg-red-700">
        Tìm kiếm
      </button>
    </div>
  </div>
</template>

<style></style>
