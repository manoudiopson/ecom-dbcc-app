package diop.lucien.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @NoArgsConstructor
@AllArgsConstructor @Builder @Getter @Setter @ToString
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
}
