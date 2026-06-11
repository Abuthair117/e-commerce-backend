package fellow.dev.demo.ecomrepo;


import fellow.dev.demo.ecomentity.Cartentity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cartrepo extends JpaRepository<Cartentity,Integer> {
    Optional<Cartentity>findByproductId(int productId);
    @Query(value = "SELECT COALESCE(SUM(qty), 0) FROM cartitem", nativeQuery = true)
    Integer getTotalQty();
}
