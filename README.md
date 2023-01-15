# WebServices-auth
Requisitos para web services

```bash
GlassFish 4.1.2
sqljdbc 6
jdk 1.8
Netbeant 8.2 o Apache netbean 15
script de sql server version 2019
```

CREATE DATABASE
```sql
create database webservicedba
```

```sql
use webservicedba
```

CREATE TABLES 

```sql
create table user_webservice(
id_user int identity(1,1) primary key,
firtname varchar (50),
lastname varchar (50),
code varchar(50),
username varchar(50),
password varbinary(500),
id_rol int,
date_created datetime
)
```

```sql
select * from user_webservice

create table rol(
id_rol int identity(1,1) primary key,
rol varchar(20)
)

select * from rol
```
