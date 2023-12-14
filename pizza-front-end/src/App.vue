<script setup>
// Importazioni
import Index from './components/Index.vue';
import axios from "axios";
import { ref, onMounted } from "vue";
import PizzaCreate from './components/PizzaCreate.vue';

// PROPS
const pizzas = ref([]);
const creatingPizza = ref(false);

// FUNCTIONS

// Axios
const getPizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/pizza");
  pizzas.value = data.data;
};

let pizzaCreated = () => {
  creatingPizza.value = false;
  getPizzas();
};


onMounted(getPizzas);

</script>

<template>
  <PizzaCreate v-if="creatingPizza" @back = "creatingPizza = false" @created = "pizzaCreated"/>
  <div v-else>
  <button type="button" class="btn btn-success" @click="creatingPizza = true">Crea pizza</button>
  <Index :pizzas = "pizzas"/>
  </div>
</template>

<style scoped>
</style>
