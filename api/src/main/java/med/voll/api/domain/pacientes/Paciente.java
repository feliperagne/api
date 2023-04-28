package med.voll.api.domain.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;


    public Paciente (DadosCadastroPacientes dados){
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.endereco= new Endereco(dados.endereco());
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null)
            this.nome = dados.nome();

        if (dados.telefone() != null)
            this.telefone = dados.telefone();

        if (dados.endereco() != null)
            endereco.atualizarInformacoes(dados.endereco());
    }
}
