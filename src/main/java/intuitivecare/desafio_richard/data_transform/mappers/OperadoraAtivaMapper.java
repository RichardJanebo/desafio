package intuitivecare.desafio_richard.data_transform.mappers;

import intuitivecare.desafio_richard.data_transform.domain.OperadoraAtiva;
import intuitivecare.desafio_richard.data_transform.dto.OperadoraAtivaDTO;

import org.springframework.stereotype.Component;

@Component
public class OperadoraAtivaMapper {

    public OperadoraAtivaDTO toDTO(OperadoraAtiva operadora) {
        return new OperadoraAtivaDTO(
                operadora.getRegistroANS(),
                operadora.getCnpj(),
                operadora.getRazaoSocial(),
                operadora.getNomeFantasia(),
                operadora.getModalidade(),
                operadora.getCidade(),
                operadora.getUf(),
                operadora.getTelefone(),
                operadora.getEnderecoEletronico(),
                operadora.getDataRegistroANS()
        );
    }
}
