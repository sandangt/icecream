package sanlab.icecream.consul.repository.restclient;

import sanlab.icecream.consul.dto.payment.CreateInvoiceRequestDto;
import sanlab.icecream.consul.dto.payment.CreateInvoiceResponseDto;
import sanlab.icecream.consul.dto.payment.HealthResponseDto;

import java.util.Optional;

public interface PaymentRepository {

    Optional<HealthResponseDto> getHealth();

    Optional<CreateInvoiceResponseDto> createInvoice(CreateInvoiceRequestDto req);

}
