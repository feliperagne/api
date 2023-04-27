package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.pacientes.DadosCadastroPacientes;
import med.voll.api.pacientes.DadosListagemPaciente;
import med.voll.api.pacientes.Paciente;
import med.voll.api.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.ClientInfoStatus;

@RestController
@RequestMapping("pacientes")
public class PacientesController {
    @Autowired
    PacienteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPacientes dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 3)Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

}
