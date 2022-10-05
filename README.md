# Getting Started

> Java 17 is used 
> H2 is used as database you just need to boot project

### Reference Documentation

Project contains two endpoint 
* update prices: it gets CSV data in body: [POST] /new/prices
* show updated price: [GET] /new/prices

# endpoints curls

To update prices: 

`
curl --location --request POST 'localhost:8080/new/prices' 
--header 'Content-Type: text/plain'
--data-raw '106, EUR/USD, 8,1.2000,01-06-2020 12:01:01:001
107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110'
`

To show prices in JSON:

`
curl --location --request GET 'localhost:8080/new/prices'
`

