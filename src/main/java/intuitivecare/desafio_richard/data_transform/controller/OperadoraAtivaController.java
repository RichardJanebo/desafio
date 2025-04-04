package intuitivecare.desafio_richard.data_transform.controller;

import intuitivecare.desafio_richard.data_transform.dto.OperadoraAtivaDTO;
import intuitivecare.desafio_richard.data_transform.service.OperadoraAtivaService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/operadoras-ativas")
public class OperadoraAtivaController {

    private final OperadoraAtivaService service;
    public OperadoraAtivaController(OperadoraAtivaService service) {
        this.service = service;
    }

    @GetMapping("/top10/trimestre")
    public List<OperadoraAtivaDTO> getTop10DespesasTrimestre() {
        return service.getTop10DespesasTrimestre();
    }

    @GetMapping("/top10/ano")
    public List<OperadoraAtivaDTO> getTop10DespesasAno() {
        return service.getTop10DespesasAno();
    }

    @GetMapping("/search")
    public List<OperadoraAtivaDTO> searchOperadoras(
            @RequestParam(required = false) String registroANS,
            @RequestParam(required = false) String cnpj,
            @RequestParam(required = false) String razaoSocial,
            @RequestParam(required = false) String nomeFantasia,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String uf
    ) {
        return service.searchOperadoras(registroANS, cnpj, razaoSocial, nomeFantasia, cidade, uf);
    }
}
