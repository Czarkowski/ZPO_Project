package ZPO.Project.Services;

import ZPO.Project.Entities.Oferta;
import ZPO.Project.Repositories.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaService {
    private final OfertaRepository ofertaRepository;

    @Autowired
    public OfertaService(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    public List<Oferta> getAllOferty() {
        return ofertaRepository.findAll();
    }

    // Dodaj inne metody serwisu, jeśli są potrzebne
}
