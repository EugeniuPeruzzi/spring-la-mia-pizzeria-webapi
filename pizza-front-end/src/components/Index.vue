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
