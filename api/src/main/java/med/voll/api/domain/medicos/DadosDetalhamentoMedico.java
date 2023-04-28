package med.voll.api.domain.medicos;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome,String telefone, String cmr, Especialidade especialidade, Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico){
       this(medico.getId(),medico.getNome(),medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
