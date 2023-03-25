package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductManager implements ProductService {
    ProductRepository productRepository;

    public ProductManager(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product add(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(int id,Product product) {
        validateProduct(product);
        product.setId(id);
        return productRepository.save(product);

    }


    //! Business rules
    private void validateProduct(Product product) {
        checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfUnitPriceValid(Product product) {
        if (product.getPrice() <= 0)
            throw new IllegalArgumentException("Price cannot be less than or equal to zero.");
    }

    private void checkIfQuantityValid(Product product) {
        if (product.getQuantity() < 0) throw new IllegalArgumentException("Quantity cannot be less than zero.");
    }

    private void checkIfDescriptionLengthValid(Product product) {
        if (product.getDescription().length() < 10 || product.getDescription().length() > 50)
            throw new IllegalArgumentException("Description length must be between 10 and 50 characters.");
    }
}
//if (product.getPrice()<=0){
//        throw new RuntimeException("ürünün fiyatı sıfırdan küçük olamaz ");
//        }
//        if(product.getQuantity()<0){
//        throw new RuntimeException("ürünün miktarı sıfırdan küçük olamaz ");
//        }
//        if(!(product.getDescription().length() >= 10 && product.getDescription().length() <= 50)){
//        throw new RuntimeException("ürünün açıklama kısmı 10 ile 50 karakter arasında olmalı ");
//        }