<script setup>
import { ref, computed } from 'vue';
import { defineProps, defineEmits } from 'vue';
import axios from 'axios'; 

// EMITS
const emits = defineEmits(["openPizza","deletePizza"]);

// Props
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});

// Modello per l'input di ricerca
const search = ref('');
// Proprietà per filtrare le pizze in base all'input di ricerca
const filteredPizzas = computed(() => {
    if (!search.value) return props.pizzas;
    return props.pizzas.filter(pizza => pizza.name.toLowerCase().includes(search.value.toLowerCase()));
});

// Funzione per eliminare una pizza
const deletePizza = async (id) => {
    const data = await axios.delete(`http://localhost:8080/api/pizza/${id}`);
    console.log(data);
    emits("deletePizza");
};


</script>

<template>
    <div class="input-group input-group-sm my-3">
        <input v-model="search" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
    </div>
    <h1>Pizze</h1>
    <ul>
        <li v-for="pizza in filteredPizzas" :key="pizza.id">
            <h2>{{ pizza.name }}</h2>
            <button @click="deletePizza(pizza.id)" class="btn btn-danger">Elimina</button>
        </li>
    </ul>
</template>
<style scoped>

ul {
    list-style-type: none;
}
li {
    cursor: pointer;
}

</style>
