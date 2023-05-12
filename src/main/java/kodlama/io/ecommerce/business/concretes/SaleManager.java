package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.*;
import kodlama.io.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateShippingRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetProductResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.entities.concretes.Sale;
import kodlama.io.ecommerce.repository.abstracts.SaleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class SaleManager implements SaleService {
    private final SaleRepository repository;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final ShippingService shippingService;
    private final SaleBusinessRules rules;
    private final ModelMapper mapper;
    @Override
    public List<GetAllSalesResponse> getAll() {
        List<Sale> sales = repository.findAll();
        List<GetAllSalesResponse> responses = sales
                .stream()
                .map(sale -> mapper.map(sale, GetAllSalesResponse.class))
                .toList();

        return responses;
    }

    @Override
    public GetSaleResponse getById(int id) {
        rules.checkIfSaleExists(id);
        Sale sale = repository.findById(id).orElseThrow();
        GetSaleResponse response = mapper.map(sale,GetSaleResponse.class);

        return response;
    }

    @Override
    public CreateSaleResponse add(CreateSaleRequest request) {
        GetProductResponse product = productService.getById(request.getProductId());
        rules.checkIsProductActiveOrInStock(product.isActive(),product.getQuantity(), request.getQuantity());

        Sale sale = mapper.map(request,Sale.class);
        sale.setId(0);
        sale.setTotalPrice(getTotalPrice(sale));
        sale.setSaleDate(LocalDateTime.now());

        CreateSalePaymentRequest salePaymentRequest = mapper.map(request.getPaymentRequest(), CreateSalePaymentRequest.class);
        salePaymentRequest.setPrice(sale.getTotalPrice());
        paymentService.processSalePayment(salePaymentRequest);

        repository.save(sale);

        productService.processSaleProduct(request);

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoice(request, product, sale, invoiceRequest);
        invoiceService.add(invoiceRequest);

        CreateShippingRequest createShippingRequest = new CreateShippingRequest();
        createShipping(request,createShippingRequest,sale);
        shippingService.add(createShippingRequest);

        CreateSaleResponse response = mapper.map(sale,CreateSaleResponse.class);
        return response;
    }

    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest request) {
        rules.checkIfSaleExists(id);
        Sale sale = mapper.map(request,Sale.class);
        sale.setId(id);
        sale.setTotalPrice(getTotalPrice(sale));

        repository.save(sale);

        UpdateSaleResponse response = mapper.map(sale,UpdateSaleResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Sale sale) {
        return sale.getPrice() * sale.getQuantity();
    }

    private void createInvoice(CreateSaleRequest request, GetProductResponse product, Sale sale, CreateInvoiceRequest invoiceRequest) {
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setQuantity(request.getQuantity());
        invoiceRequest.setPrice(request.getPrice());
        invoiceRequest.setProductName(product.getName());
        invoiceRequest.setSaleDate(sale.getSaleDate());
    }

    private void createShipping(CreateSaleRequest saleRequest, CreateShippingRequest shippingRequest, Sale sale){
        shippingRequest.setAddress(saleRequest.getShippingRequest().getAddress());
        shippingRequest.setFullName(saleRequest.getShippingRequest().getFullName());
        shippingRequest.setSaleId(sale.getId());
    }

}
