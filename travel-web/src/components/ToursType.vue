<script setup></script>

<template>
  <div class="tours-type">
    <div class="container mx-auto flex flex-col justify-center items-center pt-16 pb-32 border-t">
      <div
        class="title text-3xl w-1/2 text-center font-bold bg-white py-8 -mt-28 shadow-lg z-[1] flex items-center justify-center"
      >
        <slot name="title"></slot>
      </div>

      <div class="tours mt-10 flex item-center flex-wrap">
        <slot name="tour-item"></slot>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
