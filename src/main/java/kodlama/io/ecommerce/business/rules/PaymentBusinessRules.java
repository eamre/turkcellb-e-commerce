package kodlama.io.ecommerce.business.rules;

import kodlama.io.ecommerce.common.constants.Messages;
import kodlama.io.ecommerce.common.dto.CreateSalePaymentRequest;
import kodlama.io.ecommerce.core.exceptions.BusinessException;
import kodlama.io.ecommerce.repository.abstracts.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private PaymentRepository repository;

    public void checkIfPaymentExists(int id) {
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.Payment.NotFound);
        }
    }

    public void checkIfCardExists(String cardNumber){
        if(repository.existsByCardNumber(cardNumber)){
            throw new BusinessException(Messages.Payment.CardNumberAlreadyExists);
        }
    }

    public void checkIfBalanceIfEnough(double balance, double price) {
        if(balance<price){
            throw new BusinessException(Messages.Payment.NotEnoughMoney);
        }
    }

    public void checkIfPaymentIsValid(CreateSalePaymentRequest request) {
        if (!repository.existsByCardNumberAndCardHolderAndCardExpirationYearAndCardExpirationMonthAndCardCvv(
                request.getCardNumber(),
                request.getCardHolder(),
                request.getCardExpirationYear(),
                request.getCardExpirationMonth(),
                request.getCardCvv()
        ))
        {
            throw new BusinessException(Messages.Payment.NotAValidPayment);
        }
    }
}
