package med.voll.api.domain.pacientes;

import med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(Long id, String nome, String telefone,DadosEndereco endereco) {
}
