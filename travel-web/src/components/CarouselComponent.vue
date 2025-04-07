<script setup>
import { ref, onMounted } from 'vue'
const currentSlide = ref(1)
const getSlideCount = ref(null)
const autoPlayEnable = ref(true)
const timeDuration = ref(3000)
let autoPlayId

onMounted(() => {
  getSlideCount.value = document.querySelectorAll('.slide').length
})

const nextSlide = () => {
  if (currentSlide.value === getSlideCount.value) {
    currentSlide.value = 1
    return
  }
  currentSlide.value += 1
}

const prevSlide = () => {
  if (currentSlide.value === 1) {
    currentSlide.value = 1
    return
  }
  currentSlide.value -= 1
}

const autoPlay = () => {
  autoPlayId = setInterval(() => {
    nextSlide()
  }, timeDuration.value)
}

const stopPlaying = () => {
  clearInterval(autoPlayId)
}

if (autoPlayEnable.value) {
  autoPlay()
}
</script>

<template>
  <div class="carousel">
    <slot :curSlide="currentSlide" />

    <div
      @mouseover="stopPlaying"
      @mouseleave="autoPlay()"
      class="navigation absolute w-full h-full px-4 flex items-center justify-between"
    >
      <div class="left" @click="prevSlide">
        <span class="material-symbols-outlined arrow"> arrow_back_ios_new </span>
      </div>
      <div class="right" @click="nextSlide">
        <span class="material-symbols-outlined arrow"> arrow_forward_ios </span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.navigation .left,
.navigation .right {
  @apply invisible;
}

.navigation:hover .left,
.navigation:hover .right {
  @apply cursor-pointer w-14 h-14 rounded-full text-white flex justify-center items-center visible duration-300;
  background-color: rgba(0, 0, 0, 0.568);
}
</style>
