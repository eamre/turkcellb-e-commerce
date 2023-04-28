package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetCategoryResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateProductResponse;
import kodlama.io.ecommerce.business.rules.ProductBusinessRules;
import kodlama.io.ecommerce.entities.concretes.Category;
import kodlama.io.ecommerce.entities.concretes.Product;
import kodlama.io.ecommerce.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final CategoryManager categoryService;
    private final ModelMapper mapper;
    private final ProductBusinessRules rules;
//    @PersistenceContext
//    private EntityManager entityManager;

    @Override
    public List<GetAllProductsResponse> getAll(boolean showInactive) {
        List<Product> products = repository.findAll();
        List<GetAllProductsResponse> responses = products.stream()
                .filter(product -> showInactive || product.isActive())
                .map(product -> {
                    GetAllProductsResponse response = mapper.map(product, GetAllProductsResponse.class);
                    response.setCategoryNames(product.getCategories().stream()
                            .map(category -> category.getName())
                            .toList());
                    return response;
                }).toList();
        return responses;
    }

    @Override
    public GetProductResponse getById(int id) {
        rules.checkIfProductExists(id);
        Product product = repository.findById(id).orElseThrow();
        GetProductResponse response = mapper.map(product, GetProductResponse.class);
        response.setCategoryNames(product.getCategories().stream().map(category -> category.getName()).toList());
        return response;
    }
    //entityManager.find(Category.class, categoryId);
    //entityManager.merge(category);
    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(0);

        List<String> categoryNames = setCategoryForProduct(request.getCategoryIds(), product);

        repository.save(product);

        CreateProductResponse response = mapper.map(product,CreateProductResponse.class);
        response.setCategoryNames(categoryNames);

        return response;
    }

    public UpdateProductResponse update(int id, UpdateProductRequest request) {
        rules.checkIfProductExists(id);
        Product product = mapper.map(request, Product.class);
        product.setId(id);
//        List<Integer> categoryIdl = request.getCategoryIds();
//        setCategoryForProduct(categoryIdl, product);
        List<String> categoryNames = setCategoryForProduct(request.getCategoryIds(), product);

        repository.save(product);

        UpdateProductResponse response = mapper.map(product,UpdateProductResponse.class);
        response.setCategoryNames(categoryNames);

        return response;
    }
    
    @Override
    public void delete(int id) {
        rules.checkIfProductExists(id);
        repository.deleteById(id);
    }

    public void changeProductStatus(int productId, boolean isActive) {
        Product product = repository.findById(productId).orElseThrow();
        product.setActive(isActive);
        repository.save(product);
    }

    public void processSaleProduct(CreateSaleRequest request){
        Product product = repository.findById(request.getProductId()).orElseThrow();

        product.setQuantity(product.getQuantity()- request.getQuantity());
        repository.save(product);
    }

    private List<String> setCategoryForProduct(List<Integer> categoryIdl, Product product) {
        List<String> categoryNames = new ArrayList<>();
        Set<Category> categories = new HashSet<>();
        for (int categoryId : categoryIdl) {
            GetCategoryResponse categoryResponse = categoryService.getById(categoryId);
            Category category = mapper.map(categoryResponse, Category.class);
            categories.add(category);
            categoryNames.add(category.getName());
        }
        product.setCategories(categories);
        return categoryNames;
//        List<Integer> categoryIds = categories
//                .stream()
//                .map(category -> category.getId())
//                .toList();
//        return categoryIds;
    }

    //! Business rules
    private void validateProduct(Product product) {
       // checkIfUnitPriceValid(product);
        checkIfQuantityValid(product);
        checkIfDescriptionLengthValid(product);
    }

    private void checkIfUnitPriceValid(CreateProductRequest request) {
        if (request.getPrice() <= 0)
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