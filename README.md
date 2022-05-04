# Simple Banking Operations Service

Banking Application using Java8, Spring Boot, Spring Security and Mysql DB

RESTful API to simulate simple banking operations.

Requirements
-------------
  1.	CRUD operations for customers and accounts.
		
  2.	Support deposits and withdrawals on accounts.
	
  3.	Internal get recent 10 transaction details
	
  4.	Calculate Interest rates based on account number 
	
  5.	Getting Started
  6. Checkout the project from GitHub
  7. git clone https://github.com/babudevandla/demo_bank_service/

	
	
Refer to the following link for instructions:
---------------------------------------------

Open IDE of your choice and Import as existing maven project in your workspace

- Import existing maven project
- Run mvn clean install
- If using STS, Run As Spring Boot App

Custom port for the api is 9095

Prerequisites
-------------
-	Java 8
-	Spring Tool Suite 4 or similar IDE
-	Maven - Dependency Management
-	Maven Dependencies
-	spring-boot-starter-actuator
-	spring-boot-starter-data-jpa
-	spring-boot-starter-security
-	spring-boot-starter-web
-	spring-boot-devtools
-	mysql - database
-	springfox-swagger2
-	springfox-swagger-ui
-	spring-boot-starter-test

Swagger
-------------------

Please find the Rest API documentation in the below url

http://localhost:9095/bankservice/swagger-ui.html


Mysql Database
---------------
Make sure to use mysql jdbc url. If you intend to you use custom database name, please define datasource properties in application.properties

		## Spring DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
		spring.datasource.url = jdbc:mysql://localhost:3306/online_bank?useSSL=false
		spring.datasource.username = root
		spring.datasource.password = root


Testing the Bank APP Rest API
------------------------------
-	Authentication Rest endpoint 


		1.	Method : POST
		2.	URL : http://localhost:9095/bankservice/authenticate
		3.	Request Body: 
			{
    	"username":"bankservice",
    	"password":"password"
		}
		4.	Response Body:
			{
				token":"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rc2VydmljZSIsImV4cCI6MTY1MTQyMTUzMSwiaWF0IjoxNjUxNDAzNTMxfQ.MPSqVuhsne_JIc92mnAjKJJhhHUpOUNv8HSb60KrAKUU8_1X75ajkx-vFk-GeUZDhrk7LWs5cN0AMzyF4KEQEg"
			}

-	CUSTOMER LIST


		1.	Method: GET
		2.	URL : http://localhost:9095/bankservice/api/v1/customers/all
	
-	ADD CUSTOMER

		1.	Method: POST
		2.	URL: http://localhost:9095/bankservice/api/v1/customers/add
		3.	Request Body:
				{
					"customerNumber":00001,
					"firstName":"Test",
					"lastName":"Test",
					"address":"Blore",
					"city":"Bengalure",
					"state":"KA",
					"mobileNum":1234567890,
					"status":"ACTIVE"
				 }

-	GET ACCOUNT ID

		1.	Method: GET
		2.	URL: http://localhost:9095/bankservice/api/v1/accounts/212345676540

-	ADD ACCOUNT FOR CUSTOMER

		1.	Method: POST
		2.	URL: http://localhost:9095/bankservice/api/v1/accounts/add/2
		3.	Request Body:
				{
					"accountNumber": 412345676546,
					"accout_type": "CURRENT",
					"account_name": "ICICI BANK",
					"description": "This is ICICI bank account.",
					"balance": 1000.0,
					"interestRate": 1.0
				}


-	DEPOSITE AMOUNT

		1.	Method: PUT
		2.	URL: http://localhost:9095/bankservice/api/v1/deposit/1
		3.	Request Body:
				{
					"accountNumber": 412345676546,
					"amount": 100
				}
				 
				 
-	WITHDROW AMOUNT

		1.	Method: PUT
		2.	URL: http://localhost:9095/bankservice/api/v1/withdrawal/1
		3.	Request Body:
				{
					"accountNumber": 212345676540,
					"amount": 1000
				}
				 
-	CALCULATE INTEREST RATES

		1.	Method: GET
		2.	URL: http://localhost:9095/bankservice/api/v1/calculate_interest/412345676546?amount=500
		
				 
-	TRANSACTIONS

		1.	Method: GET
		2.	URL: http://localhost:9095/bankservice/api/v1/transactions/1/412345676546
		

Please use the Swagger url (or) POST MAN to perform CRUD operations.

Browse to /src/test/resources to find sample requests to add customer and accounts.


Authors

@Babu Devandla
