CREATE TABLE `citi_bank` (
  `account_no` bigint(11) NOT NULL,
  `account_balance` double default NULL,
  `account_type` varchar(45) NOT NULL,
  `account_holder_name` varchar(45) NOT NULL,
  PRIMARY KEY  (`account_no`)
);

INSERT INTO citi_bank(
   account_no
  ,account_balance
  ,account_type
  ,account_holder_name
) VALUES (
   9876835861
  ,85000  
  ,'Saving'
  ,'Martin'
),(
   9876598791
  ,20000  
  ,'Saving'
  ,'Paul'
);