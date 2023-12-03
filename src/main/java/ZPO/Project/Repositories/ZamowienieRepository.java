package ZPO.Project.Repositories;

import ZPO.Project.Entities.Pytanie;
import ZPO.Project.Entities.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZamowienieRepository extends JpaRepository<Zamowienie, Long> {

}

