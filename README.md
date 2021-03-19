# shopping-cart
Shopping Cart Application using [Spring Boot](http://projects.spring.io/spring-boot/) with Hibernate/JPA

## Requirements

For building and running the application we need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on our local machine. One way is to execute the `main` method in the `com.shopping.cart.ShoppingCartApplication` class from the IDE.

Alternatively we can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
mvn spring-boot:run
```

## Database details

MySql Database is used in the application. Database with name shopping_cart needs to be created. 
Below queries needs to be run to create the required tables in database. 

```
create database shopping_cart;

use shopping_cart;

create table user_details (
	user_id int NOT NULL AUTO_INCREMENT,
    user_name varchar(255) NOT NULL,
    first_name varchar(255),
    last_name varchar(255) ,
    primary key (user_id)
);

create table cart (
	cart_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    primary key (cart_id),
    foreign key (user_id) references user_details(user_id)
);

create table product (
    product_id int NOT NULL AUTO_INCREMENT,
    product_name varchar(255) NOT NULL,
    category varchar(255),
    price float,
    PRIMARY KEY (product_id)
);

create table book (
	book_id int NOT NULL auto_increment,
    product_id int NOT NULL,
    author varchar(255) NOT NULL,
    genre varchar(255),
    publications varchar(255),
    primary key (book_id),
    foreign key (product_id) references product(product_id)
);

create table apparel (
	apparel_id int NOT NULL auto_increment,
    product_id int NOT NULL,
    apparel_type varchar(255) NOT NULL,
    brand varchar(255),
    design varchar(255),
    primary key (apparel_id),
    foreign key (product_id) references product(product_id)
);

create table cart_item (
	cart_item_id int NOT NULL AUTO_INCREMENT,
    cart_id int NOT NULL,
    product_id int NOT NULL,
    quantity int NOT NULL,
    primary key (cart_item_id),
    foreign key (cart_id) references cart(cart_id),
    foreign key (product_id) references product(product_id)
);
```

Below are the queries and examples to populate the users data and products data in the DB in order to add them to the cart.

```
insert into user_details (user_name, first_name, last_name) 
values("enter user name", "enter first name", "enter last name");
Example: insert into user_details (user_name, first_name, last_name) 
values("kusal", "Kusal", "Bandaru");

insert into cart (user_id) values(enter the user_id created from the previous query);
Example: insert into cart (user_id) values(1);

insert into product (product_name, category, price) values("enter product name", "enter category - either apparel or book", enter price);
Example: insert into product (product_name, category, price) values("Art of computer programming", "book", 500);
insert into product (product_name, category, price) values("Flower Shirt", "apparel", 1500);
```

For the products whose category is given as book, below query can be used to add the book details.

```
insert into book(product_id, author, genre, publications) values(enter the product id generated while inserting the product, "enter author of the book", "enter genre of the book", "enter publication of the book");
Example: insert into book(product_id, author, genre, publications) values(1, "Donald Knuth", "programming", "Pearson");
```

For the products whose category is given as apparel, below query can be used to add the apparel details.

```
insert into apparel(product_id, apparel_type, brand, design) values(enter the product id generated while inserting the product, "enter type of apparel", "enter brand of apparel", "enter design of apparel");
Example: insert into apparel(product_id, apparel_type, brand, design) values(2, "shirt", "UCB", "casual");
```

## Documentation details

[Swagger](https://swagger.io/) is used for the API documentation. It provides the details about the Rest APIs and endpoints along with the operations that can be performed from the respective APIs.

Swagger provides the documentation through it's own user interface which can be accessed from the below URL. Port in the below URL specifies the local port where the application is being served.

```
http://localhost:port/swagger-ui.html
```
We can also interact with the APIs by making rest calls from Swagger UI which makes it a great product. We can make rest API calls same as postman with request body and it provides us the response respectively.