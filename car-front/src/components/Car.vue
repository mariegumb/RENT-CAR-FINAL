<template>
  <div class="bg-gray-200 p-4 rounded-xl">
    <p class="text-xl pb-2">{{car.brand}}  </p>
    <div class="flex items-center">
      <img class="border-l-2 border-t-2 border-b-2 border-solid border-blue-800 rounded-l h-8 inline-block" src="../assets/plateNumber.png" alt="">
      <span class="inline-block bg-white border-t-2 border-b-2 border-r-2 border-solid border-blue-800 px-3 rounded-r h-8"> {{car.plateNumber }}</span>
    </div>
    <p > Number of seats: {{car.numberOfSeats}} </p>
    <p > Price: {{car.price}} $/week </p>
    <div class="text-right ">
      <button type="button" v-if="!car.rent" @click="rentCar" class="bg-blue-700 text-white hover:bg-blue-800 rounded px-2 py-1">
        Rent it now !
      </button>
      <button type="button" v-else @click="getBackCar" class="bg-pink-600 text-white hover:bg-pink-800 rounded px-2 py-1">
        Get back the car !
      </button>
    </div>
  </div>
</template>

<script>

import * as axios from "axios";

export default {
  name: "Car",
  props: ['car'],
  methods:{
    async rentCar() {
      const beginRent = new Date()
      const endRent = new Date()
      endRent.setDate(endRent.getDate() + 7)
      const dates = {begin: beginRent.toISOString(), end: endRent.toISOString()}
      await axios.put('http://localhost:8080/cars/' + this.car.plateNumber, null, {params: dates})
      this.$emit("refresh")
    },
    async getBackCar(){
      await axios.delete('http://localhost:8080/cars/' + this.car.plateNumber)
      this.$emit("refresh")
    }
  }
}
</script>

<style scoped>

</style>