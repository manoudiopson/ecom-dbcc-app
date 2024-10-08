package diop.lucien.inventoryservice;

import diop.lucien.inventoryservice.entities.Product;
import diop.lucien.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().id("P01").name("Computer").price(800).quantity(11).build());
            productRepository.save(Product.builder().id("P02").name("Printer").price(500).quantity(21).build());
            productRepository.save(Product.builder().id("P03").name("Smart Phone").price(300).quantity(5).build());
        };
    }
}
