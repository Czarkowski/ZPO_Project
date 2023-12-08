package ZPO.Project.Repositories;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Platnosc;
import ZPO.Project.Entities.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatnoscRepository extends JpaRepository<Platnosc, Long> {
    Platnosc findPlatnoscByPayPalId(String payPalId);
    boolean existsByZamowienie(Zamowienie zamowienie);
    Platnosc findByZamowienie(Zamowienie zamowienie);
    Platnosc findByZamowienie_Id(Long zamowienieId);
}
