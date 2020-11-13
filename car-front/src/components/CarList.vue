<template>
<div>
  <div class="grid grid-cols-4 gap-4">
  <div v-for="car in cars" :key="car.plateNumber"> <!-- boucle pour afficher les voitures -->
    <car @refresh="getCars" :car="car"></car> <!-- composant responsable de car (refresh : pour refresh des qu'on fait une action dans car -->
  </div>
</div>
</div>

</template>

<script>
import Car from "@/components/Car";
const axios = require('axios');

export default {
  name: "CarList",
  components: {Car},
  data() {
    return {
      cars : []
    }
  },
  created() {
    this.getCars()
  },
  methods:{
    async getCars(){
      const res = await axios.get('http://localhost:8080/cars')
      this.cars = res.data
    }
  }
}
</script>
