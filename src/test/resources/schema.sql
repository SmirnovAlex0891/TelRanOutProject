use testbankapp;
drop table if exists testbankapp.transactions;
drop table if exists testbankapp.agreements;
drop table if exists testbankapp.products;
drop table if exists testbankapp.accounts;
drop table if exists testbankapp.clients;
drop table if exists testbankapp.managers;
drop table if exists testbankapp.databasechangelog;
drop table if exists testbankapp.databasechangeloglock;
drop table if exists testbankapp.hibernate_sequence;

create table if not exists hibernate_sequence (
    next_val bigint
);
create table if not exists managers (
    id int not null unique primary key,
    first_name varchar(50),
    last_name varchar(50),
    email varchar(50) unique not null,
    manager_password varchar(60) not null,
    `status` varchar(20),
    created_at date,
    updated_at date
);
create table if not exists clients (
    id int not null unique primary key,
    `status` varchar(20),
    tax_code varchar(20),
    first_name varchar(50),
    last_name varchar(50),
    email varchar(60) not null unique,
    client_password varchar(60) not null,
    address varchar(80),
    phone varchar(20),
    created_at date,
    updated_at date,
    manager_id int,
    foreign key (manager_id) references testbankapp.managers(id)
);
create table if not exists accounts (
    id int not null unique primary key,
    `name` varchar(100),
    type varchar(50),
    `status` varchar(50),
    balance decimal(15,2),
    currency_type varchar(50),
    created_at date,
    updated_at date,
    client_id int,
    foreign key (client_id) references testbankapp.clients(id)
);
create table if not exists transactions (
    id int not null unique primary key,
    type varchar(50),
    amount decimal(12,4),
    `description` varchar(255),
    created_at date,
    debit_account_id int,
    credit_account_id int,
    foreign key (debit_account_id) references testbankapp.accounts(id),
    foreign key (credit_account_id) references testbankapp.accounts(id)
);
create table if not exists products (
    id int not null unique primary key,
    `name` varchar(70),
    `status` varchar(50),
    currency_type varchar(50),
    interest_rate decimal(6,4),
    product_limit int,
    created_at date,
    updated_at date,
    manager_id int,
    foreign key (manager_id) references testbankapp.managers(id)
);
create table if not exists agreements (
    id int not null unique primary key,
    interest_rate decimal(6,4),
    `status` varchar(50),
    sum decimal(15,2),
    created_at date,
    updated_at date,
    account_id int,
    product_id int,
    foreign key (account_id) references testbankapp.accounts(id),
    foreign key (product_id) references testbankapp.products(id)
);