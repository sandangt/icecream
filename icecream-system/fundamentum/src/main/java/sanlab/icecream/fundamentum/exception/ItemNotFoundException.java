package sanlab.icecream.fundamentum.exception;

import java.io.Serial;
import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7255509391253688046L;

    private static final String PRODUCT_NOT_FOUND_PATTERN = "Product with id %s not found";
    private static final String CATEGORY_NOT_FOUND_PATTERN = "Category with id %s not found";
    private static final String STOCK_NOT_FOUND_PATTERN = "Stock with id %s not found";
    private static final String FEEDBACK_NOT_FOUND_PATTERN = "Feedback with id %s not found";

    public ItemNotFoundException(String message) {
        super(message);
    }

    public static ItemNotFoundException product(UUID productId) {
        return new ItemNotFoundException(PRODUCT_NOT_FOUND_PATTERN.formatted(productId));
    }

    public static ItemNotFoundException category(UUID categoryId) {
        return new ItemNotFoundException(CATEGORY_NOT_FOUND_PATTERN.formatted(categoryId));
    }

    public static ItemNotFoundException stock(UUID stockId) {
        return new ItemNotFoundException(STOCK_NOT_FOUND_PATTERN.formatted(stockId));
    }

    public static ItemNotFoundException feedback(UUID feedbackId) {
        return new ItemNotFoundException(FEEDBACK_NOT_FOUND_PATTERN.formatted(feedbackId));
    }

}
