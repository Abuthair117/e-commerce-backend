package fellow.dev.demo.ecomrepo;

import fellow.dev.demo.ecomentity.Orderentity;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Orderrepo extends JpaRepository<Orderentity, Integer> {
}
