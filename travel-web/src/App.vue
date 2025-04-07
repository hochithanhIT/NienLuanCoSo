<script setup>
import HeaderComponent from './components/HeaderComponent.vue'
</script>

<template>
  <HeaderComponent v-if="!$route.meta.hideNavbar" />
  <RouterView />
</template>

<style></style>
