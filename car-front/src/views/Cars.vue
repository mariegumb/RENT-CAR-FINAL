<template>
  <div>
    <div class="flex items-center">
      <div class="w-1/3"></div>
      <h1 class="w-1/3 text-blue-700 text-2xl text-center py-6">Voitures</h1>
      <div class="w-1/3 text-right">
        <router-link to="/car-form">
          <button type="button"  class="bg-blue-700 text-white hover:bg-blue-800 rounded px-2 py-1">
            Create new car
          </button>
        </router-link>
      </div>
    </div>
    <car-list></car-list>
  </div>
</template>

<script>
import CarList from "@/components/CarList";
export default {
  name: "Cars",
  components: {CarList}
}
</script>

<style scoped>

</style>