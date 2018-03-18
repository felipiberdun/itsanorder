# Things yet to be done:
* Error handlers
* Response messages
* Apply criteria to search endpoints
* Create dtos for the input operation
* Improve Order Status strategy return
* Jackson enum ignore case
* Swagger

# Endpoints:

**Customer (User):**
* Create new user
POST http://localhost:8080/api/v1/customers
{
    "email": "felipi.berdun@gmail.com",
    "password": "abcde",
    "name": "nome",
    "address": "endereco teste"
}

* Find a existing user:
GET http://localhost:8080/api/v1/customers/{id}

* Authenticate user:
POST http://localhost:8080/api/v1/customers/auth
{
	"email": "felipi.berdun@gmail.com",
	"password": "abcde"
}

**Stores**
* Find Stores
GET http://localhost:8080/api/v1/stores

* Find Store by Id
GET http://localhost:8080/api/v1/stores/{id}

* Find Products by Store
GET http://localhost:8080/api/v1/stores/{id}/products

**Cousine**
* Find Cousines
GET http://localhost:8080/api/v1/cousines

* Find Cousine By Id
GET http://localhost:8080/api/v1/cousines/{id}

* Find Stores by Cousine
GET http://localhost:8080/api/v1/cousine/1/stores

**Products**
* Find Products
GET http://localhost:8080/api/v1/products

* Find Product By Id
GET http://localhost:8080/api/v1/products/{id}

**Orders**
* Create an order
POST http://localhost:8080/api/v1/orders
{
	"customer": {
		"id": 1
	},
	"store": {
		"id": 1
	},
	"orderItems": [
		{
			"product": {
				"id": 1
			},
			"price": 9.2,
			"quantity": 2,
			"total": 18.4
	}	
	]
}

* Find Order by Id
GET http://localhost:8080/api/v1/orders/{id}

* Find Order Customer
GET http://localhost:8080/api/v1/orders/{id}/customer

* Find Order Status
GET http://localhost:8080/api/v1/orders/{id}/status

* Change Order Status
PUT http://localhost:8080/api/v1/orders/{id}/status
{
	"source": "CUSTOMER",
	"status": "DELIVERIED"
}
