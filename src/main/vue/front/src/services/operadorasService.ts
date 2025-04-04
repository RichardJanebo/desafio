import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

export async function searchOperadoras(
  registroANS?: string,
  cnpj?: string,
  razaoSocial?: string,
  nomeFantasia?: string,
  cidade?: string,
  uf?: string
) {
  const params = { registroANS, cnpj, razaoSocial, nomeFantasia, cidade, uf };

  try {
    const response = await api.get("/search", { params });
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar operadoras:", error);
    return [];
  }
}
