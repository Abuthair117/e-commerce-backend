package fellow.dev.demo.ecomservice;

import fellow.dev.demo.ecomentity.Cartentity;
import fellow.dev.demo.ecomentity.Orderentity;
import fellow.dev.demo.ecomentity.Productentity;
import fellow.dev.demo.ecomrepo.Cartrepo;
import fellow.dev.demo.ecomrepo.Orderrepo;
import fellow.dev.demo.ecomrepo.Productrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Orderservice {

    @Autowired
    private Orderrepo orrepo;

    @Autowired
    private Cartrepo cartrepo;

    @Autowired
    private Productrepo productrepo;

    public Orderentity placeOrder(String address) {

        List<Cartentity> cartItems = cartrepo.findAll();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;

        for (Cartentity item : cartItems) {

            Productentity product = productrepo.findById(item.getProductId())
                    .orElse(null);

            if (product != null) {
                total += product.getPrice() * item.getQty();
            }
        }

        Orderentity order = new Orderentity();
        order.setTotalAmount(total);
        order.setAddress(address);

        Orderentity saved = orrepo.save(order);

        cartrepo.deleteAll();

        return saved;
    }
}