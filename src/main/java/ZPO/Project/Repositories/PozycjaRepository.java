package ZPO.Project.Repositories;

import ZPO.Project.Entities.Pozycja;
import ZPO.Project.Entities.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PozycjaRepository extends JpaRepository<Pozycja, Long> {
}

