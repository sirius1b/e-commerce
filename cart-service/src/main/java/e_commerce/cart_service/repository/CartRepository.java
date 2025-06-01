package e_commerce.cart_service.repository;

import e_commerce.cart_service.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    Cart findByUserId(String userId);

    Cart findByUserIdAndStatus(String userId, String string);
}