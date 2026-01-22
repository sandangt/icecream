package sanlab.icecream.consul.repository.restclient.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import sanlab.icecream.consul.dto.payment.CreateInvoiceRequestDto;
import sanlab.icecream.consul.dto.payment.CreateInvoiceResponseDto;
import sanlab.icecream.consul.dto.payment.HealthResponseDto;
import sanlab.icecream.consul.repository.restclient.PaymentRepository;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.Optional;

import static sanlab.icecream.consul.exception.ConsulErrorModel.PAYMENT_REQUEST_INVOICE_FAILED;

@Component
@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {

    private final RestClient paymentClient;

    public PaymentRepositoryImpl(RestClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public Optional<HealthResponseDto> getHealth() {
        try {
            var result = paymentClient.get()
                .uri("/health")
                .retrieve()
                .body(HealthResponseDto.class);
            return Optional.ofNullable(result);
        } catch (HttpClientErrorException ex) {
            return Optional.empty();
        }
    }

    @Override
    public CreateInvoiceResponseDto createInvoice(CreateInvoiceRequestDto req) {
        try {
            return paymentClient.post()
                .uri("/api/create-invoice")
                .body(req)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(CreateInvoiceResponseDto.class);
        } catch (HttpClientErrorException ex) {
            LogUtils.logException(log, ex);
            throw new IcRuntimeException(ex, PAYMENT_REQUEST_INVOICE_FAILED);
        }
    }
}
