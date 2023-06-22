package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.CartService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateCartRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetCartResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/carts")
public class CartsController {
    private final CartService service;
    @GetMapping
    public List<GetAllCartResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCartResponse getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCartResponse add(@RequestBody CreateCartRequest request){
        return service.add(request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.deleteCartItem(id);
    }
}
