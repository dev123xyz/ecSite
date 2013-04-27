-- データ削除
DELETE FROM GOODS;
-- データ追加
BEGIN
  FOR i IN 1..100000
  LOOP
    INSERT
    INTO GOODS
      (
        GOODS_ID,
        GOODS_NAME,
        UPD_USER_ID,
        UPD_DATE
      )
      VALUES
      (
        'GDS'
        || i,
        '商品名称'
        || i,
        'USR'
        || i,
        CURRENT_DATE
      );
  END LOOP;
END;
