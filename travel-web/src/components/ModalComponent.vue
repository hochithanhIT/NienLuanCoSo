<script setup>
const emits = defineEmits(['closeModal'])

const close = () => {
  emits('closeModal')
}
</script>

<template>
  <!-- Main modal -->
  <div
    class="modal-backdrop overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full h-full flex"
  >
    <div class="relative p-4 w-1/3">
      <!-- Modal content -->
      <div class="relative bg-white rounded-lg shadow">
        <!-- Modal header -->
        <div class="flex items-center justify-between p-4 border-b rounded-t">
          <slot name="modal-header"></slot>
          <button
            type="button"
            class="end-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center"
            @click="close"
          >
            <svg
              class="w-3 h-3"
              aria-hidden="true"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 14 14"
            >
              <path
                stroke="currentColor"
                stroke-linecap="round"
                stroke-linejoin="round"
                stroke-width="2"
                d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
              />
            </svg>
          </button>
        </div>
        <!-- Modal body -->
        <div class="p-4 body">
          <slot name="modal-content"></slot>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.modal-backdrop {
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
