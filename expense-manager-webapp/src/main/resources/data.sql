INSERT INTO tbl_users(NAME,EMAIL,PASSWORD,USER_ID) 
VALUES('rrd','rd@gmail.com','$2a$10$wQJLN1tFNohqlHg4tf8sve8eorUBTBIzi5NbxYfBsvoW3yFIdXCey','75412f65-2040-47ac-a242-a5f17c485af9');
--pwd rd123 


INSERT INTO tbl_expenses(AMOUNT,CATEGORY,DATE,DESCRIPTION,EXPENSE_ID,NAME,USER_ID)
VALUES 
(15000.00,'Savings',CURRENT_TIMESTAMP, '','536718b1-5992-4b73-9a80-3ff9c0bec68a','Index Funds',1),
(25000.00,'Bills',CURRENT_TIMESTAMP, '','22ba0d38-f91a-442e-a222-b60fffddc3f4','Rent',1),
(1200.00,'Bills',CURRENT_TIMESTAMP, '','9dbfacf8-04f8-431f-9070-1e2fed1448e4','Electricity Bill',1),
(1000.00,'Bills',CURRENT_TIMESTAMP, '','aa5cfdcb-0563-4808-9409-6ce1fd67eca3','Wifi',1),
(3000.00,'Grocery',CURRENT_TIMESTAMP, 'Big Basket','b5220dbb-dd36-40f0-8e5a-2adccd5b650c','Groceries',1),
(700.00,'Micellnous',CURRENT_TIMESTAMP, 'CCD','378f9144-ddce-4bd4-8b9f-84c2b45ba13d','Cafe',1),
(900.00,'Grocery',CURRENT_TIMESTAMP, '','aa5cfdcb-0563-4808-9409-6ce1fd67eca4','Blinkit',1),
(1000.00,'Micellnous',CURRENT_TIMESTAMP, '','a620b897-e803-4dc4-89f3-0a766881f473','Movie',1),
(149.00,'Bills',CURRENT_TIMESTAMP, '','01a2b569-d211-4c29-8fdc-861501b6c6a4','Netflix',1),
(2000.00,'Miscellnous',CURRENT_TIMESTAMP, '','6c502dfa-5f31-4f52-b74c-1f59a1645944','Restaurant',1);