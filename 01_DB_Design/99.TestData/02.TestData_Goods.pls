-- �f�[�^�폜
DELETE FROM GOODS;
-- �f�[�^�ǉ�
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
        '���i����'
        || i,
        'USR'
        || i,
        CURRENT_DATE
      );
  END LOOP;
END;
