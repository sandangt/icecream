package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sanlab.icecream.consul.dto.extended.CartExtendedDto;
import sanlab.icecream.consul.mapper.CartMapper;
import sanlab.icecream.consul.model.Cart;
import sanlab.icecream.consul.model.CartItem;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.repository.crud.CartItemRepository;
import sanlab.icecream.consul.repository.crud.CartRepository;
import sanlab.icecream.consul.repository.crud.CustomerRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;
import sanlab.icecream.consul.viewmodel.request.CartRequest;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CART_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    @Transactional(readOnly = true)
    public CartExtendedDto get(UUID userId) {
        return cartRepository.findFirstByCustomer_UserId(userId)
            .map(cartMapper::entityToExtendedDto)
            .orElse(null);
    }

    @Transactional
    public CartExtendedDto upsert(UUID userId, CartRequest payload) {
        Customer customer = customerRepository.findFirstByUserId(userId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CUSTOMER_NOT_FOUND, "id: %s".formatted(userId)));
        var requestMapByProductIds = payload.getCartItems()
            .stream()
            .collect(Collectors.toMap(CartRequest.CartItemRequest::getProductId, Function.identity()));
        Optional<Cart> cartOptional = cartRepository.findFirstByCustomer_UserId(userId);
        Map<UUID, Product> productMap = productRepository.findAllByIdIn(requestMapByProductIds.keySet().stream().toList())
            .stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        if (cartOptional.isEmpty()) {
            try {
                Cart cart = cartRepository.save(Cart.builder().customer(customer).build());
                var itemList = productMap.values().stream()
                    .map(item -> (CartItem) CartItem.builder()
                        .quantity(requestMapByProductIds.get(item.getId()).getQuantity())
                        .cart(cart)
                        .product(item)
                        .build()
                    )
                    .toList();
                itemList = cartItemRepository.saveAll(itemList);
                cart.setCartItems(itemList);
                return cartMapper.entityToExtendedDto(cart);
            } catch (Exception ex) {
                throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Failed to create cart");
            }
        }
        Cart cart = cartOptional.get();
        var cartItemMapByProductId = cartOptional.map(Cart::getCartItems)
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toMap(item -> item.getProduct().getId(), Function.identity()));
        List<CartItem> existingItemList = new ArrayList<>();
        List<CartItem> itemList = new ArrayList<>();
        List<CartItem> deletingItemList = new ArrayList<>();
        for (var entry : requestMapByProductIds.entrySet()) {
            if (cartItemMapByProductId.containsKey(entry.getKey())) {
                var item = cartItemMapByProductId.get(entry.getKey());
                var newQuantity = entry.getValue().getQuantity();
                if (newQuantity <= 0) deletingItemList.add(item);
                else {
                    item.setQuantity(newQuantity);
                    existingItemList.add(item);
                }
                continue;
            }
            if (entry.getValue().getQuantity() > 0) {
                itemList.add(CartItem.builder()
                    .cart(cart)
                    .quantity(entry.getValue().getQuantity())
                    .product(productMap.get(entry.getKey()))
                    .build());
            }
        }
        deletingItemList.addAll(cartItemMapByProductId.entrySet()
            .stream()
            .filter(inner -> !requestMapByProductIds.containsKey(inner.getKey()))
            .map(Map.Entry::getValue)
            .collect(Collectors.toSet()));
        try {
            if (CollectionUtils.isNotEmpty(existingItemList)) cartItemRepository.saveAll(existingItemList);
            if (CollectionUtils.isNotEmpty(itemList)) cartItemRepository.saveAll(itemList);
            if (CollectionUtils.isNotEmpty(deletingItemList)) cartItemRepository.deleteAll(deletingItemList);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Failed to update cart");
        }

        cart = cartRepository.findFirstByCustomer_UserId(userId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CART_NOT_FOUND));

        return cartMapper.entityToExtendedDto(cart);
    }

    @Transactional
    public CartExtendedDto reset(UUID userId) {
        var cartOptional = cartRepository.findFirstByCustomer_UserId(userId);
        if (cartOptional.isEmpty()) return null;
        List<CartItem> cartItems = cartOptional.map(Cart::getCartItems).orElse(Collections.emptyList());
        Cart savedCart = cartOptional.get();
        try {
            if (CollectionUtils.isNotEmpty(cartItems)) {
                cartItems.clear();
                savedCart = cartRepository.save(savedCart);
            }
            return cartMapper.entityToExtendedDto(savedCart);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Failed to clear cart");
        }
    }

}
