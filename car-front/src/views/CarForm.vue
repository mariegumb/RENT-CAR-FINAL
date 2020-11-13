<template>
  <div>
    <div class="flex items-center">
      <div class="w-1/3">
        <router-link to="/">
          <button type="button"  class="bg-blue-700 text-white hover:bg-blue-800 rounded px-2 py-1">
            <i class="fa fa-chevron-left"></i> Return
          </button>
        </router-link>
      </div>
      <div class="w-1/3">
        <h1 class="text-blue-700 text-2xl text-center py-6">Create new car <i class="fa fa-car"></i></h1>
      </div>
      <div class="w-1/3"></div>
    </div>
    <div>
      <div class="w-1/3 mx-auto">
        <input type="text" v-model="newCar.brand" placeholder="Brand" id="brand" class=" mt-2 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        <input type="text" v-model="newCar.plateNumber" placeholder="Plate Number" id="plateNumber" class=" mt-2 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        <input type="text" v-model="newCar.numberOfSeats" placeholder="Number Of Seats" id="NumberOfSeats" class=" mt-2 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        <input type="text" v-model="newCar.price" placeholder="Price" id="price" class=" mt-2 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        <div class="mt-2 text-right">
          <button class="px-2 py-1 rounded bg-green-500 hover:bg-green-600 text-white" type="button" @click="addCar"><i class="fa fa-save"></i> Save</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as axios from "axios";

export default {
  name: "CarForm",
  data(){
    return {
      newCar: {
        brand: '',
        plateNumber: '',
        numberOfSeats:'',
        price:'',
      }
    }
  },
  methods: {
    async addCar() {
      await axios.post('http://localhost:8080/cars/', null, {params: this.newCar})
      this.$emit("refresh")
      this.resetForm()
      await this.$router.push('/')
    },
    resetForm(){
      this.newCar.brand = ''
      this.newCar.plateNumber = ''
      this.newCar.numberOfSeats = ''
      this.newCar.price = ''
    }
  }
}
</script>

<style scoped>

</style>