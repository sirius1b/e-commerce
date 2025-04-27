# e-commerce

Super Repo for E-Commerce Backend Project

#### Tech Stack

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

### Port

** Services ** 
- Auth: 8080
- Product: 8081
- Cart: 8082 
- Order: 8083
- Notification: 8084
- Inventory: 8085
- Payment: 8086

** Systems ** 
- Redis: 6379
- Mysql: 3306
- Mongo: 27017


- to implement:

  - auth

    - [x] registration
    - [x] login
    - [x] logout
    - [x] get user profile
    - [x] update user profile
    - [x] verify token with optional Role
  
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
