package sanlab.icecream.consul.repository.crud.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.ObjectUtils;
import sanlab.icecream.consul.model.Order;
import sanlab.icecream.consul.repository.crud.OrderExtendRepository;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;

import java.util.UUID;

public class OrderExtendRepositoryImpl implements OrderExtendRepository {

    private final EntityManager entityManager;

    public OrderExtendRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updatePaymentId(UUID orderId, UUID paymentId) {
        if (ObjectUtils.isEmpty(paymentId)) return;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Order> update = cb.createCriteriaUpdate(Order.class);
        Root<Order> root = update.from(Order.class);
        update.set(root.get("paymentId"), paymentId)
            .where(cb.equal(root.get("id"), orderId));
        entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public void updatePaymentStatusWhenEPaid(UUID orderId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Order> update = cb.createCriteriaUpdate(Order.class);
        Root<Order> root = update.from(Order.class);
        update.set(root.get("paymentStatus"), EPaymentStatus.COMPLETED.name())
            .where(cb.equal(root.get("id"), orderId));
        entityManager.createQuery(update).executeUpdate();
    }

    @Override
    public void updateOrderStatus(UUID orderId, EOrderStatus orderStatus) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Order> update = cb.createCriteriaUpdate(Order.class);
        Root<Order> root = update.from(Order.class);
        update.set(root.get("orderStatus"), EOrderStatus.toName(orderStatus))
            .where(cb.equal(root.get("id"), orderId));
        entityManager.createQuery(update).executeUpdate();
    }

}
