package fellow.dev.demo.ecomentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Productentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private String image;

    public Productentity(String name, double price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }


}
