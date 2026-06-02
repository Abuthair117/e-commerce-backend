package fellow.dev.demo.ecomentity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orderitem")
public class Orderentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalAmount;

    private String address;
}