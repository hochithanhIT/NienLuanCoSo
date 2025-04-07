<script setup>
import NavBar from './NavBar.vue'
</script>

<template>
  <header class="header px-3 bg-white py-1 sticky top-0 z-10 shadow-lg">
    <NavBar>
      <template #menu-item-list>
        <router-link to="/" class="nav-link group">
          <span class="link-title"> Trang chủ </span>
        </router-link>
        <router-link to="/contact" class="nav-link group">
          <span class="link-title"> Liên hệ </span>
        </router-link>
      </template>
    </NavBar>
  </header>
</template>

<style scoped>
.nav-link {
  @apply p-4 transition-all duration-300 ease-in-out text-lg font-normal;
}

.link-title {
  @apply bg-gradient-to-r from-blue-800 to-blue-800 bg-[length:0%_2px] bg-left-bottom bg-no-repeat pb-1.5 transition-all duration-500 ease-out group-hover:bg-[length:100%_2px];
}
</style>
