<script setup>
import { ref, onBeforeMount } from 'vue'
import axios from 'axios'
import CarouselComponent from '@/components/CarouselComponent.vue'
import SliderComponent from '@/components/SliderComponent.vue'
import ToursType from '@/components/ToursType.vue'
import FooterComponent from '@/components/FooterComponent.vue'
import FilterComponent from '@/components/FilterComponent.vue'
import moment from 'moment/moment'

const images = [
  'slider-image-1',
  'slider-image-2',
  'slider-image-3',
  'slider-image-4',
  'slider-image-5'
]

const tours = ref([])
const filterDate = ref('')
const filterDestination = ref('')

onBeforeMount(async () => {
  try {
    const response = await axios.get('http://localhost:8090/dulich/api/tour')

    tours.value = response.data
  } catch (error) {
    console.error(`Error fetching data: ${error}`)
  }
})

const filter = (selected) => {
  if (selected.destination === 'Tất cả') {
    filterDestination.value = ''
  } else {
    filterDestination.value = selected.destination
  }
  filterDate.value = selected.date
}

const filterTours = (tour_type) => {
  return tours.value.filter((tour) => {
    const destinationMatch = filterDestination.value
      ? tour.destination.des_name.toLowerCase().includes(filterDestination.value.toLowerCase())
      : true

    const dateMatch = filterDate.value
      ? moment(tour.tour_departureDay).format('DD/MM/YYYY') === filterDate.value
      : true

    const tourTypeMatch = tour_type ? tour.tour_type.tt_typename === tour_type : true

    return destinationMatch && dateMatch && tourTypeMatch
  })
}
</script>

<template>
  <CarouselComponent v-slot="curSlide" class="h-[30rem] relative">
    <SliderComponent v-for="(image, index) in images" :key="index">
      <div v-show="curSlide.curSlide === index + 1" class="slide-detail absolute w-full h-full">
        <img class="w-full h-full object-cover" :src="`src/images/${image}.jpg`" />
      </div>
    </SliderComponent>
  </CarouselComponent>

  <FilterComponent @filterTours="filter" />

  <ToursType>
    <template #title>
      <span class="border-b-2 pb-1 border-blue-400">Các Tours Cao Cấp</span>
    </template>

    <template #tour-item>
      <div
        v-for="tour in filterTours('Cao cấp')"
        :key="tour.tour_id"
        class="tour-items rounded-2xl shadow-xl w-[30.8%] hover:scale-[1.02] duration-300 m-4"
      >
        <div class="tour-image aspect-[16/9] overflow-hidden">
          <img class="object-cover rounded-t-2xl h-full" src="@/images/popular-tours-1-1.jpg" />
        </div>

        <div class="tour-info p-5 space-y-2.5">
          <div class="tour-title text-lg text-blue-700 font-bold h-14 truncate-text-2">
            {{ tour.tour_title }}
          </div>
          <div class="departure-time italic">
            Ngày khởi hành: {{ moment(tour.tour_departureDay).format('DD/MM/YYYY') }}
          </div>

          <div class="truncate-text italic text-gray-500">
            {{ tour.tour_description }}
          </div>
          <div class="tour-price font-bold text-green-500">{{ tour.tour_unitPrice }} vnđ/người</div>
          <div class="funtion-button flex justify-between px-4 items-center pb-4">
            <button
              class="tour-detail-btn bg-blue-500 p-2 mt-4 text-white rounded-lg hover:bg-blue-800 duration-300 w-2/5 mx-auto"
            >
              <router-link :to="`/${tour.tour_id}`">Xem chi tiết</router-link>
            </button>
          </div>
        </div>
      </div>
    </template>
  </ToursType>

  <ToursType>
    <template #title>
      <span class="border-b-2 pb-1 border-blue-400">Các Tours Tiêu Chuẩn</span>
    </template>

    <template #tour-item>
      <div
        v-for="tour in filterTours('Tiêu chuẩn')"
        :key="tour.tour_id"
        class="tour-items rounded-2xl shadow-xl w-[30.8%] hover:scale-[1.02] duration-300 m-4"
      >
        <div class="tour-image aspect-[16/9] overflow-hidden">
          <img class="object-cover rounded-t-2xl h-full" src="@/images/popular-tours-1-1.jpg" />
        </div>

        <div class="tour-info p-5 space-y-2.5">
          <div class="tour-title text-lg text-blue-700 font-bold h-14 truncate-text-2">
            {{ tour.tour_title }}
          </div>
          <div class="departure-time italic">
            Ngày khởi hành: {{ moment(tour.tour_departureDay).format('DD/MM/YYYY') }}
          </div>

          <div class="truncate-text italic text-gray-500">
            {{ tour.tour_description }}
          </div>
          <div class="tour-price font-bold text-green-500">{{ tour.tour_unitPrice }} vnđ/người</div>
          <div class="funtion-button flex justify-between px-4 items-center pb-4">
            <button
              class="tour-detail-btn bg-blue-500 p-2 text-white rounded-lg mt-4 hover:bg-blue-800 duration-300 w-2/5 mx-auto"
            >
              <router-link :to="`/${tour.tour_id}`">Xem chi tiết</router-link>
            </button>
          </div>
        </div>
      </div>
    </template>
  </ToursType>

  <ToursType>
    <template #title>
      <span class="border-b-2 pb-1 border-blue-400">Các Tours Tiết Kiệm</span>
    </template>

    <template #tour-item>
      <div
        v-for="tour in filterTours('Tiết kiệm')"
        :key="tour.tour_id"
        class="tour-items rounded-2xl shadow-xl w-[30.8%] hover:scale-[1.02] duration-300 m-4"
      >
        <div class="tour-image aspect-[16/9] overflow-hidden">
          <img class="object-cover rounded-t-2xl h-full" src="@/images/popular-tours-1-1.jpg" />
        </div>

        <div class="tour-info p-5 space-y-2.5">
          <div class="tour-title text-lg text-blue-700 font-bold h-14 truncate-text-2">
            {{ tour.tour_title }}
          </div>
          <div class="departure-time italic">
            Ngày khởi hành: {{ moment(tour.tour_departureDay).format('DD/MM/YYYY') }}
          </div>

          <div class="truncate-text italic text-gray-500">
            {{ tour.tour_description }}
          </div>
          <div class="tour-price font-bold text-green-500">{{ tour.tour_unitPrice }} vnđ/người</div>
          <div class="funtion-button flex justify-between px-4 items-center pb-4">
            <button
              class="tour-detail-btn bg-blue-500 p-2 text-white rounded-lg mt-4 hover:bg-blue-800 duration-300 w-2/5 mx-auto"
            >
              <router-link :to="`/${tour.tour_id}`">Xem chi tiết</router-link>
            </button>
          </div>
        </div>
      </div>
    </template>
  </ToursType>

  <FooterComponent />
</template>

<style scoped>
.truncate-text {
  display: -webkit-box;
  -webkit-line-clamp: 4;
  overflow: hidden;
  -webkit-box-orient: vertical;
}

.truncate-text-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  overflow: hidden;
  -webkit-box-orient: vertical;
}
</style>
