<template>
  <table v-if="operadoras.length > 0">
    <thead>
      <tr>
        <th>Registro ANS</th>
        <th>CNPJ</th>
        <th>Razão Social</th>
        <th>Nome Fantasia</th>
        <th>Modalidade</th>
        <th>Cidade</th>
        <th>UF</th>
        <th>Telefone</th>
        <th>E-mail</th>
        <th>Data Registro ANS</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="operadora in operadoras" :key="operadora.registroANS">
        <td>{{ operadora.registroANS }}</td>
        <td>{{ operadora.cnpj }}</td>
        <td>{{ operadora.razaoSocial }}</td>
        <td>{{ operadora.nomeFantasia }}</td>
        <td>{{ operadora.modalidade }}</td>
        <td>{{ operadora.cidade }}</td>
        <td>{{ operadora.uf }}</td>
        <td>{{ operadora.telefone }}</td>
        <td>{{ operadora.enderecoEletronico }}</td>
        <td>{{ operadora.dataRegistroANS }}</td>
      </tr>
    </tbody>
  </table>
  <p v-else>Nenhum resultado encontrado.</p>
</template>

<script>
export default {
  props: {
    operadoras: Array,
  },
};
</script>