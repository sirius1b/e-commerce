Handles user carts and checkout session.

**Endpoints:**

- `GET /cart` – Get current cart
- `POST /cart/add` – Add item to cart
- `POST /cart/remove` – Remove item from cart
- `POST /cart/checkout` – Initiate checkout

**Internal interface:**

- Calculate total price
- Lock stock before checkout
- Validate prices from Product Service
