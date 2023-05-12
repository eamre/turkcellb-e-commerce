package kodlama.io.ecommerce.business.abstracts;

import kodlama.io.ecommerce.business.dto.requests.create.CreateShippingRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateShippingRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateShippingResponse;

import java.util.List;

public interface ShippingService {
    List<GetAllShippingResponse> getAll();
    GetShippingResponse getById(int id);
    CreateShippingResponse add(CreateShippingRequest request);
    UpdateShippingResponse update(int id, UpdateShippingRequest request);
    void delete(int id);
}
