package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medicos.DadosListagemMedico;
import med.voll.api.domain.medicos.Medico;
import med.voll.api.domain.medicos.MedicoRepository;
import med.voll.api.domain.medicos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicosController {

    @Autowired
    private MedicoRepository repository;
    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriComponentsBuilder){
        var medico  = new Medico(dados); //PEGA AS PROPRIEDADES DA ENTIDADE
        repository.save(medico); //SALVA NO BANCO DE DADOS

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //MOSTRAR O ID DO MEDICO QUE FOI CRIADO NA URL APÓS A INSERÇÃO DELE NO BANCO

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico)); //MOSTRAR OS DADOS QUE FORAM SALVOS APÓS A INSERÇÃO
}
    @GetMapping
    public ResponseEntity <Page<DadosListagemMedico>> listar(@PageableDefault(size = 3, page = 0, sort = {"nome"}) Pageable paginacao){ //PAGEABLE É PARA PAGINAR , DEIXAR PAGINADO
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);//O findAll() RETORNAR TODAS AS PROPRIEDADES DA ENTIDADE. QUERO APENAS RETORNAR O QUE ESTÁ EM DADOSLISTAGEMMEDICO , E ASSIM É COMO SE DEVE FAZER PARA CONVERTER A ENTIDADE PARA O RECORD!!
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id()); //VARIAVEL MEDICO VAI BUSCAR TODOS OS CADASTROS JA FEITOS NO BANCO DE DADOS PARA PEGAR O MEDICO ESPECIFICO PARA ATUALIZAR
        medico.atualizarInformacoes(dados);
        return ResponseEntity.ok( new DadosDetalhamentoMedico(medico));
    }
    @DeleteMapping("/{id}") // ADICIONA O ID NA URL POR PADRÃO PARA DELETAR O MEDICO DE ACORDO COM O ID
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
       var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}") // QUANDO ESCOLHER O ID PRA BUSCAR O MÉDICO , ELE VAI MOSTRAR TODOS OS DETALHES DO MEDICO QUE FOI ESCOLHIDO
    public ResponseEntity detalhar(@PathVariable Long id){
       var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
