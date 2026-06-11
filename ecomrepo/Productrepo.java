package fellow.dev.demo.ecomrepo;

import fellow.dev.demo.ecomentity.Productentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepo extends JpaRepository<Productentity, Integer> {

}
