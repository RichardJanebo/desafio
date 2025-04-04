CREATE TABLE IF NOT EXISTS operadoras_ativas (
  Registro_ANS VARCHAR(500),
  CNPJ VARCHAR(500),
  Razao_Social VARCHAR(500),
  Nome_Fantasia VARCHAR(500),
  Modalidade VARCHAR(500),
  Logradouro VARCHAR(500),
  Numero VARCHAR(500),
  Complemento VARCHAR(500),
  Bairro VARCHAR(500),
  Cidade VARCHAR(500),
  UF VARCHAR(500),
  CEP VARCHAR(500),
  DDD VARCHAR(500),
  Telefone VARCHAR(500),
  Fax VARCHAR(500),
  Endereco_eletronico VARCHAR(500),
  Representante VARCHAR(500),
  Cargo_Representante VARCHAR(500),
  Regiao_de_Comercializacao VARCHAR(500),
  Data_Registro_ANS DATE
);

CREATE TABLE IF NOT EXISTS demonstracoes_contabeis (
  DATA DATE,
  REG_ANS VARCHAR(500),
  CD_CONTA_CONTABIL VARCHAR(500),
  DESCRICAO VARCHAR(500),
  VL_SALDO_INICIAL DECIMAL(18, 2),
  VL_SALDO_FINAL DECIMAL(18, 2)
);