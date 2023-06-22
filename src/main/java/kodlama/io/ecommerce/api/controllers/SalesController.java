package kodlama.io.ecommerce.api.controllers;

import kodlama.io.ecommerce.business.abstracts.SaleService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequestTwo;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponseTwo;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/sales")
public class SalesController {
    private final SaleService saleService;

    @GetMapping
    public List<GetAllSalesResponse> getAll(){
        return saleService.getAll();
    }

    @GetMapping("/{id}")
    public GetSaleResponse getById(@PathVariable int id){
        return saleService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateSaleResponse add(@RequestBody CreateSaleRequest request){
        return saleService.add(request);
    }
    @PostMapping("/create")
    public CreateSaleResponseTwo createSale(@RequestBody CreateSaleRequestTwo request) {
        return saleService.createSale(request);
    }
    @PutMapping ("/{id}")
    public UpdateSaleResponse update(@PathVariable int id, @RequestBody UpdateSaleRequest request){
        return saleService.update(id,request);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        saleService.delete(id);
    }

}
