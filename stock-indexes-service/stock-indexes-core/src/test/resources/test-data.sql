CREATE TABLE indexes (symbol VARCHAR(100) NOT NULL PRIMARY KEY );

INSERT INTO indexes(symbol) VALUES('TST1');


CREATE TABLE index_values (symbol VARCHAR(100) NOT NULL , value_date TIMESTAMP NOT NULL , value DECIMAL NOT NULL );
ALTER TABLE index_values  ADD  
    PRIMARY KEY (symbol,value_date);

INSERT INTO index_values(symbol, value_date, value) VALUES('TST1','2008-08-08 20:08:08','13.2');
INSERT INTO index_values(symbol, value_date, value) VALUES('TST1','2004-08-08 20:08:08','10');