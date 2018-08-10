-- Database: aineko

-- DROP DATABASE aineko;

CREATE DATABASE aineko
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.utf8'
       LC_CTYPE = 'en_US.utf8'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE aineko
  IS 'is database for storing settings for aineko agent';

'create table settings (
'	id serial PRIMARY KEY,
'	website VARCHAR (255) UNIQUE NOT NULL,
'	added TIMESTAMP NOT NULL
');