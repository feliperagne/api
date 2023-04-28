package med.voll.api.domain.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
    /* USANDO ESSA INTERFACE , O SPRING JÁ FAZ A CLASSE DAO DE MÉDICO HERDANDO TODOS OS MÉTODOS DA ENTIDADE AUTOMATICAMENTE */
}
