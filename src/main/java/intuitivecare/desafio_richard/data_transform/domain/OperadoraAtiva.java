package intuitivecare.desafio_richard.data_transform.domain;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "operadoras_ativas")
public class OperadoraAtiva {

    @Id
    @Column(name = "Registro_ANS", length = 500, nullable = false)
    private String registroANS;

    @Column(name = "CNPJ", length = 500, nullable = false)
    private String cnpj;

    @Column(name = "Razao_Social", length = 500, nullable = false)
    private String razaoSocial;

    @Column(name = "Nome_Fantasia", length = 500, nullable = false)
    private String nomeFantasia;

    @Column(name = "Modalidade", length = 500, nullable = false)
    private String modalidade;

    @Column(name = "Logradouro", length = 500)
    private String logradouro;

    @Column(name = "Numero", length = 500)
    private String numero;

    @Column(name = "Complemento", length = 500)
    private String complemento;

    @Column(name = "Bairro", length = 500)
    private String bairro;

    @Column(name = "Cidade", length = 500, nullable = false)
    private String cidade;

    @Column(name = "UF", length = 500, nullable = false)
    private String uf;

    @Column(name = "CEP", length = 500)
    private String cep;

    @Column(name = "DDD", length = 500)
    private String ddd;

    @Column(name = "Telefone", length = 500)
    private String telefone;

    @Column(name = "Fax", length = 500)
    private String fax;

    @Column(name = "Endereco_eletronico", length = 500)
    private String enderecoEletronico;

    @Column(name = "Representante", length = 500)
    private String representante;

    @Column(name = "Cargo_Representante", length = 500)
    private String cargoRepresentante;

    @Column(name = "Regiao_de_Comercializacao", length = 500)
    private String regiaoDeComercializacao;

    @Column(name = "Data_Registro_ANS", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataRegistroANS;

    // Construtor padrão
    public OperadoraAtiva() {}

    // Getters e Setters
    public String getRegistroANS() { return registroANS; }
    public void setRegistroANS(String registroANS) { this.registroANS = registroANS; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getDdd() { return ddd; }
    public void setDdd(String ddd) { this.ddd = ddd; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public String getEnderecoEletronico() { return enderecoEletronico; }
    public void setEnderecoEletronico(String enderecoEletronico) { this.enderecoEletronico = enderecoEletronico; }

    public String getRepresentante() { return representante; }
    public void setRepresentante(String representante) { this.representante = representante; }

    public String getCargoRepresentante() { return cargoRepresentante; }
    public void setCargoRepresentante(String cargoRepresentante) { this.cargoRepresentante = cargoRepresentante; }

    public String getRegiaoDeComercializacao() { return regiaoDeComercializacao; }
    public void setRegiaoDeComercializacao(String regiaoDeComercializacao) { this.regiaoDeComercializacao = regiaoDeComercializacao; }

    public Date getDataRegistroANS() { return dataRegistroANS; }
    public void setDataRegistroANS(Date dataRegistroANS) { this.dataRegistroANS = dataRegistroANS; }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperadoraAtiva that = (OperadoraAtiva) o;
        return Objects.equals(registroANS, that.registroANS);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registroANS);
    }

    // toString
    @Override
    public String toString() {
        return "OperadoraAtiva{" +
                "registroANS='" + registroANS + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", dataRegistroANS=" + dataRegistroANS +
                '}';
    }
}