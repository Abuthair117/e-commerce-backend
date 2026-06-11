package fellow.dev.demo.ecomcontroller;


import fellow.dev.demo.ecomentity.Orderentity;
import fellow.dev.demo.ecomentity.Orderresponse;
import fellow.dev.demo.ecomservice.Orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class Ordercontrol {

    @Autowired
    private Orderservice ordersere;

    @PostMapping("/order/place")
    public Orderentity placeOrder(@RequestBody Orderresponse request) {
        return ordersere.placeOrder(request.getAddress());
    }
}