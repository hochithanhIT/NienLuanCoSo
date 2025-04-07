<script setup>
import { reactive } from 'vue'
import axios from 'axios'
import router from '@/router/router'
const signUpForm = reactive({
  username: '',
  password: ''
})

const onSubmitLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8090/dulich/api/admin/login', signUpForm)
    // Lưu token vào LocalStorage
    const token = response.data

    // Kiểm tra xem token đã được lưu thành công hay không
    if (token) {
      alert('Đăng nhập thành công')
      router.push('/admin/manageTours')
      localStorage.setItem('isAdmin', token)
      // Đăng nhập thành công, thực hiện hành động sau đăng nhập ở đây
      // Ví dụ: chuyển hướng tới trang chính sau khi đăng nhập thành công
    } else {
      // Đăng nhập không thành công, xử lý phản hồi lỗi ở đây (hiển thị thông báo lỗi, vv.)
      alert('Tài khoản hoặc mật khẩu không đúng')
    }
  } catch (error) {
    // Xử lý phản hồi lỗi (hiển thị thông báo lỗi, vv.)
    console.error('Đăng nhập không thành công:', error)
    alert('Tài khoản hoặc mật khẩu không đúng')
  }
}
</script>

<template>
  <div class="flex items-center justify-center h-screen w-full">
    <div class="bg-white shadow-md rounded-lg px-8 py-6 w-1/3">
      <h1 class="text-2xl font-bold text-center mb-4">Welcome Back!</h1>
      <form @submit.prevent="onSubmitLogin">
        <div class="mb-4">
          <label for="admin-user-name" class="block text-sm font-medium text-gray-700 mb-2"
            >Tên đăng nhập</label
          >
          <input
            v-model="signUpForm.username"
            type="text"
            class="shadow-sm rounded-md w-full px-3 py-2 border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            placeholder="Tên đăng nhập"
            required
          />
        </div>
        <div class="mb-4">
          <label for="password" class="block text-sm font-medium text-gray-700 mb-2"
            >Mật khẩu</label
          >
          <input
            v-model="signUpForm.password"
            type="password"
            id="password"
            class="shadow-sm rounded-md w-full px-3 py-2 border border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
            placeholder="Vui lòng nhập mật khẩu"
            required
          />
        </div>
        <button
          type="submit"
          class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Đăng nhập
        </button>
      </form>
    </div>
  </div>
</template>

<style scoped></style>
