package med.voll.api.domain.medicos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Size(min = 6)
        String crm,
        @NotNull
        Especialidade especialidade,
        @Valid
        @NotNull
        DadosEndereco endereco) {
}
