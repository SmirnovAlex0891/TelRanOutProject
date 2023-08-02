create database bankApp;
use bankapp;
drop table transaction;

create table transaction
(debit_account_id binary(16),
credit_account_id binary(16),
type integer(1),
amount decimal(12, 4),
description VARCHAR(255),
created_at timestamp
) ;

