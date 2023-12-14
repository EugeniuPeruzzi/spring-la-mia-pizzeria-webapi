<script setup>
// Importazioni
import Index from './components/Index.vue';
import axios from "axios";
import { ref, onMounted } from "vue";
import PizzaShow from './components/PizzaShow.vue';

// PROPS
const pizzas = ref([]);
const pizzaActive = ref(null);

// FUNCTIONS
// Axios
const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/pizza");
  pizzas.value = data.data;
};

const openPizza = (id) => {
  pizzas.value.forEach((pizza) => {
    if (pizza.id === id) {
      pizzaActive.value = pizza;
    }
  });
};

onMounted(getPizzas);

</script>

<template>
  <Index :pizzas = "pizzas"/>
  <PizzaShow :pizzas = "pizzaActive" @open-pizza="openPizza"/>
</template>

<style scoped>
</style>
