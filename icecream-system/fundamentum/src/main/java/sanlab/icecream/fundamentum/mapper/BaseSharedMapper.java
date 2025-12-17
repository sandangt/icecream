package sanlab.icecream.fundamentum.mapper;

import org.mapstruct.Named;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.constant.EDeliveryMethod;
import sanlab.icecream.fundamentum.constant.EDeliveryStatus;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentMethod;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.constant.EProductStatus;

import java.util.UUID;

public interface BaseSharedMapper  {

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return String.valueOf(uuid);
    }

    @Named("stringToUuid")
    default UUID stringToUuid(String str) {
        try {
            return UUID.fromString(str);
        } catch (Exception ignored) {
            return null;
        }
    }

    @Named("eCustomerStatusToName")
    default String eCustomerStatusToName(ECustomerStatus status) {
        return ECustomerStatus.toName(status);
    }

    @Named("nameToECustomerStatus")
    default ECustomerStatus nameToECustomerStatus(String status) {
        return ECustomerStatus.fromName(status);
    }

    @Named("eImageTypeToName")
    default String eImageTypeToName(EImageType type) {
        return EImageType.toName(type);
    }

    @Named("nameToEImageType")
    default EImageType nameToEImageType(String type) {
        return EImageType.fromName(type);
    }

    @Named("eDeliveryMethodToName")
    default String eDeliveryMethodToName(EDeliveryMethod method) {
        return EDeliveryMethod.toName(method);
    }

    @Named("nameToEDeliveryMethod")
    default EDeliveryMethod nameToEDeliveryMethod(String method) {
        return EDeliveryMethod.fromName(method);
    }

    @Named("ePaymentMethodToName")
    default String ePaymentMethodToName(EPaymentMethod method) {
        return EPaymentMethod.toName(method);
    }

    @Named("nameToEPaymentMethod")
    default EPaymentMethod nameToEPaymentMethod(String method) {
        return EPaymentMethod.fromName(method);
    }

    @Named("eDeliveryStatusToName")
    default String eDeliveryStatusToName(EDeliveryStatus status) {
        return EDeliveryStatus.toName(status);
    }

    @Named("nameToEDeliveryStatus")
    default EDeliveryStatus nameToEDeliveryStatus(String status) {
        return EDeliveryStatus.fromName(status);
    }

    @Named("ePaymentStatusToName")
    default String ePaymentStatusToName(EPaymentStatus status) {
        return EPaymentStatus.toName(status);
    }

    @Named("nameToEPaymentStatus")
    default EPaymentStatus nameToEPaymentStatus(String status) {
        return EPaymentStatus.fromName(status);
    }

    @Named("eOrderStatusToName")
    default String eOrderStatusToName(EOrderStatus status) {
        return EOrderStatus.toName(status);
    }

    @Named("nameToEOrderStatus")
    default EOrderStatus nameToEOrderStatus(String status) {
        return EOrderStatus.fromName(status);
    }

    @Named("eProductStatusToName")
    default String eProductStatusToName(EProductStatus status) {
        return EProductStatus.toName(status);
    }

    @Named("nameToEProductStatus")
    default EProductStatus nameToEProductStatus(String status) {
        return EProductStatus.fromName(status);
    }
}
