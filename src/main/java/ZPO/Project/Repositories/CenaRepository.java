package ZPO.Project.Repositories;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenaRepository extends JpaRepository<Cena, Long> {
    List<Cena> findTop1ByOfertaOrderByDataUtworzeniaDesc(Oferta oferta);
}
