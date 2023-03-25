//package kodlama.io.ecommerce.repository.concretes;
//
//import kodlama.io.ecommerce.entities.concretes.Product;
//import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//@Repository
//public class InMemoryProductRepository implements ProductRepository {
//
//    List<Product> products;
//
//    public InMemoryProductRepository() {
//        products = new ArrayList<>();
//        products.add(new Product(1,"Kalem",5,15,"Kurşun Kalem"));
//        products.add(new Product(2,"Telefon",50,8500,"Samsung S23 5G"));
//        products.add(new Product(3,"Klavye",60,150,"Mekanik Klavye"));
//    }
//
//    @Override
//    public List<Product> getAll() {
//        return products;
//    }
//
//    @Override
//    public Product getById(int id) {
//        for (Product product : products) {
//            if (product.getId()==id){
//                return product;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Product add(Product product) {
//        products.add(product);
//        return product;
//    }
//
//    @Override
//    public void delete(int id) {
//        products.removeIf(p -> p.getId()==id);
//    }
//
//    @Override
//    public Product update(int id,Product product) {
//        for (Product pr : products) {
//            if (pr.getId()==id){
//                pr.setName(product.getName());
//                pr.setDescription(product.getDescription());
//                pr.setPrice(product.getPrice());
//                pr.setQuantity(product.getQuantity());
//                return pr;
//            }
//        }
//        return null;
//    }
//}
