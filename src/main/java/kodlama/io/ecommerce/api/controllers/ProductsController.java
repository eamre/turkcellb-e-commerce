package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.ProductService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateProductRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateProductRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam(defaultValue = "true")boolean showInactive){
        return productService.getAll(showInactive);
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@RequestBody CreateProductRequest request){
        return productService.add(request);
    }

    @PutMapping ("/{id}")
    public UpdateProductResponse update(@PathVariable int id, @RequestBody UpdateProductRequest request){
        return productService.update(id,request);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        productService.delete(id);
    }

    @PutMapping ("/status/{id}")
    public void changeProductStatus(@PathVariable int id, @RequestParam boolean isActive){
        productService.changeProductStatus(id,isActive);
    }
}
