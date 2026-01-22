package sanlab.icecream.echo.constant;

import lombok.Getter;

@Getter
public enum EMailInfo {

    CHECKOUT_NOTIFICATION("Checkout Summary", "email/checkout-summary"),
    PAYMENT_NOTIFICATION("Payment Confirmation", "email/successful-payment"),
    ;

    private final String subject;
    private final String templatePath;

    EMailInfo(String subject, String templatePath) {
        this.subject = subject;
        this.templatePath = templatePath;
    }

}
