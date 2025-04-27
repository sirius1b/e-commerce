Auth Service

- Register Flow (New User)d
- Login Flow
- Token Validation flow
- profile get/ update,
- Logout

Product Service

User

- Get product/ product(id)
- Get categories

Admin

- post/put/delete product

Cart Service

- add item , remove item, get cart
- checkout cart
- empty cart

Order service

- post/put order
- get order /order(id)

payment service

-- idea
user sees, adds to cart, then checkout cart -> if ok(inventory) -> order is created for user

then order status is updated by someone else ( like other service, which delivery agents, communicates with)

-> then email is dispatched to user
