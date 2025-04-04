<template>
  <div>
    <input type="text" v-model="query.registroANS" placeholder="Registro ANS" />
    <input type="text" v-model="query.cnpj" placeholder="CNPJ" />
    <input type="text" v-model="query.razaoSocial" placeholder="Razão Social" />
    <input type="text" v-model="query.nomeFantasia" placeholder="Nome Fantasia" />
    <input type="text" v-model="query.cidade" placeholder="Cidade" />
    <input type="text" v-model="query.uf" placeholder="UF" />
    <button @click="$emit('search', query)">Pesquisar</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      query: {
        registroANS: "",
        cnpj: "",
        razaoSocial: "",
        nomeFantasia: "",
        cidade: "",
        uf: "",
      },
    };
  },
};
</script>