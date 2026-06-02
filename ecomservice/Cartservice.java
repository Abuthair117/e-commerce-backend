package fellow.dev.demo.ecomservice;


import fellow.dev.demo.ecomentity.Cartentity;
import fellow.dev.demo.ecomentity.Cartresponse;
import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Cartrepo;
import fellow.dev.demo.ecomrepo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cartservice {

    @Autowired
    private Cartrepo cartrepo;

    @Autowired
    private Productrepo productrepo;

    public Cartentity item(int productId, int qty) {

        Cartentity item = cartrepo.findByproductId(productId).orElse(null);

        if (item != null) {
            item.setQty(item.getQty() + qty);
            return cartrepo.save(item);
        }

        Cartentity newItem = new Cartentity();
        newItem.setProductId(productId);
        newItem.setQty(qty);

        return cartrepo.save(newItem);
    }

    public int itemqual(){

        return cartrepo.getTotalQty();
    }

    public List<Cartresponse> getCartItems() {

        return cartrepo.findAll().stream().map(item -> {

            Productentity product = productrepo.findById(item.getProductId()).orElse(null);

            Cartresponse res = new Cartresponse();

            res.setId((long) item.getId());
            res.setProductId(item.getProductId());
            res.setQty(item.getQty());

            if (product != null) {
                res.setProductName(product.getName());
                res.setPrice(product.getPrice());
            }

            return res;
        }).toList();
    }


    public void removeItem(int id) {
            cartrepo.deleteById(id);
    }



}