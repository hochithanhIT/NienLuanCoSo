/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './index.html',
    './src/**/*.{vue,js}',
    './node_modules/vue-tailwind-datepicker/**/*.js'
  ],
  theme: {
    extend: {
      fontFamily: {
        lexend: ["'Lexend Deca'", 'san-serif']
      }
    }
  },
  plugins: []
}
