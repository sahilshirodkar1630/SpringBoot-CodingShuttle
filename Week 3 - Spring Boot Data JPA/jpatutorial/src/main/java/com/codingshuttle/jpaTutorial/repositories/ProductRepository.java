package com.codingshuttle.jpaTutorial.repositories;

import com.codingshuttle.jpaTutorial.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{
    List<Product> findBy(Sort sort);
    List<Product> findByOrderByPrice();

    List<Product> findByCreatedAtAfter(LocalDateTime createdAfter);

    List<Product> findByQuantityAndPrice(int i, BigDecimal valueOf);
    //@Query("select e from Product e where e.quantity=?1 and e.price=?2")
  //  Optional<Product> findByQuantityAndPrice(int quatity, BigDecimal price);

    Page<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
