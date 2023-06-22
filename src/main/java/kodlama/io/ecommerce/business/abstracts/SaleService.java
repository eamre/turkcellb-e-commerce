package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequestTwo;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponseTwo;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;

import java.util.List;

public interface SaleService {
    List<GetAllSalesResponse> getAll();
    GetSaleResponse getById(int id);
    CreateSaleResponseTwo createSale(CreateSaleRequestTwo request);
    UpdateSaleResponse update(int id, UpdateSaleRequest request);
    void delete(int id);
}
//   CreateSaleResponse add(CreateSaleRequest request);