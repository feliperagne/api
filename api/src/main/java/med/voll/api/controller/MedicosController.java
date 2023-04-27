package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;
    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){

        repository.save(new Medico(dados));
}
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 3, page = 0, sort = {"nome"}) Pageable paginacao){ //PAGEABLE É PARA PAGINAR , DEIXAR PAGINADO
        return repository.findAll(paginacao).map(DadosListagemMedico::new);//O findAll() RETORNAR TODAS AS PROPRIEDADES DA ENTIDADE. QUERO APENAS RETORNAR O QUE ESTÁ EM DADOSLISTAGEMMEDICO , E ASSIM É COMO SE DEVE FAZER PARA CONVERTER A ENTIDADE PARA O RECORD!!

    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); //VARIAVEL MEDICO VAI BUSCAR TODOS OS CADASTROS JA FEITOS NO BANCO DE DADOS PARA PEGAR O MEDICO ESPECIFICO PARA ATUALIZAR
        medico.atualizarInformacoes(dados);
    }
}
