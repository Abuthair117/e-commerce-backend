package fellow.dev.demo.ecomservice;

import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Productservice {

    @Autowired
    private Productrepo repo;

    public List<Productentity> getAllProducts() {
        return repo.findAll();
    }
}