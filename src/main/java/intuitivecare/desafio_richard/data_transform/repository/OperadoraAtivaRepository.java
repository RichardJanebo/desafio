package intuitivecare.desafio_richard.data_transform.repository;

import intuitivecare.desafio_richard.data_transform.domain.OperadoraAtiva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperadoraAtivaRepository extends JpaRepository<OperadoraAtiva, String> {
    @Query(value = """
                SELECT oa.*, SUM(dc.VL_SALDO_FINAL) AS despesa_total
                FROM operadoras_ativas oa
                JOIN demonstracoes_contabeis dc ON oa.Registro_ANS = dc.REG_ANS
                WHERE dc.DATA >= CURRENT_DATE - INTERVAL '9 month'
                AND dc.DESCRICAO ILIKE '%EVENTOS%SINISTROS%CONHECIDOS%OU%AVISADOS%DE%ASSISTÊNCIA%A%SAÚDE%MEDICO%HOSPITALAR%'
                GROUP BY oa.Registro_ANS, oa.CNPJ, oa.Razao_Social, oa.Nome_Fantasia, oa.Modalidade, 
                         oa.Logradouro, oa.Numero, oa.Complemento, oa.Bairro, oa.Cidade, oa.UF, oa.CEP, 
                         oa.DDD, oa.Telefone, oa.Fax, oa.Endereco_eletronico, oa.Representante, 
                         oa.Cargo_Representante, oa.Regiao_de_Comercializacao, oa.Data_Registro_ANS
                ORDER BY despesa_total DESC
                LIMIT 10
            """, nativeQuery = true)
    List<OperadoraAtiva> findTop10ByDespesas();


    @Query("""
                SELECT oa FROM OperadoraAtiva oa
                WHERE (:registroANS IS NULL OR oa.registroANS LIKE %:registroANS%)
                AND (:cnpj IS NULL OR oa.cnpj LIKE %:cnpj%)
                AND (:razaoSocial IS NULL OR oa.razaoSocial LIKE %:razaoSocial%)
                AND (:nomeFantasia IS NULL OR oa.nomeFantasia LIKE %:nomeFantasia%)
                AND (:cidade IS NULL OR oa.cidade LIKE %:cidade%)
                AND (:uf IS NULL OR oa.uf LIKE %:uf%)
            """)
    List<OperadoraAtiva> searchOperadoras(
            @Param("registroANS") String registroANS,
            @Param("cnpj") String cnpj,
            @Param("razaoSocial") String razaoSocial,
            @Param("nomeFantasia") String nomeFantasia,
            @Param("cidade") String cidade,
            @Param("uf") String uf
    );

    @Query(value = """
            SELECT oa.*, SUM(dc.VL_SALDO_FINAL) AS despesa_total
            FROM operadoras_ativas oa
            JOIN demonstracoes_contabeis dc ON oa.Registro_ANS = dc.REG_ANS
            WHERE dc.DATA >= CURRENT_DATE - INTERVAL '12 month'
            AND dc.DESCRICAO ILIKE '%EVENTOS%SINISTROS%CONHECIDOS%OU%AVISADOS%DE%ASSISTÊNCIA%A%SAÚDE%MEDICO%HOSPITALAR%'
            GROUP BY oa.Registro_ANS, oa.CNPJ, oa.Razao_Social, oa.Nome_Fantasia, oa.Modalidade, oa.Logradouro, 
                     oa.Numero, oa.Complemento, oa.Bairro, oa.Cidade, oa.UF, oa.CEP, oa.DDD, oa.Telefone, oa.Fax, 
                     oa.Endereco_eletronico, oa.Representante, oa.Cargo_Representante, oa.Regiao_de_Comercializacao, 
                     oa.Data_Registro_ANS
            ORDER BY despesa_total DESC
            LIMIT 10;
            """, nativeQuery = true)
    List<OperadoraAtiva> findTop10ByDespesasAno();


}
