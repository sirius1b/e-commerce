# e-commerce

Super Repo for E-Commerce Backend Project

Tech Stack

| svc          | tech stack                     |
| ------------ | ------------------------------ |
| auth         | spring, mysql, redis           |
| product      | spring, mongo                  |
| cart         | spring, mysql                  |
| order        | spring, mysql                  |
| notification | spring, smtp integration, nodb |
| inventory    | spring, mysql                  |
| payment      | gin, nodb                      |

---

- to implement:

  - auth

    - [ ] registration
    - [ ] login
    - [ ] logout
    - [ ] get user profile
    - [ ] update user profile
    - [ ] verify token
    - [ ] has authority -> implemented in verity-token

  - products

    - [ ]get product(id,ids)
    - [ ]list product
    - [ ]post proudct
    - [ ]update product
    - [ ]delete product
    - [ ]list categories

  - cart

    - [ ] get cart
    - [ ] add item to cart
    - [ ] remove cart
    - [ ] do checkout

  - order

    - [ ] post order
    - [ ] list orders
    - [ ] get order details (id)
    - [ ] update order status

  - notification

    - [ ] notify user(user_id, event)

  - inventory
    - [ ] lock-stock
    - [ ] unlock-stock
    - [ ] get stock (product_id)
    - [ ] update stock (product_id)

- to mock:
  - payment
    - [ ] make payment: random, hook towards order api, to update order status
