Handles user carts and checkout session.

**Endpoints:**

- `GET /cart` – Get current cart
- `POST /cart/add` – Add item to cart
- `POST /cart/remove` – Remove item from cart
- `POST /cart/checkout` – Initiate checkout

Note:

- each api is user level, extract user_id from it from auth-service for that token by users/me api and use it cart api flow
- there is will no priciing engine, pricing information will be stored in product-service,
