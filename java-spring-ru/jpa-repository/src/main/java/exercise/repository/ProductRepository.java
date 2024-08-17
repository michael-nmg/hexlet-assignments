package exercise.repository;

import java.util.List;
import exercise.model.Product;

import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // @Query("SELECT e FROM Product e WHERE e.price BETWEEN :min AND :max ORDER BY e.price ASC")
    // public List<Product> findByPriceRange(
        // @Param("min") Integer min,
        // @Param("max") Integer max);
    public List<Product> findByPriceBetween(Integer min, Integer max, Sort sort);
}
