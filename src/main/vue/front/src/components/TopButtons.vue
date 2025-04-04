<template>
  <div class="top-buttons">
    <button @click="$emit('top10-trimestre')">Top 10 Despesas Trimestre</button>
    <button @click="$emit('top10-ano')">Top 10 Despesas Ano</button>
  </div>
</template>

<script>
export default {
  name: "TopButtons",
};
</script>

<style scoped>
.top-buttons {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #4CAF50;
  color: #fff;
  cursor: pointer;
}

button:hover {
  background-color: #3e8e41;
}
</style>