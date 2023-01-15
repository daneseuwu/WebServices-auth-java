# WebServices-auth
Required

```bash
GlassFish 4.1.2
sqljdbc 6
jdk 1.8
Netbeant 8.2 or Apache netbean 15
server version 2019 or lastest
```

Create database on sql server
```sql
create database webservicedba
```
use database on sql server
```sql
use webservicedba
```

Create tables  

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

select * from user_webservice
```

```sql
create table rol(
id_rol int identity(1,1) primary key,
rol varchar(20)
)

select * from rol
```
Encrypt passwords for users registers sentence ```UPDATE```


update user_webservice set password=ENCRYPTBYPASSPHRASE('Pr0gr4mm1ng',123)
