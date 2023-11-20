# Bank Project [Backend]

There is a prototype of the BackEnd Bank's Core Services data.
Data consist of clients, accounts, products, accounts, transactions and managers
___

### Database Diagram
![PhotoBase](https://github.com/SmirnovAlex0891/TelRanOutProject/blob/master/DB_diagram.jpg)

___
## Database Structure

### Table manager (BankApp managers table)

| Column name      | Type        | Description                                |
|------------------|-------------|--------------------------------------------|
| id               | integer     | id key of row - unique, not null, PK       | 
| first_name       | varchar(50) | manager's name                             | 
| last_name        | varchar(50) | manager's surname                          | 
| email            | varchar(50) | manager's email - unique, (Login app)      | 
| manager_password | varchar(60) | manager's email - not null, (Password app) | 
| status           | varchar(20) | manager's status from enum ManagerStatus   | 
| created_at       | date        | date of row creation                       |
| updated_at       | timestamp   | date of last update                        | 


### Table client ( BankApp clients table )

| Column name     | Type        | Description                                  |
|-----------------|-------------|----------------------------------------------|
| id              | integer     | id key of entity - unique, not null, PK      | 
| status          | VARCHAR(20) | client's status from enum ClientStatus       |
| tax_code        | varchar(20) | client's TAX code                            |
| first_name      | varchar(50) | client's name                                |
| last_name       | varchar(50) | client's surname                             |
| email           | varchar(60) | client's e-mail - unique, (Login app)        |                               
| client_password | varchar(60) | client's password - not null, (Password app) |                               
| address         | varchar(80) | client's address                             |
| phone           | varchar(20) | client's phone                               |                                
| created_at      | date        | date of entity creation                      |
| updated_at      | date        | date of last update                          |
| manager_id      | integer     | manager`s id FK references managers(id)      |


### Table account (BankApp accounts table)

| Column name   | Type          | Description                             |
|---------------|---------------|-----------------------------------------|
| id            | integer       | id key of entity - unique, not null, PK |        
| name          | varchar(100)  | name of account                         |                              
| type          | varchar(50)   | account from enum AccountType           |                                   
| status        | varchar(50)   | status from enum AccountStatus          |                          
| balance       | decimal(15,2) | balance of the account                  | 
| currency_type | varchar(50)   | currency code from enum CurrencyType    |                          
| created_at    | date          | date of entity creation                 |
| updated_at    | date          | date of last update                     |
| client_id     | integer       | client`s id FK references clients(id)   | 


### Table transaction (BankApp transactions table) 

| Column name        | Type          | Description                                 |
|--------------------|---------------|---------------------------------------------|
| id                 | integer       | id key of entity - unique, not null, PK     | 
| type               | varchar(50)   | transaction type  from enum TransactionType | 
| amount             | decimal(12,4) | transaction amount - not null               | 
| description        | varchar(255)  | description of transaction                  | 
| created_at         | timestamp     | timestamp of entity creation                | 
| debit_account_id   | integer       | account`s id FK references accounts(id)     | 
| credit_account_id  | integer       | account`s id FK references accounts(id)     | 


### Table product (BankApp products table)

| Column name   | Type         | Description                              |
|---------------|--------------|------------------------------------------|
| id            | integer      | id key of entity - unique, not null, PK  |
| name          | varchar(70)  | product's name                           |
| status        | varchar(50)  | product's status from enum ProductStatus |
| currency_code | varchar(50)  | currency code from enum CurrencyType     |
| interest_rate | decimal(6,4) | interest rate of product                 |
| product_limit | integer      | limit of product                         |
| created_at    | date         | date of entity creation                  |
| updated_at    | date         | date of last update                      |
| manager_id    | integer      | manager`s id FK references managers(id)  |


### Table agreement (BankApp agreements table)

| Column name   | Type          | Description                                |
|---------------|---------------|--------------------------------------------|
| id            | integer       | id key of entity - unique, not null, PK    |
| interest_rate | decimal(6,4)	 | current interest rate of agreement         | 
| status        | VARCHAR(50)   | agreement's status from enum AccountStatus | 
| sum           | decimal(15,2) | sum of agreement                           | 
| created_at    | date          | date of entity creation                    | 
| updated_at    | date          | date of last update                        | 
| product_id    | integer       | product's id FK references products(id)    |
| account_id    | integer       | client's FK references accounts(id)        | 
