package diop.lucien.orderservice.repositories;

import diop.lucien.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIemRepository extends JpaRepository<ProductItem, Long> {
}
