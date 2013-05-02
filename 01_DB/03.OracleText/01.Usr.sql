-- ユーザデータストア・プロシージャ : ユーザ情報
CREATE OR REPLACE
PROCEDURE PROC_DATASTORE_USR(
    rid  IN rowid,
    tlob IN OUT NOCOPY CLOB)
IS
BEGIN
  FOR c1 IN
  (SELECT USR_ID,
    USR_NAME,
    USR_PWD,
    DECODE(INVALID_FLG,'0','有効','1','無効') INVALID_FLG,
    ORDERS.ORDER_ID,
    ORDERS.ORDER_DATE,
    GOODS.GOODS_NAME,
    ORDERS_DTL.ORDER_GOODS_NUM,
    ORDERS_DTL.ORDER_GOODS_UNIT_PRICE
  FROM USR
  LEFT OUTER JOIN ORDERS
  ON USR.USR_ID = ORDERS.ORDER_USR_ID
  LEFT OUTER JOIN ORDERS_DTL
  ON ORDERS.ORDER_ID = ORDERS_DTL.ORDER_ID
  LEFT OUTER JOIN GOODS
  ON ORDERS_DTL.ORDER_GOODS_ID = GOODS.GOODS_ID
  WHERE Usr.rowid              = rid
  )
  LOOP
    IF( c1.USR_ID IS NOT NULL ) THEN
      -- ユーザID
      dbms_lob.writeappend(tlob, LENGTH(c1.USR_ID), c1.USR_ID);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.USR_NAME IS NOT NULL ) THEN
      -- ユーザ名称
      dbms_lob.writeappend(tlob, LENGTH(c1.USR_NAME), c1.USR_NAME);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.USR_PWD IS NOT NULL ) THEN
      -- ユーザPWD
      dbms_lob.writeappend(tlob, LENGTH(c1.USR_PWD), c1.USR_PWD);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.INVALID_FLG IS NOT NULL ) THEN
      -- 無効フラグ
      dbms_lob.writeappend(tlob, LENGTH(c1.INVALID_FLG), c1.INVALID_FLG);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.ORDER_ID IS NOT NULL ) THEN
      -- 注文ID
      dbms_lob.writeappend(tlob, LENGTH(c1.ORDER_ID), c1.ORDER_ID);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.ORDER_DATE IS NOT NULL ) THEN
      -- 注文日時
      dbms_lob.writeappend(tlob, LENGTH(c1.ORDER_DATE), c1.ORDER_DATE);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.GOODS_NAME IS NOT NULL ) THEN
      -- 商品名称
      dbms_lob.writeappend(tlob, LENGTH(c1.GOODS_NAME), c1.GOODS_NAME);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.ORDER_GOODS_NUM IS NOT NULL ) THEN
      -- 注文数量
      dbms_lob.writeappend(tlob, LENGTH(c1.ORDER_GOODS_NUM), c1.ORDER_GOODS_NUM);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
    IF( c1.ORDER_GOODS_UNIT_PRICE IS NOT NULL ) THEN
      -- 注文単価
      dbms_lob.writeappend(tlob, LENGTH(c1.ORDER_GOODS_UNIT_PRICE), c1.ORDER_GOODS_UNIT_PRICE);
      dbms_lob.writeappend(tlob, 1, ' ');
    END IF;
  END LOOP;
END;


-- プリファレンスの作成
EXEC ctx_ddl.drop_preference('USER_DATASTORE_USR');
EXEC ctx_ddl.create_preference('USER_DATASTORE_USR', 'USER_DATASTORE');
EXEC ctx_ddl.set_attribute('USER_DATASTORE_USR', 'PROCEDURE', 'PROC_DATASTORE_USR');
EXEC ctx_ddl.set_attribute('USER_DATASTORE_USR', 'OUTPUT_TYPE', 'CLOB');


-- Oracle Textインデックスを作成
-- 作成には10分以上かかることがある

DROP INDEX USR_FI;

CREATE INDEX USR_FI ON USR( UPD_USER_ID )
  INDEXTYPE IS CTXSYS.CONTEXT PARAMETERS ('
    DATASTORE USER_DATASTORE_USR
    LEXER     MY_USER_LEXER
    STOPLIST  MY_USER_STOPLIST
    SYNC(ON COMMIT)
');

