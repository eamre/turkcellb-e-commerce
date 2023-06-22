package kodlama.io.ecommerce.business.concretes;

import kodlama.io.ecommerce.business.abstracts.*;
import kodlama.io.ecommerce.business.dto.requests.create.CreateInvoiceRequest;
import kodlama.io.ecommerce.business.dto.requests.create.CreateSaleRequestTwo;
import kodlama.io.ecommerce.business.dto.requests.create.CreateShippingRequest;
import kodlama.io.ecommerce.business.dto.requests.update.UpdateSaleRequest;
import kodlama.io.ecommerce.business.dto.responses.create.CreateSaleResponseTwo;
import kodlama.io.ecommerce.business.dto.responses.get.GetAllSalesResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetCartResponse;
import kodlama.io.ecommerce.business.dto.responses.get.GetSaleResponse;
import kodlama.io.ecommerce.business.dto.responses.update.UpdateSaleResponse;
import kodlama.io.ecommerce.business.rules.SaleBusinessRules;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.entities.concretes.Cart;
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
    private final CartService cartService;
    private final CartItemService cartItemService;
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

//
    @Override
    public CreateSaleResponseTwo createSale(CreateSaleRequestTwo request) {

        GetCartResponse cartResponse = cartService.getById(request.getCartId());
        Cart cart = mapper.map(cartResponse, Cart.class);

        Sale sale = new Sale();
        sale.setCart(cart);
        sale.setTotalPrice(cart.getTotalPrice());
        sale.setSaleDate(LocalDateTime.now());

        CreateSalePaymentRequest salePaymentRequest = mapper.map(request.getPaymentRequest(), CreateSalePaymentRequest.class);
        salePaymentRequest.setPrice(sale.getTotalPrice());
        paymentService.processSalePayment(salePaymentRequest);

        Sale createdSale = repository.save(sale);

        productService.processSaleProduct(cart);

        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoice(request, sale, invoiceRequest);
        invoiceService.add(invoiceRequest);

        CreateShippingRequest createShippingRequest = new CreateShippingRequest();
        createShipping(request,createShippingRequest,sale);
        shippingService.add(createShippingRequest);
//        var updateCart = mapper.map(cart, UpdateCartRequest.class);
//        cartService.update(cartId, updateCart);

        CreateSaleResponseTwo responseTwo = mapper.map(createdSale, CreateSaleResponseTwo.class);

        return responseTwo;
    }
    @Override
    public UpdateSaleResponse update(int id, UpdateSaleRequest request) {
        rules.checkIfSaleExists(id);
        Sale sale = mapper.map(request,Sale.class);
        sale.setId(id);
//        sale.setTotalPrice(getTotalPrice(sale));

        repository.save(sale);

        UpdateSaleResponse response = mapper.map(sale,UpdateSaleResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfSaleExists(id);
        repository.deleteById(id);
    }

//    private double getTotalPrice(Sale sale) {
//        return sale.getPrice() * sale.getQuantity();
//    }

    private void createInvoice(CreateSaleRequestTwo request, Sale sale, CreateInvoiceRequest invoiceRequest) {
        invoiceRequest.setCartId(request.getCartId());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
//        invoiceRequest.setQuantity(request.getQuantity());
//        invoiceRequest.setPrice(request.getPrice());
//        invoiceRequest.setProductName(product.getName());
        invoiceRequest.setTotalPrice(sale.getTotalPrice());
        invoiceRequest.setSaleDate(sale.getSaleDate());
    }

    private void createShipping(CreateSaleRequestTwo saleRequest, CreateShippingRequest shippingRequest, Sale sale){
        shippingRequest.setAddress(saleRequest.getShippingRequest().getAddress());
        shippingRequest.setFullName(saleRequest.getShippingRequest().getFullName());
        shippingRequest.setSaleId(sale.getId());
    }

}
//    private double calculateTotalPrice(GetCartResponse cart) {
//        List<CartItem> cartItems = cart.getCartItems();
//        double totalPrice = 0.0;
//
//        for (CartItem cartItem : cartItems) {
//            double itemPrice = cartItem.getPrice() * cartItem.getQuantity();
//            totalPrice += itemPrice;
//        }
//
//        return totalPrice;
//    }
//
//    @Override
//    public CreateSaleResponse add(CreateSaleRequest request) {
//        GetProductResponse product = productService.getById(request.getProductId());
//        rules.checkIsProductActiveOrInStock(product.isActive(),product.getQuantity(), request.getQuantity());
//
//        Sale sale = mapper.map(request,Sale.class);
//        sale.setId(0);
//        sale.setTotalPrice(getTotalPrice(sale));
//        sale.setSaleDate(LocalDateTime.now());
//
//        CreateSalePaymentRequest salePaymentRequest = mapper.map(request.getPaymentRequest(), CreateSalePaymentRequest.class);
//        salePaymentRequest.setPrice(sale.getTotalPrice());
//        paymentService.processSalePayment(salePaymentRequest);
//
//        repository.save(sale);
//
//        productService.processSaleProduct(request);
//
//        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
//        createInvoice(request, product, sale, invoiceRequest);
//        invoiceService.add(invoiceRequest);
//
//        CreateShippingRequest createShippingRequest = new CreateShippingRequest();
//       // createShipping(request,createShippingRequest,sale);
//        shippingService.add(createShippingRequest);
//
//        CreateSaleResponse response = mapper.map(sale,CreateSaleResponse.class);
//        return response;
//    }