package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPacientes(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 9, max = 11)
        String telefone,
        @NotBlank
        @Size(min = 8)
        String cpf,
        @Valid
        @NotNull
         DadosEndereco endereco) {
}
