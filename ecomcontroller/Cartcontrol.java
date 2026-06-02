package fellow.dev.demo.ecomcontroller;

import fellow.dev.demo.ecomentity.Cartentity;
import fellow.dev.demo.ecomentity.Cartresponse;
import fellow.dev.demo.ecomservice.Cartservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class Cartcontrol {

    @Autowired
    private Cartservice cartser;


    @PostMapping("/add")
    public ResponseEntity<Cartentity> add(@RequestBody Cartentity cart){
        return new ResponseEntity<>(cartser.item(cart.getProductId(),cart.getQty()),HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<?> qual(){
        return new ResponseEntity<>(cartser.itemqual(),HttpStatus.OK);
    }

    @GetMapping("/items")
    public List<Cartresponse> getCartItems() {
        return cartser.getCartItems();
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable int id) {
        cartser.removeItem(id);
    }

}