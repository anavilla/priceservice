# PriceService
Simple API REST implementation of PriceService using Spring-Boot 

## Steps to run
1. To build and test, you can run:
```sh
$ cd priceservice
$ ./mvnw clean install
```
2. Run using
```sh
$ ./mvnw spring-boot:run
```
## Swagger console
You can use Swagger console to access to the endpoint via http://localhost:8080/api/swagger-ui.html
## Api EndPoint
The API endpoint is accessible via http://localhost:8080/api/priceservice/applicable_price
### Api Params
1. brandId 
2. productId
3. appTimestamp (ISO DateTime in UTC)

This is a request example:
http://localhost:8080/api/prices/applicable_price?appTimestamp=2020-06-14T00%3A00%3A00&brandId=1&productId=35455
