
/* Drop Indexes */

DROP INDEX ORDERS_F1;
DROP INDEX ORDER_DTL_F1;



/* Drop Views */

DROP VIEW M_USR;



/* Drop Tables */

DROP TABLE GOODS;
DROP TABLE ORDERS_DTL;
DROP TABLE ORDERS;
DROP TABLE USR;




/* Create Tables */

CREATE TABLE GOODS
(
	-- 商品を一意に識別するID
	GOODS_ID VARCHAR2(20) NOT NULL,
	-- 商品名
	GOODS_NAME VARCHAR2(300) NOT NULL,
	-- 最終更新ユーザID
	UPD_USER_ID VARCHAR2(13),
	-- 最終更新日時
	UPD_DATE DATE,
	PRIMARY KEY (GOODS_ID)
);


CREATE TABLE ORDERS
(
	-- 注文を一意に識別するためのID
	ORDER_ID VARCHAR2(13) NOT NULL,
	-- 注文ユーザを一意に識別するID
	ORDER_USR_ID VARCHAR2(13) NOT NULL,
	-- 注文日時
	ORDER_DATE DATE NOT NULL,
	-- 最終更新ユーザID
	UPD_USER_ID VARCHAR2(13),
	-- 最終更新日時
	UPD_DATE DATE,
	PRIMARY KEY (ORDER_ID)
);


CREATE TABLE ORDERS_DTL
(
	-- 注文を一意に識別するためのID
	ORDER_ID VARCHAR2(13) NOT NULL,
	-- 注文を明細毎に一意に識別するための番号
	ORDER_DTL_NO NUMBER(3,0) DEFAULT 1 NOT NULL,
	-- 注文商品を一意に識別するためのID
	ORDER_GOODS_ID VARCHAR2(13) NOT NULL,
	-- 注文商品数量
	ORDER_GOODS_NUM NUMBER(3,0) NOT NULL,
	-- 注文商品単価
	ORDER_GOODS_UNIT_PRICE NUMBER(10,0) NOT NULL,
	-- 最終更新ユーザID
	UPD_USER_ID VARCHAR2(13),
	-- 最終更新日時
	UPD_DATE DATE,
	PRIMARY KEY (ORDER_ID, ORDER_DTL_NO)
);


CREATE TABLE USR
(
	-- ユーザを一意に識別するID
	USR_ID VARCHAR2(13) NOT NULL,
	-- ユーザ名
	USR_NAME VARCHAR2(60) NOT NULL,
	-- ユーザパスワード : DBMS_CRYPTOによる暗号
	USR_PWD VARCHAR2(128),
	-- 削除フラグ
	-- 0:存在
	-- 1:削除済み
	DEL_FLG CHAR(1) DEFAULT '0' NOT NULL,
	-- 最終更新ユーザID
	UPD_USER_ID VARCHAR2(13),
	-- 最終更新日時
	UPD_DATE DATE,
	PRIMARY KEY (USR_ID)
);



/* Create Foreign Keys */

ALTER TABLE ORDERS_DTL
	ADD FOREIGN KEY (ORDER_ID)
	REFERENCES ORDERS (ORDER_ID)
;



/* Create Views */

-- 存在しているユーザ情報のビュー
CREATE VIEW M_USR AS SELECT
  *
FROM
  USR
WHERE
  USR.DEL_FLG = '0';



/* Create Indexes */

CREATE INDEX ORDERS_F1 ON ORDERS (ORDER_USR_ID);
CREATE INDEX ORDER_DTL_F1 ON ORDERS_DTL (ORDER_GOODS_ID);



/* Comments */

COMMENT ON COLUMN GOODS.GOODS_ID IS '商品を一意に識別するID';
COMMENT ON COLUMN GOODS.GOODS_NAME IS '商品名';
COMMENT ON COLUMN GOODS.UPD_USER_ID IS '最終更新ユーザID';
COMMENT ON COLUMN GOODS.UPD_DATE IS '最終更新日時';
COMMENT ON COLUMN ORDERS.ORDER_ID IS '注文を一意に識別するためのID';
COMMENT ON COLUMN ORDERS.ORDER_USR_ID IS '注文ユーザを一意に識別するID';
COMMENT ON COLUMN ORDERS.ORDER_DATE IS '注文日時';
COMMENT ON COLUMN ORDERS.UPD_USER_ID IS '最終更新ユーザID';
COMMENT ON COLUMN ORDERS.UPD_DATE IS '最終更新日時';
COMMENT ON COLUMN ORDERS_DTL.ORDER_ID IS '注文を一意に識別するためのID';
COMMENT ON COLUMN ORDERS_DTL.ORDER_DTL_NO IS '注文を明細毎に一意に識別するための番号';
COMMENT ON COLUMN ORDERS_DTL.ORDER_GOODS_ID IS '注文商品を一意に識別するためのID';
COMMENT ON COLUMN ORDERS_DTL.ORDER_GOODS_NUM IS '注文商品数量';
COMMENT ON COLUMN ORDERS_DTL.ORDER_GOODS_UNIT_PRICE IS '注文商品単価';
COMMENT ON COLUMN ORDERS_DTL.UPD_USER_ID IS '最終更新ユーザID';
COMMENT ON COLUMN ORDERS_DTL.UPD_DATE IS '最終更新日時';
COMMENT ON COLUMN USR.USR_ID IS 'ユーザを一意に識別するID';
COMMENT ON COLUMN USR.USR_NAME IS 'ユーザ名';
COMMENT ON COLUMN USR.USR_PWD IS 'ユーザパスワード : DBMS_CRYPTOによる暗号';
COMMENT ON COLUMN USR.DEL_FLG IS '削除フラグ
0:存在
1:削除済み';
COMMENT ON COLUMN USR.UPD_USER_ID IS '最終更新ユーザID';
COMMENT ON COLUMN USR.UPD_DATE IS '最終更新日時';



