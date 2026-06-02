package fellow.dev.demo.ecomcontroller;

import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Productrepo;
import fellow.dev.demo.ecomservice.Productservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class Productcontrol {

    @Autowired
    private Productservice service;

    @Autowired
    private Productrepo productrepo;

    @GetMapping("/{id}")
    public Productentity getById(@PathVariable int id) {
        return productrepo.findById(id).orElse(null);
    }

    @GetMapping("/products")
    public List<Productentity> getAll() {
        return service.getAllProducts();
    }
}

