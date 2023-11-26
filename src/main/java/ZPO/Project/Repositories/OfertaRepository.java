package ZPO.Project.Repositories;

import ZPO.Project.Entities.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    // Dodaj dodatkowe metody, jeśli są potrzebne
}
