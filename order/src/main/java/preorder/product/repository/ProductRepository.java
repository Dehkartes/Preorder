package preorder.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import preorder.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
