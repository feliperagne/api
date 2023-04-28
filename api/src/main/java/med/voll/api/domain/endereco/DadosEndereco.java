package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        @Size(min = 8)
        String cep,
        @NotBlank
        String cidade ,
        @NotBlank
        String uf,
        @NotBlank
        String bairro,
        String complemento ,
        String numero) {
}
