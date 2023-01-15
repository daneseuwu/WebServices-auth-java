update user_webservice set password=ENCRYPTBYPASSPHRASE('Pr0gr4mm1ng','123')

select * from user_webservice

select username,CONVERT(varchar(25), DECRYPTBYPASSPHRASE('Pr0gr4mm1ng', password)) password from user_webservice

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

exec sp_auth 'amiranda','123','Pr0gr4mm1ng'