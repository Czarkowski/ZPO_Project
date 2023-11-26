package ZPO.Project.Repositories;

import ZPO.Project.Entities.Odpowiedz;
import ZPO.Project.Entities.Pytanie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OdpowiedzRepository extends JpaRepository<Odpowiedz, Long> {
    List<Odpowiedz> findByPytanieIdOrderByDataUtworzeniaAsc(Long questionId);
}

