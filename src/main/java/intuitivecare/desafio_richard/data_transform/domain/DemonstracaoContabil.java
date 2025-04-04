package intuitivecare.desafio_richard.data_transform.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "demonstracoes_contabeis")
public class DemonstracaoContabil {

    @Id
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "REG_ANS")
    private String regAns;

    @Column(name = "CD_CONTA_CONTABIL")
    private String cdContaContabil;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VL_SALDO_INICIAL")
    private BigDecimal vlSaldoInicial;

    @Column(name = "VL_SALDO_FINAL")
    private BigDecimal vlSaldoFinal;

    public DemonstracaoContabil() {
    }

    public DemonstracaoContabil(Date data, String regAns, String cdContaContabil, String descricao, BigDecimal vlSaldoInicial, BigDecimal vlSaldoFinal) {
        this.data = data;
        this.regAns = regAns;
        this.cdContaContabil = cdContaContabil;
        this.descricao = descricao;
        this.vlSaldoInicial = vlSaldoInicial;
        this.vlSaldoFinal = vlSaldoFinal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getRegAns() {
        return regAns;
    }

    public void setRegAns(String regAns) {
        this.regAns = regAns;
    }

    public String getCdContaContabil() {
        return cdContaContabil;
    }

    public void setCdContaContabil(String cdContaContabil) {
        this.cdContaContabil = cdContaContabil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getVlSaldoInicial() {
        return vlSaldoInicial;
    }

    public void setVlSaldoInicial(BigDecimal vlSaldoInicial) {
        this.vlSaldoInicial = vlSaldoInicial;
    }

    public BigDecimal getVlSaldoFinal() {
        return vlSaldoFinal;
    }

    public void setVlSaldoFinal(BigDecimal vlSaldoFinal) {
        this.vlSaldoFinal = vlSaldoFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemonstracaoContabil that = (DemonstracaoContabil) o;
        return Objects.equals(data, that.data) && Objects.equals(regAns, that.regAns) && Objects.equals(cdContaContabil, that.cdContaContabil) && Objects.equals(descricao, that.descricao) && Objects.equals(vlSaldoInicial, that.vlSaldoInicial) && Objects.equals(vlSaldoFinal, that.vlSaldoFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, regAns, cdContaContabil, descricao, vlSaldoInicial, vlSaldoFinal);
    }

    @Override
    public String toString() {
        return "DemonstracaoContabil{" +
                "data=" + data +
                ", regAns='" + regAns + '\'' +
                ", cdContaContabil='" + cdContaContabil + '\'' +
                ", descricao='" + descricao + '\'' +
                ", vlSaldoInicial=" + vlSaldoInicial +
                ", vlSaldoFinal=" + vlSaldoFinal +
                '}';
    }
}