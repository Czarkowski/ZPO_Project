package ZPO.Project.Repositories;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Pytanie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PytanieRepository extends JpaRepository<Pytanie, Long> {
}

