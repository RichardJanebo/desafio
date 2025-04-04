<template>
  <div>
    <button @click="$emit('web-scraping')" :disabled="isLoadingWebScraping">
      <span v-if="isLoadingWebScraping">⏳ Carregando...</span>
      <span v-else>Iniciar Web Scraping</span>
    </button>

    <button v-if="webScrapingFinished" @click="$emit('transform-data')" :disabled="isLoadingTransformData">
      <span v-if="isLoadingTransformData">⏳ Processando...</span>
      <span v-else>Iniciar Transformação de Dados</span>
    </button>

    <button v-if="transformDataFinished" @click="$emit('data-base-test')" :disabled="isLoadingDataBaseTest">
      <span v-if="isLoadingDataBaseTest">⏳ Testando...</span>
      <span v-else>Iniciar Teste de Banco de Dados</span>
    </button>
  </div>
</template>

<script>
export default {
  props: {
    webScrapingFinished: Boolean,
    transformDataFinished: Boolean,
    isLoadingWebScraping: Boolean,
    isLoadingTransformData: Boolean,
    isLoadingDataBaseTest: Boolean,
  },
};
</script>
