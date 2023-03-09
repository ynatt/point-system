Configure datasource in application.yml before starting

TODO: Tests

Available queries:

mutation {
    processPayment(
        prePaymentDetails : {
        price : "1000.9"
        priceModifier : 0.95
        paymentMethod : "MASTERCARD"
        dateTime : "2020-03-08T22:17:00Z"
        }
    ) {
    finalPrice
    points
    }
}

query {
    salesByHour(startDateTime : "2020-03-08T21:17:00Z",
        endDateTime:  "2020-03-08T23:59:59Z") 
    {
        sales
        points
        dateTime
    }
}

