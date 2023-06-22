package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.InvoiceService;
import kodlama.io.ecommerce.common.dto.CartItemResponse;
import kodlama.io.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateInvoiceResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllInvoicesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetInvoiceResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateInvoiceResponse;
import kodlama.io.ecommerce.business.rules.InvoiceBusinessRules;
import kodlama.io.ecommerce.entities.concretes.Invoice;
import kodlama.io.ecommerce.repository.abstracts.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final InvoiceBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> responses = invoices.stream()
                .map(invoice -> mapToGetAllInvoicesResponse(invoice))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapToGetInvoiceResponse(invoice);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        //invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);

        CreateInvoiceResponse response = mapper.map(invoice,CreateInvoiceResponse.class);
        return response;
    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.map(request,Invoice.class);
        invoice.setId(id);
        //invoice.setTotalPrice(getTotalPrice(invoice));

        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.map(invoice,UpdateInvoiceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private GetAllInvoicesResponse mapToGetAllInvoicesResponse(Invoice invoice) {
        GetAllInvoicesResponse response = mapper.map(invoice, GetAllInvoicesResponse.class);

        List<CartItemResponse> cartItemResponses = invoice.getCart().getCartItems()
                .stream()
                .map(cartItem -> mapper.map(cartItem, CartItemResponse.class))
                .collect(Collectors.toList());

        response.setCartItems(cartItemResponses);

        return response;
    }
    private GetInvoiceResponse mapToGetInvoiceResponse(Invoice invoice) {
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);

        List<CartItemResponse> cartItemResponses = invoice.getCart().getCartItems()
                .stream()
                .map(cartItem -> mapper.map(cartItem, CartItemResponse.class))
                .collect(Collectors.toList());

        response.setCartItems(cartItemResponses);

        return response;
    }
   // private double getTotalPrice(Invoice invoice) {
       // return invoice.getPrice() * invoice.getQuantity();
//    }

}
