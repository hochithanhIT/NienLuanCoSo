<script setup></script>

<template>
  <div class="slide">
    <transition name="slide">
      <slot />
    </transition>
  </div>
</template>

<style scoped>
.slide-enter-active,
.slide-leave-active {
  transition: opacity 1.4s ease-in-out;
}
.slide-enter-from,
.slide-leave-to {
  opacity: 0;
}
</style>
