package kodlama.io.ecommerce.common.constants;

public class Messages {
    public static class Product {
        public static final String NotExists = "PRODUCT_NOT_EXISTS";
        public static final String AlreadyExists = "PRODUCT_ALREADY_EXISTS";
        public static final String NotEnoughStock = "PRODUCT_NOT_ENOUGH_STOCK";
        public static final String IsOutOfStock = "PRODUCT_IS_OUT_OF_STOCK";
        public static final String ProductIsNotActive = "PRODUCT_NOT_ACTIVE";


    }

    public static class Sale {
        public static final String NotExists = "SALE_NOT_EXISTS";
        public static final String AlreadyExists = "SALE_ALREADY_EXISTS";
    }

    public static class Payment{
        public static final String NotFound = "PAYMENT_NOT_FOUND";
        public static final String CardNumberAlreadyExists = "CARD_NUMBER_ALREADY_EXISTS";
        public static final String NotEnoughMoney = "NOT_ENOUGH_MONEY";
        public static final String NotAValidPayment="NOT_A_VALID_PAYMENT";
        public static final String Failed = "PAYMENT_FAILED";
    }

    public static class Category {
        public static final String NotExists = "Category_NOT_EXISTS";
        public static final String AlreadyExists = "Category_ALREADY_EXISTS";
    }

    public static class Invoice{
        public static final String NotFound = "INVOICE_NOT_FOUND";
    }
}
