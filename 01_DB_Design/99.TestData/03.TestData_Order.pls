-- データ削除
DELETE FROM ORDERS_DTL;
DELETE FROM ORDERS;

-- データ追加
BEGIN
  FOR i IN 1..10000
  LOOP
    INSERT
    INTO ORDERS
      (
        ORDER_ID,
        ORDER_USR_ID,
        ORDER_DATE,
        UPD_USER_ID,
        UPD_DATE
      )
      VALUES
      (
        'ODR'
        || i,
        'USR'
        || i,
        CURRENT_DATE,
        'USR'
        || i,
        CURRENT_DATE
      );
  END LOOP;
END;

BEGIN
  FOR i IN 1..10000
  LOOP
    INSERT
    INTO ORDERS_DTL
      (
        ORDER_ID,
        ORDER_DTL_NO,
        ORDER_GOODS_ID,
        ORDER_GOODS_NUM,
        ORDER_GOODS_UNIT_PRICE,
        UPD_USER_ID,
        UPD_DATE
      )
      VALUES
      (
        'ODR'
        || i,
        1,
        'GDS'
        || i,
        2,
        3,
        'USR'
        || i,
        CURRENT_DATE
      );
  END LOOP;
END;
