package diop.lucien.orderservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor @Builder @Getter @Setter @ToString
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
