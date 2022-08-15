CREATE TABLE Category(
	name		VARCHAR(20)		PRIMARY KEY,
	picture 	VARCHAR(100)	NOT NULL
);

SELECT * FROM Category;

INSERT INTO Category VALUES ("teavana");
--------------------------------------------------------------------------------

CREATE TABLE Menu(
	name			VARCHAR(20)		PRIMARY KEY ,
	price			BIGINT			NOT NULL DEFAULT 0,
	quantity		BIGINT			NOT NULL DEFAULT 0,
	categoryName	VARCHAR(20)		NOT NULL,
	picture 		VARCHAR(100)	NOT NULL,
	CONSTRAINT Menu_categoryName_FK
		FOREIGN KEY(categoryName) REFERENCES Category(name)
);

SELECT * FROM Menu;

INSERT INTO Menu VALUES ("아메리카노", 4000, 100, "coffee");
--------------------------------------------------------------------------------

UPDATE Account SET balance = 1000000 WHERE customerId = 'lty';
DROP TABLE Category;
UPDATE Account A INNER JOIN Customer C ON A.customerId = C.id 
DELETE FROM Category WHERE name = '';
SET A.balance = 1000000 WHERE A.accountNum = '539-11-1187' AND C.passwd = '1234';
alter table Menu add picture VARCHAR(100) not null;

