insert into testbankapp.hibernate_sequence(next_val) values
    (1), (1), (1), (1), (1), (1);

insert into testbankapp.managers(id, first_name, last_name, email, manager_password, `status`, created_at, updated_at) values
    (1001, 'Petr', 'Sorokin', 'sorokin@employees.bankapp.com', '$2a$12$XCO4uvk2m/siPyqnqPuD2uVwyPMnKguZCKDCftGwmqelFDm6tJV92', 'CLIENT_MANAGER', 20160518, 20230603),
    (1002, 'Nikolay', 'Ivanov', 'ivanov@employees.bankapp.com', '$2a$12$XCO4uvk2m/siPyqnqPuD2uVwyPMnKguZCKDCftGwmqelFDm6tJV92','CLIENT_MANAGER', 20180603, 20230903),
    (1003, 'Ivan', 'Sidorov', 'sidorov@employees.bankapp.com', '$2a$12$XCO4uvk2m/siPyqnqPuD2uVwyPMnKguZCKDCftGwmqelFDm6tJV92', 'PRODUCT_MANAGER', 20150603, 20210603),
    (1004, 'Dmitriy', 'Savkin', 'savkin@employees.bankapp.com', '$2a$12$XCO4uvk2m/siPyqnqPuD2uVwyPMnKguZCKDCftGwmqelFDm6tJV92', 'PRODUCT_MANAGER', 20151201, 20221201),
    (9999, 'Admin', 'BankappAdmin', 'admin@bankapp.com', '$2a$12$XCO4uvk2m/siPyqnqPuD2uVwyPMnKguZCKDCftGwmqelFDm6tJV92', 'ADMIN_BANK_APP', 20151201, 20221201);

insert into testbankapp.clients(id, `status`, tax_code, first_name, last_name, email, client_password, address, phone, created_at, updated_at, manager_id) values
    (2001, 'IN_USE', 'XXX', 'Nikolay', 'Cherkasov', 'cherkasov@gmail.com', '$2a$12$b3Q3Jp5347qDR6mHaGiwJOsGaY5n0wJ5wGUiLkdSKzAxH16Lk0XMi', 'Minsk, pr. Nezavisimosty 54/22', '+375 (29) 222-33-44', 20210302, 20220610, 1001),
    (2002, 'IN_USE', 'XXX', 'Igor', 'Usmanov', 'usmanov@gmail.com', '$2a$12$b3Q3Jp5347qDR6mHaGiwJOsGaY5n0wJ5wGUiLkdSKzAxH16Lk0XMi', 'Grodno, st. Kalinovskogo 35/122', '+375 (44) 555-44-77', 20211012, 20230311, 1001),
    (2003, 'IN_USE', 'XXX', 'Andrey', 'Pchelkin', 'rojkov@gmail.com', '$2a$12$b3Q3Jp5347qDR6mHaGiwJOsGaY5n0wJ5wGUiLkdSKzAxH16Lk0XMi', 'Brest, st. Masherova 27/99', '+375 (33) 777-44-22', 20210115, 20230801, 1002),
    (2004, 'BLOCKED', 'XXX', 'Genadiy', 'Buckin', 'buckin@gmail.com', '$2a$12$b3Q3Jp5347qDR6mHaGiwJOsGaY5n0wJ5wGUiLkdSKzAxH16Lk0XMi', 'Grodno, ul. Sovetskaya 15/31', '+375 (33) 788-32-21', 20200405, 20230901, 1002);

insert into testbankapp.accounts(id, `name`, type, `status`, balance, currency_type, created_at, updated_at, client_id) values
    (3001, 'PL10 1050 0099 7603 1234 5678 9123', 'CURRENT', 'IN_USE', 1000, 'USD', 20210302, 20230604, 2001),
    (3002, 'BY86 AKBB 1010 0000 0029 6600 0000', 'CURRENT', 'IN_USE', 2000, 'USD', 20211012, 20230504, 2002),
    (3003, 'LT60 1010 0123 4567 8901', 'CURRENT', 'IN_USE', 500, 'USD', 20210115, 20230210, 2003),
    (3004, 'BY86 AKBB 1010 0000 0029 6601 0000', 'CREDIT', 'REMOVED', 0, 'USD', 20200405, 20230210, 2004),
    (3005, 'BY86 AKBB 1010 0000 0029 6602 0000', 'CREDIT', 'IN_USE', 10000, 'EUR', 20220101, 20230210, 2002),
    (3006, 'BY86 AKBB 1010 0000 0029 6603 0000', 'DEPOSIT', 'IN_USE', 2000, 'USD', 20220501, 20230210, 2002),
    (3007, 'LT60 1010 0123 4567 8911', 'CREDIT', 'REMOVED', 0, 'USD', 20200925, 20230101, 2003);

insert into testbankapp.transactions(id, type, amount, `description`, created_at, debit_account_id, credit_account_id) values
    (4001, 'TRANSFER', 150, '150 usd from N.Cherkasov to A.Pchelkin', 20231009, 3003, 3001),
    (4002, 'TRANSFER', 250, '250 usd from I.Usmanov to N.Cherkasov', 20231010, 3001, 3002),
    (4003, 'TRANSFER', 200, '200 usd from I.Usmanov to N.Cherkasov', 20231011, 3001, 3002),
    (4004, 'PAYMENT', 120, '120 usd from A.Pchelkin to N.Cherkasov', 20231011, 3001, 3003),
    (4005, 'PAYMENT', 140, '140 usd from A.Pchelkin to N.Cherkasov', 20220215, 3001, 3003);

insert into testbankapp.products(id, `name`, `status`, currency_type, interest_rate, product_limit, created_at, updated_at, manager_id) values
    (5001, 'currency account', 'IN_USE', 'USD', 0.0000, 50000, 20190501, 20200705, 1003),
    (5002, 'credit account', 'IN_USE', 'USD', 0.0500, 10000, 20191001, 20200805, 1004),
    (5003, 'deposit account', 'IN_USE', 'USD', 0.0100, 100000, 20190212, 20210520, 1003),
    (5004, 'currency account', 'IN_USE', 'EUR', 0.0000, 50000, 20180110, 20190311, 1004),
    (5005, 'credit account', 'IN_USE', 'EUR', 0.0500, 10000, 20180919, 20210420, 1004),
    (5006, 'deposit account', 'IN_USE', 'EUR', 0.0100, 100000, 20181102, 20210723, 1003);

insert into testbankapp.agreements(id, interest_rate, `status`, sum, created_at, updated_at, account_id, product_id) values
    (6001, 0.0000, 'IN_USE', 5000, 20210302, 20230705, 3001, 5001),
    (6002, 0.0050, 'IN_USE', 10000, 20211012, 20230705, 3002, 5001),
    (6003, 0.0050, 'IN_USE', 7500, 20210115, 20230705, 3003, 5002),
    (6004, 0.0050, 'REMOVED', 500, 20210115, 20230705, 3004, 5001),
    (6005, 0.0500, 'IN_USE', 10000, 20201201, 20201201, 3005, 5005),
    (6006, 0.0100, 'IN_USE', 5000, 20210911, 20210911, 3006, 5003),
    (6007, 0.0500, 'REMOVED', 5000, 20191020, 20221201, 3007, 5002);