package fellow.dev.demo.ecomentity;

import lombok.Data;

@Data
public class Cartresponse {

    private Long id;
    private int productId;
    private String productName;
    private double price;
    private int qty;
}