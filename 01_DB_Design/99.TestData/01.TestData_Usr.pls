-- �f�[�^�폜
DELETE FROM USR;

-- �f�[�^�ǉ�
BEGIN
  FOR i IN 1..10000
  LOOP
    INSERT
    INTO
      USR
      (
        USR_ID,
        USR_NAME,
        USR_PWD,
        UPD_USER_ID,
        UPD_DATE
      )
      VALUES
      (
        'USR' || i,
        '����' || i,
        'PWD' || i,
        'USR' || i,
        CURRENT_DATE
      );
  END LOOP;
END;
