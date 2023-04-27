package med.voll.api.medicos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
/* USANDO ESSA INTERFACE , O SPRING JÁ FAZ A CLASSE DAO DE MÉDICO HERDANDO TODOS OS MÉTODOS DA ENTIDADE AUTOMATICAMENTE */
}
