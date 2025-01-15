package com.codingshuttle.jpaTutorial;

import com.codingshuttle.jpaTutorial.entities.Product;
import com.codingshuttle.jpaTutorial.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpatutorialApplicationTests {
	@Autowired
	ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testRepository(){
		Product productEntity = Product.builder()
				.sku("nest")
				.title("Nestle Calende ")
				.price(BigDecimal.valueOf(23.45))
				.quantity(12)
				.build();

		Product savedProduct = productRepository.save(productEntity);
		System.out.println(savedProduct);

	}

	@Test
	void getRepository(){
		//List<Product> entities = productRepository.findByCreatedAtAfter(
		//		LocalDateTime.of(2024,1,1,1,0,0,0));
		//List<Product> entities = productRepository.findByQuantityAndPrice(12,BigDecimal.valueOf(23.45));
		//System.out.println(entities);
	}

	@Test
	void getSingleFromRepository(){
		Optional<Product> product = productRepository.findByQuantityAndPrice(12,BigDecimal.valueOf(23.45));
		product.ifPresent(System.out::println);
	}
}
