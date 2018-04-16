connect / as sysdba;
drop TABLESPACE ts_POLIFY including contents and datafiles;

CREATE TABLESPACE ts_POLIFY LOGGING
DATAFILE 'C:\database\Scripts\df_POLIFY.dbf' size 100M
extent management local segment space management auto;

drop user bd_polify cascade;

create user bd_polify profile default
identified by 1234
default tablespace ts_POLIFY
temporary tablespace temp
account unlock;

--permisos
grant connect, resource, dba to bd_polify;

connect bd_polify/1234;



ALTER TABLESPACE  
   ts_POLIFY
ADD DATAFILE 
   'C:\database\Scripts\df_POLIFY_02.dbf' 
size 100M