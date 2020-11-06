<template>
  <div id="app" class="container mx-auto">
    <h1 class="text-blue-700 text-2xl text-center py-6">Voitures</h1>
    <car-list></car-list>
  </div>
</template>

<script>
import CarList from "@/components/CarList";

export default {
  name: 'App',
  components: {
    CarList,
  }
}
</script>

<style>
@tailwind base;
@tailwind components;
@tailwind utilities;
</style>
