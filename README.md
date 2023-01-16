# WebServices-auth
Required

```bash
GlassFish 4.1.2 server http
sqljdbc 6  is required in the web service library as well as in the connection to the service database
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
Encrypt passwords for users' registers using sentence ```SQL UPDATE``` where 123 is the password for the default for all

```sql
update user_webservice set password=ENCRYPTBYPASSPHRASE('Pr0gr4mm1ng','123')
```

decrypt passwords with pattern

```sql
select username,CONVERT(varchar(25), DECRYPTBYPASSPHRASE('Pr0gr4mm1ng', password)) password from user_webservice
```
Create storage procedure for validate auth user
```sql
create procedure sp_auth
@username varchar(50),
@password varchar(50),
@pattern varchar(50)
as
begin
if Exists (select * from user_webservice where username=@username and 
CONVERT(varchar(25), DECRYPTBYPASSPHRASE(@pattern, password)) =@password )
select  * from user_webservice where username=@username and 
CONVERT(varchar(25), DECRYPTBYPASSPHRASE(@pattern, password)) =@password
else 
select -1 id_user
end
```
Connection String 
```sql
public class connectionsql {
    
    //configure driver connection sql driver
    Connection cn = null;
    //String inst = "";
    static connectionsql instancia = null;
    String strConexionSql = "jdbc:sqlserver://localhost\\SRV01:1433;databaseName=webservicedba";
    String strDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    //contructor class connection
    public connectionsql() {
        
        //declare var user and pass to execute storage procdure on database
        String dbuser = "webservicedba";
        String dbpassword = "123";

        try {

            Class.forName(strDriver);
            cn = DriverManager.getConnection(strConexionSql,dbuser,dbpassword);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static connectionsql getInstancia() throws Exception {

        if (instancia == null) {
            instancia = new connectionsql();
        }
        return instancia;

    }

    public Connection getConexion() {
        return cn;
    }
    
}

````
import
```java
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import config.connectionsql;
import interfaces.authInterface;
import models.auth;
```
controller
```java
public class authControllers implements authInterface {

    Connection cn;
    CallableStatement auth;
    ResultSet rs;
    String pattern = "Pr0gr4mm1ng";

    @Override
    public auth authUser(String username, String password) {
        auth user = new auth();

        try {
            cn = connectionsql.getInstancia().getConexion();
            auth = cn.prepareCall("{call sp_auth(?,?,?)}");
            auth.setString(1, username);
            auth.setString(2, password);
            auth.setString(3, pattern);
            rs = auth.executeQuery();

            if (rs.next()) {
                if (rs.getInt("id_user") > 0) {

                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));

//                    if (rs.getBoolean("Estado")) {
//                        user.setStatus(1);
//                    } else {
//                        user.setStatus(0);
//                    }

                } else {
                    user.setId_user(-1);
                    user.setError("user not found");
                }
            } else {
                user.setId_user(-1);
                user.setError("user or pass incorrect");
            }
        } catch (Exception ex) {
            user.setId_user(-1);
            user.setError("error validation" + ex.getMessage());
        }
        return user;
    }

}

```
model getter and setter
```java
 int id_user;
   String username;
   String password;
   int status;
   String error;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

```
interface
```java
public auth authUser(
            String username,
            String password);
```
