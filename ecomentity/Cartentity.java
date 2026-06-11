package fellow.dev.demo.ecomentity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cartitem")
public class Cartentity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private int productId;

        private int qty;
}
