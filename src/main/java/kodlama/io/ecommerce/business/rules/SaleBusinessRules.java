package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.abstracts.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaleBusinessRules {
    private final SaleRepository repository;

    public void checkIfSaleExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Sale.NotExists);
        }
    }

    public void checkIsProductActiveOrInStock(boolean isActive, int productQuantity, int saleQuantity){
        checkIfProductIsActive(isActive);
        checkIfProductIsInStockEnough(productQuantity,saleQuantity);
    }

    public void checkIfProductIsActive(boolean isActive){
        if (!isActive){
            throw new BusinessException(Messages.Product.ProductIsNotActive);
        }
    }

    public void checkIfProductIsInStockEnough(int productQuantity, int saleQuantity){
        if (productQuantity < saleQuantity){
            throw new BusinessException(Messages.Product.NotEnoughStock);
        }
    }
//    public void checkIfQuantityEnough(int productQuantity, int saleQuantity){
//        if (productQuantity < saleQuantity){
//            throw new BusinessException(Messages.Product.NotEnoughStock);
//        }
//    }

}
