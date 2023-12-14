<script setup>

// Props
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});
</script>

<template>
    <h1>Pizze</h1>
    <ul>
        <li v-for="pizza in pizzas" :key="pizza.id" @click="$emit('openPizza', pizza.id)">
            <h2>{{ pizza.name }}</h2>
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
