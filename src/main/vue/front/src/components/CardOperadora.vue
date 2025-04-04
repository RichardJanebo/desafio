<template>
  <div class="card-operadora">
    <h3>{{ operadora.nomeFantasia }}</h3>
    <p>{{ operadora.razaoSocial }}</p>
    <p>{{ operadora.cnpj }}</p>
    <p>{{ operadora.registroANS }}</p>
  </div>
</template>

<script>
export default {
  props: {
    operadora: Object,
  },
};
</script>

<style scoped>
.card-operadora {
  background-color: #f7f7f7;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h3 {
  font-weight: bold;
  margin-bottom: 10px;
}

p {
  margin-bottom: 10px;
}
</style>