# Fast Food Delivery App 🍔

This app is customized for a specific Fast Food restaurant and it allows the admin to keep track of the delivery drivers and of the menu.
It also allows the client to create a profile, modify it, make orders and rate drivers.

# Types of Objects:

1. Person
   * name
   * phone
   * email

2. Client - extends Person
   * address
   * card

3. Delivery Driver - extends Person
   * rating
   * vehicle

4. Product
    * name
    * price
    * description

5. Burger 🍔 - extends Product
    * typeOfMeat
    * sauce
    * cheese

6. Fries 🍟 - extends Product
    * type
    * size
    * topping
    
7. Milkshake 🥤 - extends Product
    * flavour
    * topping
    * whippingCream

8. Order
     * id
     * data
     * cost
     * paymentType
     * products
     * client
     * driver
    


# Actions:

I have created two separate menus, depending on the role of the user who is currently using the app:
 
- Client Menu
    1. Create profile
    2. Search for client
    3. Update your data
    4. Delete my account
    5. Make an order
    6. Review my order details
    7. Cancel my order
    8. Rate a driver
    9. Switch user mode
    10. Exit
    
- Admin Menu
    1. Add new delivery driver
    2. Search for delivery driver
    3. Update driver's data
    4. Delete driver
    5. Add new product
    6. Search for product
    7. Update product
    8. Delete product
    9. Show driver list sorted by rating
    10. Switch user mode
    11. Exit
