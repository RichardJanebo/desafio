<template>
  <div id="app">
    <TopButtons @top10-trimestre="getTop10DespesasTrimestre" @top10-ano="getTop10DespesasAno" />

    <h1>Busca de Operadoras de Saúde</h1>
    <SearchBar @search="searchOperadoras" />
    <TableComponent :operadoras="operadoras" />

    <h2>Processos</h2>
    <ButtonGroup
      :webScrapingFinished="webScrapingFinished"
      :transformDataFinished="transformDataFinished"
      :isLoadingWebScraping="isLoadingWebScraping"
      :isLoadingTransformData="isLoadingTransformData"
      :isLoadingDataBaseTest="isLoadingDataBaseTest"
      @web-scraping="initWebScraping"
      @transform-data="initTransformData"
      @data-base-test="initDataBaseTest"
    />
  </div>
</template>

<script>
import SearchBar from "@/components/SearchBar.vue";
import TableComponent from "@/components/TableComponent.vue";
import ButtonGroup from "@/components/ButtonGroup.vue";
import TopButtons from "@/components/TopButtons.vue";

export default {
  components: {
    SearchBar,
    TableComponent,
    ButtonGroup,
    TopButtons,
  },
  data() {
    return {
      operadoras: [],
      webScrapingFinished: false,
      transformDataFinished: false,
      isLoadingWebScraping: false,
      isLoadingTransformData: false,
      isLoadingDataBaseTest: false,
    };
  },
  methods: {
    async searchOperadoras(query) {
      try {
        const url = new URL("http://localhost:8080/api/v1/operadoras-ativas/search");
        const params = new URLSearchParams(query);
        url.search = params.toString();
        const response = await fetch(url.href);
        this.operadoras = await response.json();
      } catch (error) {
        console.error("Erro ao buscar operadoras:", error);
      }
    },

    async initWebScraping() {
      try {
        this.isLoadingWebScraping = true;
        await fetch("http://localhost:8080/webScrapping");
        this.webScrapingFinished = true;
      } catch (error) {
        console.error("Erro no web scraping:", error);
      } finally {
        this.isLoadingWebScraping = false;
      }
    },

    async initTransformData() {
      try {
        this.isLoadingTransformData = true;
        await fetch("http://localhost:8080/transformData");
        this.transformDataFinished = true;
      } catch (error) {
        console.error("Erro na transformação de dados:", error);
      } finally {
        this.isLoadingTransformData = false;
      }
    },

    async initDataBaseTest() {
      try {
        this.isLoadingDataBaseTest = true;
        console.log("Iniciando Teste de Banco de Dados...");
        await fetch("http://localhost:8080/dataBaseTest");
        console.log("Teste concluído!");
      } catch (error) {
        console.error("Erro no teste de banco de dados:", error);
      } finally {
        this.isLoadingDataBaseTest = false;
      }
    },

    async getTop10DespesasTrimestre() {
      try {
        const response = await fetch("http://localhost:8080/api/v1/operadoras-ativas/top10/trimestre");
        this.operadoras = await response.json();
      } catch (error) {
        console.error("Erro ao obter top 10 despesas trimestrais:", error);
      }
    },

    async getTop10DespesasAno() {
      try {
        const response = await fetch("http://localhost:8080/api/v1/operadoras-ativas/top10/ano");
        this.operadoras = await response.json();
      } catch (error) {
        console.error("Erro ao obter top 10 despesas anuais:", error);
      }
    },
  },
};
</script>
