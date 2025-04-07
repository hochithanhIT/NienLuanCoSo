<script setup>
import { reactive, ref } from 'vue'
import ModalComponent from './ModalComponent.vue'
import axios from 'axios'
import router from '@/router/router'

const loginUser = ref(null)

const token = ref(null)

const signUpForm = reactive({
  us_name: '',
  us_password: '',
  us_fullname: '',
  us_gender: '',
  us_email: '',
  us_address: '',
  us_phonenumber: ''
})

const loginForm = reactive({
  us_name: '',
  us_password: ''
})

const isShowModalLogIn = ref(false)
const isShowModalSignUp = ref(false)

const openLogIn = () => {
  isShowModalLogIn.value = true
  isShowModalSignUp.value = false
}

const openSignUp = () => {
  isShowModalSignUp.value = true
  isShowModalLogIn.value = false
}

const closeModal = () => {
  isShowModalLogIn.value = false
  isShowModalSignUp.value = false
}

const getUser = async (us_name) => {
  try {
    const response = await axios.get(`http://localhost:8090/dulich/api/users/${us_name}`)
    loginUser.value = response.data
    console.log(loginUser.value)
  } catch (error) {
    console.log('Không lấy được thông tin người dùng: ' + error)
  }
}

const onSubmitLogin = async () => {
  try {
    const response = await axios.post('http://localhost:8090/dulich/api/users/login', loginForm)
    // Lưu token vào LocalStorage
    token.value = response.data

    // Kiểm tra xem token đã được lưu thành công hay không
    if (token.value) {
      alert('Đăng nhập thành công')
      closeModal()
      router.push('/')
      localStorage.setItem('token', token.value)
      localStorage.setItem('username', loginForm.us_name)
      getUser(loginForm.us_name)
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

const signOut = () => {
  token.value = null
  loginUser.value = null
  localStorage.setItem('token', token.value)
  localStorage.setItem('username', loginUser)
  router.push('/')
}

const onSubmitSignUp = async () => {
  try {
    signUpForm.us_gender = signUpForm.us_gender === 'true' ? true : false
    await axios.post('http://localhost:8090/dulich/api/users/register', signUpForm)
    // Xử lý phản hồi thành công (hiển thị thông báo, vv.)
    alert('Đăng ký thành công')
    console.log('Đăng ký thành công')
    // Đóng modal đăng ký
    closeModal()
  } catch (error) {
    // Xử lý phản hồi lỗi (hiển thị thông báo lỗi, vv.)
    alert('Tên đăng nhập đã tồn tại')
    console.error('Đăng ký không thành công:', error)
  }
}
</script>

<template>
  <nav class="flex justify-between">
    <div class="menu flex items-center w-1/3">
      <slot name="menu-item-list" />
    </div>

    <div class="title relative w-1/3 flex justify-center">
      <router-link class="absolute bottom-1" to="/">
        <span class="font-bold text-5xl">Travel</span>
      </router-link>
    </div>

    <div class="account flex justify-end items-center mr-10 w-1/3">
      <div v-if="!token" class="log-in">
        <button
          @click="openLogIn"
          class="btn-log-in mx-2 bg-blue-500 hover:bg-blue-800 text-white hover:white py-1 px-3 border border-transparent hover:border-blue-800 rounded duration-200"
        >
          Đăng nhập
        </button>

        <ModalComponent @close-modal="closeModal" v-if="isShowModalLogIn">
          <template #modal-header>
            <span class="text-2xl font-bold">Đăng nhập</span>
          </template>
          <template #modal-content>
            <form @submit.prevent="onSubmitLogin" class="space-y-4" action="#">
              <div>
                <label for="username" class="block mb-2 text-sm font-medium text-gray-900"
                  >Tên đăng nhập</label
                >
                <input
                  v-model="loginForm.us_name"
                  type="text"
                  name="username"
                  id="username"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                  placeholder="Tên đăng nhập"
                  required
                />
              </div>
              <div>
                <label for="password" class="block mb-2 text-sm font-medium text-gray-900"
                  >Mật khẩu</label
                >
                <input
                  v-model="loginForm.us_password"
                  type="password"
                  name="password"
                  id="password"
                  placeholder="••••••••"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                  required
                />
              </div>

              <button
                type="submit"
                class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
              >
                Đăng nhập
              </button>
              <div class="text-sm font-medium text-gray-500" @click="openSignUp">
                Chưa có tài khoản?
                <a href="#" class="text-blue-700 hover:underline">Đăng ký</a>
              </div>
            </form>
          </template>
        </ModalComponent>
      </div>

      <div v-if="!token" class="sign-up">
        <button
          @click="openSignUp"
          class="sign-up mx-2 bg-transparent hover:bg-blue-800 text-blue-700 hover:text-white py-1 px-3 border border-blue-500 hover:border-transparent rounded duration-200"
        >
          Đăng ký
        </button>
        <ModalComponent @closeModal="closeModal" v-if="isShowModalSignUp">
          <template #modal-header>
            <span class="text-2xl font-bold">Đăng ký</span>
          </template>
          <template #modal-content>
            <form @submit.prevent="onSubmitSignUp" class="space-y-4" action="#">
              <div>
                <label for="username" class="block mb-2 text-sm font-medium text-gray-900">
                  Tên đăng nhập <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="signUpForm.us_name"
                  type="text"
                  name="username"
                  id="username"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                  placeholder="Tên đăng nhập"
                  required
                />
              </div>
              <div>
                <label for="password" class="block mb-2 text-sm font-medium text-gray-900">
                  Mật khẩu <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="signUpForm.us_password"
                  type="password"
                  name="password"
                  id="password"
                  placeholder="••••••••"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                  required
                />
              </div>
              <div class="flex items-center">
                <div class="fullname-field w-[65%] mr-10">
                  <label for="fullname" class="block mb-2 text-sm font-medium text-gray-900">
                    Họ tên <span class="text-red-600">*</span>
                  </label>
                  <input
                    v-model="signUpForm.us_fullname"
                    type="text"
                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                    placeholder="Nguyễn Văn A"
                    required
                  />
                </div>

                <div class="gender-field w-[35%]">
                  <div class="title">
                    <label class="block mb-2 text-sm font-medium text-gray-900"> Giới tính </label>
                  </div>

                  <div class="gender-select flex">
                    <label for="gender-male" class="p-2 text-sm">
                      Nam
                      <input
                        v-model="signUpForm.us_gender"
                        type="radio"
                        name="gender"
                        id="gender-male"
                        value="true"
                      />
                    </label>
                    <label for="gender-female" class="p-2 text-sm">
                      Nữ
                      <input
                        v-model="signUpForm.us_gender"
                        type="radio"
                        name="gender"
                        id="gender-female"
                        value="false"
                      />
                    </label>
                  </div>
                </div>
              </div>

              <div>
                <label for="email" class="block mb-2 text-sm font-medium text-gray-900">
                  Email <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="signUpForm.us_email"
                  type="email"
                  name="email"
                  id="email"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                  placeholder="name@company.com"
                  required
                />
              </div>

              <div>
                <label for="address" class="block mb-2 text-sm font-medium text-gray-900">
                  Địa chỉ <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="signUpForm.us_address"
                  type="text"
                  name="address"
                  id="address"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                  placeholder="Số 123 đường abc"
                  required
                />
              </div>

              <div>
                <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">
                  Số điện thoại <span class="text-red-600">*</span>
                </label>
                <input
                  v-model="signUpForm.us_phonenumber"
                  type="text"
                  name="phone"
                  id="phone"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2"
                  placeholder="0123xxx"
                  required
                />
              </div>

              <button
                type="submit"
                class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
              >
                Đăng ký
              </button>
              <div class="text-sm font-medium text-gray-500" @click="openLogIn">
                Đã có tài khoản?
                <a href="#" class="text-blue-700 hover:underline">Đăng nhập</a>
              </div>
            </form>
          </template>
        </ModalComponent>
      </div>

      <div
        v-if="loginUser"
        class="info-user flex flex-col justify-center items-center py-1 px-3 cursor-pointer"
      >
        <span class="material-symbols-outlined"> person </span>
        {{ loginUser.us_name }}
      </div>

      <div v-if="token" class="log-out">
        <button
          @click="signOut"
          class="btn-log-in mx-2 bg-blue-500 hover:bg-blue-800 text-white hover:white py-1 px-3 border border-transparent hover:border-blue-800 rounded duration-200"
        >
          Đăng xuất
        </button>
      </div>
    </div>
  </nav>
</template>

<style scoped></style>
