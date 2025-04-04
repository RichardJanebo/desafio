package intuitivecare.desafio_richard.data_transform.dto;

import java.util.Date;

public record OperadoraAtivaDTO(
        String registroANS,
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String modalidade,
        String cidade,
        String uf,
        String telefone,
        String enderecoEletronico,
        Date dataRegistroANS
) {
}
