## Service Overview

This document outlines the primary flows and responsibilities of each microservice in the system.

**1. Auth Service**

- **Registration:** Handles new user registration.
- **Login:** Authenticates users and provides access tokens.
- **Token Validation:** Validates the authenticity and validity of access tokens.
- **Profile Management:** Allows users to retrieve and update their profile information.
- **Logout:** Invalidates user sessions.

**2. Product Service**

- **User Perspective:**
  - Retrieves a list of products or a specific product by ID.
  - Retrieves a list of product categories.
- **Admin Perspective:**
  - Creates, updates, and deletes product listings.

**3. Cart Service**

- **Cart Management:**
  - Adds items to the cart.
  - Removes items from the cart.
  - Retrieves the contents of the cart.
- **Checkout:** Initiates the checkout process for the cart, integrating with the Payment and Order services.
- **Cart Reset:** Empties the cart after a successful checkout or when needed.

**4. Order Service**

- **Order Management:**
  - Creates and updates order information.
  - Retrieves a list of orders or a specific order by ID.
- **Order Fulfillment:**
  - Manages the fulfillment process, potentially integrating with external logistics services.
- **Order Status Updates:**
  - Handles updates to the order status (e.g., processing, shipped, delivered).

**5. Payment Service**

- **Payment Processing:**
  - Handles payment processing using a payment gateway (e.g., Stripe, PayPal).
  - Captures payment authorization.
- **Transaction Management:**
  - Manages transactions and refunds.
- **Payment Confirmation:**
  - Confirms successful payments and updates order status.

**6. Notification Service**

- **Email Notifications:**
  - Sends email notifications for order confirmation, shipping updates, and other events.
- **SMS Notifications:**
  - Sends SMS notifications (optional).
- **Push Notifications:**
  - Sends push notifications to mobile apps (optional).

**7. Inventory Service**

- **Inventory Management:**
  - Tracks the quantity of products in stock.
  - Updates inventory levels when orders are placed or canceled.
- **Stock Level Monitoring:**
  - Monitors stock levels and triggers alerts when levels are low.
- **Inventory Reporting:**
  - Generates reports on inventory levels and trends.

**Workflow Example: Checkout Process**

1.  The user views their cart and initiates the checkout process (Cart Service).
2.  The Cart Service communicates with the Payment Service to process the payment.
3.  Upon successful payment, the Payment Service notifies the Order Service to create a new order.
4.  The Order Service updates the order status and manages the fulfillment process.
5.  The Notification Service sends email notifications to the user to confirm order placement and updates.:\*\*
    - Creates, updates, and deletes product listings.
