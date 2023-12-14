<script setup>
import { ref, computed } from 'vue';
import { defineProps } from 'vue';  

// EMITS
const emits = defineEmits(["openPizza"]);
// Props
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});

// Model for search input
const search = ref('');

// Computed property to filter pizzas based on search input
const filteredPizzas = computed(() => {
    if (!search.value) return props.pizzas;
    return props.pizzas.filter(pizza => pizza.name.toLowerCase().includes(search.value.toLowerCase()));
});
</script>

<template>
    <div class="input-group input-group-sm my-3">
        <button type="button">Cerca</button>
        <input v-model="search" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
    </div>
    <h1>Pizze</h1>
    <ul>
        <li v-for="pizza in filteredPizzas" :key="pizza.id" @click="$emit('openPizza', pizza.id)">
            <h2><span class="d-none"> -- </span>{{ pizza.name }}<span class="d-none"> -- </span></h2>
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

.d-none {
    display: none;
}

li:hover .d-none {
    display: block;
    display: inline;
}
</style>
