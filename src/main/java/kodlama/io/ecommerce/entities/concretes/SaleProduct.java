//package kodlama.io.ecommerce.entities.concretes;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@AllArgsConstructor
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//
//public class SaleProduct {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "sale_id")
//    private Sale sale;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    private double price;
//    private int quantity;
//}
