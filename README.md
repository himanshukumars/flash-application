# Flash Sale Framework

This is a toy implementation for a possible flash sales framework (implemented as a library) in Java.

## Pre-requisites

1. A running MySql instance
2. A REST client to test the exposed APIs - Postman

## API

The library contains the following relevant public facing APIs:
1. /register/flashSale/{flashSaleId} - Registration by any valid user for a particular flash sale.
2. /product/{productId} - Product Details
3. /products/flashSale/{flashSaleId} - All the products of a particular flash sale.
4. /order/checkout - Checking out your order.

## Idea
As per the problem statement, we have implemented a FlashSale framework. The system is mostly database-driven till flash sale actually starts. During sale, Database persistence is done only after the confirmation of purchase.

#### Assumptions

 - Login is not there.
 - One user can order only once in one flash sale event.
- One user cannot order more than one product in a flash sale event.
 - Flash Sale start and close time will be driven by external factors - which sets true/false to status field of flash sale event entity.
- Only Registered user to eCommerce site will be able to register and that's why another registration table.
- Payment is abstracted out and considered successful after checkout() API is invoked.



#### Flow

- Marketplace registers a flashsale with an existing product and opens the registration.
- Buyers register for the flash sale using the link from the email.
- Marketplace operator starts the flash sale. Technically, this can be done, by updating the status of event as open in the database.
- User can check if he/she is eligible to buy a product. This will only return true if all of following holds:

      1. If buyer is already registered.
      2. The buyer hasn't already purchased the product in the same flash sale.
      3. Buyer can only register if the flash sale is not running and is in future.
      
- Once the user has registered for the product, the purchase process starts.
- The purchase will be done only if the following condition holds:

      1. User is registered for the sale.
      2. Product is in the stock.
      3. User is a first-time buyer for that particular sale.
      4. Purchase limit per buyer per flash sale is 1 but the quantity can change.
      5. User is buying in the flash sale period. 
      
## Future Scope

1. Login Implementation
2. A timer based implementation for tracking flash sale period.
3. More than one product can be ordered in a flash sale.
4. Payment Implementation



