#create database if not exists testdb;
# use testbankapp;
create table if not exists managers (
    id int not null unique primary key,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    stat varchar(20) not null,
    created_at date,
    updated_at date
);
create table if not exists clients (
    id int not null unique primary key,
    stat varchar(20) not null,
    tax_code varchar(20) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(60) not null unique,
    address varchar(80) not null,
    phone varchar(20) not null,
    created_at date,
    updated_at date,
    manager_id int,
    foreign key (manager_id) references managers(id)
);



#     <changeSet author="SmA" id="create accounts table">
#         <preConditions onFail="CONTINUE" onFailMessage="Table accounts already exists">
#             <not>
#                 <tableExists tableName="accounts"/>
#             </not>
#         </preConditions>
#         <createTable tableName="accounts">
#             <column name="id" type="integer">       <!-- <column name="id" type="binary(16)"> -->
#                 <constraints primaryKey="true" nullable="false" unique="true"/>
#             </column>
#             <column name="name" type="VARCHAR(100)">
#                 <constraints nullable="false" unique="true"/>
#             </column>
#             <column name="type" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="status" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="balance" type="DECIMAL(15,2)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="currency_code" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="created_at" type="DATE"/>    <!-- defaultValueDate="CURRENT_TIMESTAMP"/> -->
#             <column name="updated_at" type="DATE"/>
#             <column name="client_id" type="integer">     <!-- <column name="client_id" type="binary(16)"> -->
#                 <constraints foreignKeyName="client_id" references="clients(id)" nullable="false"/>
#             </column>
#         </createTable>
#     </changeSet>
#
#     <changeSet id="create transactions table" author="SmA">
#         <preConditions>
#             <not>
#                 <tableExists tableName="transactions"/>
#             </not>
#         </preConditions>
#         <createTable tableName="transactions">
#             <column name="id" type="integer">       <!-- <column name="id" type="binary(16)"> -->
#                 <constraints primaryKey="true" nullable="false" unique="true"/>
#             </column>
#             <column name="type" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="amount" type="DECIMAL(12,4)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="description" type="VARCHAR(255)">
#                 <constraints nullable="true"/>
#             </column>
#             <column name="created_at" type="TIMESTAMP" defaultValueDate="CURRENT_TIMESTAMP"/>
#             <column name="debit_account_id" type="integer">      <!-- <column name="debit_account_id" type="binary(16)"> -->
#                 <constraints foreignKeyName="debit_account_id" references="accounts(id)"/>
#             </column>
#             <column name="credit_account_id" type="integer">     <!-- <column name="credit_account_id" type="binary(16)"> -->
#                 <constraints foreignKeyName="credit_account_id" references="accounts(id)"/>
#             </column>
#         </createTable>
#     </changeSet>
#
#     <changeSet id="create products table" author="SmA">
#         <preConditions onFail="CONTINUE" onFailMessage="Table products already exists">
#             <not>
#                 <tableExists tableName="products"/>
#             </not>
#         </preConditions>
#         <createTable tableName="products">
#             <column name="id" type="integer">
# <!--            <column name="id" type="binary(16)">-->
#                 <constraints primaryKey="true" nullable="false" unique="true"/>
#             </column>
#             <column name="name" type="VARCHAR(70)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="status" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="currency_code" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="interest_rate" type="DECIMAL(6,4)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="product_lim" type="integer">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="created_at" type="DATE"/> <!-- defaultValueDate="CURRENT_TIMESTAMP"/> -->
#             <column name="updated_at" type="DATE"/> <!-- defaultValueDate="CURRENT_TIMESTAMP"/> -->
#             <column name="manager_id" type="integer">        <!-- <column name="manager_id" type="binary(16)"> -->
#                 <constraints foreignKeyName="manager_id_fk" references="managers(id)"
#                              nullable="false"/>
#             </column>
#         </createTable>
#     </changeSet>
#
#     <changeSet id="create agreements table" author="SmA">
#         <preConditions onFail="CONTINUE" onFailMessage="Table agreements already exists">
#             <not>
#                 <tableExists tableName="agreements"/>
#             </not>
#         </preConditions>
#         <createTable tableName="agreements">
#             <column name="id" type="integer">        <!-- <column name="id" type="binary(16)"> -->
#                 <constraints primaryKey="true" nullable="false" unique="true"/>
#             </column>
#             <column name="interest_rate" type="DECIMAL(6,4)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="status" type="VARCHAR(50)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="sum" type="DECIMAL(15,2)">
#                 <constraints nullable="false"/>
#             </column>
#             <column name="created_at" type="DATE"/>    <!-- defaultValueDate="CURRENT_TIMESTAMP"/> -->
#             <column name="updated_at" type="DATE"/>
#             <column name="account_id" type="integer">        <!-- <column name="account_id" type="binary(16)"> -->
#                 <constraints foreignKeyName="account_id_fk" references="accounts(id)"
#                              nullable="false"/>
#             </column>
#             <column name="product_id" type="integer">
# <!--            <column name="product_id" type="binary(16)">-->
#                 <constraints foreignKeyName="product_id_fk" references="products(id)"
#                              nullable="false"/>
#             </column>
#         </createTable>
#     </changeSet>