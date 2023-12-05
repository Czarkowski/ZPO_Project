package ZPO.Project.Services;

import ZPO.Project.Entities.Pozycja;
import ZPO.Project.Entities.Zamowienie;
import ZPO.Project.Models.OrderModel;
import ZPO.Project.Models.Product;
import ZPO.Project.Repositories.CenaRepository;
import ZPO.Project.Repositories.OfertaRepository;
import ZPO.Project.Repositories.PozycjaRepository;
import ZPO.Project.Repositories.ZamowienieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZamowienieService {

    private final CenaRepository cenaRepository;
    private final OfertaRepository ofertaRepository;
    private final PozycjaRepository pozycjaRepository;
    private final ZamowienieRepository zamowienieRepository;

    public ZamowienieService(CenaRepository cenaRepository, OfertaRepository ofertaRepository, PozycjaRepository pozycjaRepository, ZamowienieRepository zamowienieRepository) {
        this.cenaRepository = cenaRepository;
        this.ofertaRepository = ofertaRepository;
        this.pozycjaRepository = pozycjaRepository;
        this.zamowienieRepository = zamowienieRepository;
    }

    public Zamowienie CreateZamowienie(OrderModel orderModel, List<Product> productList){
        var zam = new Zamowienie(orderModel);
        List<Pozycja> pozList = new ArrayList<Pozycja>();
        productList.forEach(product -> {
            var poz = new Pozycja(product, zam);
            pozList.add(poz);
            System.out.println(poz);
        });
        zam.setPozycje(pozList);
        zamowienieRepository.save(zam);
        return zam;
    }

    public Zamowienie GetZamowienieById(Long id){
        return zamowienieRepository.getReferenceById(id);
    }
}