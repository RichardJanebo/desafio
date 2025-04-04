package intuitivecare.desafio_richard.data_transform.service;

import intuitivecare.desafio_richard.data_transform.domain.OperadoraAtiva;
import intuitivecare.desafio_richard.data_transform.dto.OperadoraAtivaDTO;
import intuitivecare.desafio_richard.data_transform.mappers.OperadoraAtivaMapper;
import intuitivecare.desafio_richard.data_transform.repository.OperadoraAtivaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperadoraAtivaService {

    private final OperadoraAtivaRepository repository;
    private final OperadoraAtivaMapper mapper;

    public OperadoraAtivaService(OperadoraAtivaRepository repository, OperadoraAtivaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OperadoraAtivaDTO> getTop10DespesasTrimestre() {
        return repository.findTop10ByDespesas()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<OperadoraAtivaDTO> getTop10DespesasAno() {
        return repository.findTop10ByDespesasAno()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<OperadoraAtivaDTO> searchOperadoras(
            String registroANS, String cnpj, String razaoSocial,
            String nomeFantasia, String cidade, String uf
    ) {
        return repository.searchOperadoras(registroANS, cnpj, razaoSocial, nomeFantasia, cidade, uf)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    private Date calcularDataMinima(int meses) {
        LocalDate dataMinima = LocalDate.now().minusMonths(meses);
        return Date.from(dataMinima.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
