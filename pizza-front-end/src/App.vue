<script setup>
// Importazioni
import Index from './components/Index.vue';
import axios from "axios";
import { ref, onMounted } from "vue";

// PROPS
const pizzas = ref(null);

// FUNCTIONS
// Axios
const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/pizza");
  pizzas.value = data.data;
};

onMounted(getPizzas);

</script>

<template>
  <Index :pizzas = "pizzas"/>
</template>

<style scoped>
</style>
