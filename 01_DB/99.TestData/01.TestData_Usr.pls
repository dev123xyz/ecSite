-- �f�[�^�폜
DELETE FROM USR;

-- �f�[�^�ǉ�
BEGIN
  FOR i IN 1..1000
  LOOP
    INSERT
    INTO
      USR
      (
        USR_ID,
        USR_NAME,
        USR_PWD,
        INVALID_FLG,
        UPD_USER_ID,
        UPD_DATE
      )
      VALUES
      (
        'USR' || i,
        '���[�U����' || i,
        'PWD' || i,
        '0',
        'USR' || i,
        CURRENT_DATE
      );
  END LOOP;
END;
