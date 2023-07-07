package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.ShippingService;
import kodlama.io.ecommerce.business.dto.requests.create.CreateShippingRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateShippingRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetShippingResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateShippingResponse;
import kodlama.io.ecommerce.entities.concretes.Shipping;
import kodlama.io.ecommerce.repository.abstracts.ShippingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ShippingManager implements ShippingService {

    private final ShippingRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllShippingResponse> getAll() {
        List<Shipping> shippings = repository.findAll();
        List<GetAllShippingResponse> responses = shippings
                .stream()
                .map(shipping -> mapper.map(shipping, GetAllShippingResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetShippingResponse getById(int id) {
        Shipping shipping = repository.findById(id).orElseThrow();
        GetShippingResponse response = mapper.map(shipping,GetShippingResponse.class);

        return response;
    }

    @Override
    public CreateShippingResponse add(CreateShippingRequest request) {
        Shipping shipping = mapper.map(request, Shipping.class);
        shipping.setShippingDate(LocalDate.now());
        shipping.setShippingCode(UUID.randomUUID().toString());
        shipping.setId(0);

        repository.save(shipping);

        CreateShippingResponse response = mapper.map(shipping, CreateShippingResponse.class);
        return response;
    }

    @Override
    public UpdateShippingResponse update(int id, UpdateShippingRequest request) {
        Shipping shipping = mapper.map(request, Shipping.class);

        shipping.setId(id);

        repository.save(shipping);

        UpdateShippingResponse response = mapper.map(shipping, UpdateShippingResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
