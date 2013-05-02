
-- Oracle Textを使用するために以下の権限が必要となる
GRANT CTXAPP TO ユーザ名;
GRANT EXECUTE ON CTX_ULEXER TO ユーザ名;

-- プロシージャの作成は一つずつ実行すること

-- ユーザレクサ INDEX_PROCEDURE
CREATE OR REPLACE
PROCEDURE USER_LEXER_INDEX_PROC(
    v_a IN VARCHAR2,
    v_b IN OUT CLOB,
    v_c IN BOOLEAN )
IS
  V_A_TRAN clob;
  v_len NUMBER;
begin
--  DBMS_OUTPUT.PUT_LINE('開始');
  V_A_TRAN := UTL_I18N.TRANSLITERATE(TO_SINGLE_BYTE( UPPER(v_a) ), 'kana_fwkatakana');
  v_len    := LENGTH(V_A_TRAN);
  v_b      := '<tokens>';
  FOR i IN 1 .. v_len - 1
  LOOP
    V_B := V_B || '<word>' || SUBSTR(V_A_TRAN, I, 2) || '</word>';
  END LOOP;
  V_B := V_B || '<word>' || SUBSTR(V_A_TRAN, V_LEN, 1) || '</word></tokens>';
--  DBMS_OUTPUT.PUT_LINE(v_b);
END USER_LEXER_INDEX_PROC;


-- ユーザレクサ QUERY_PROCEDURE
CREATE OR REPLACE
PROCEDURE USER_LEXER_QUERY_PROC(
    v_a IN VARCHAR2,
    v_b IN CTX_ULEXER.WILDCARD_TAB,
    v_c IN OUT VARCHAR2 )
IS
  v_a_tran CLOB;
  v_len    NUMBER;
BEGIN
  v_a_tran := UTL_I18N.TRANSLITERATE(TO_SINGLE_BYTE( UPPER(v_a) ), 'kana_fwkatakana');
  v_len    := LENGTH(v_a_tran);
  v_c      := '<tokens>';
  IF v_len >= 2 THEN
    FOR i IN 1 .. v_len - 1
    LOOP
      v_c := v_c || '<word>' || SUBSTR(v_a_tran, i, 2) || '</word>';
    END LOOP;
  ELSIF v_len = 1 THEN
    v_c      := v_c || '<word wildcard="1">' || v_a_tran || '%</word>';
  END IF;
  V_C := V_C || '</tokens>';
END USER_LEXER_QUERY_PROC;


-- プリファレンスの作成
EXEC ctx_ddl.drop_preference('MY_USER_LEXER');
EXEC ctx_ddl.create_preference('MY_USER_LEXER', 'USER_LEXER');
EXEC ctx_ddl.set_attribute ('MY_USER_LEXER', 'INDEX_PROCEDURE', 'USER_LEXER_INDEX_PROC');
EXEC ctx_ddl.set_attribute('MY_USER_LEXER', 'INPUT_TYPE', 'CLOB');
EXEC ctx_ddl.set_attribute ('MY_USER_LEXER', 'QUERY_PROCEDURE', 'USER_LEXER_QUERY_PROC');


-- ストップリストの作成
EXEC ctx_ddl.drop_stoplist('MY_USER_STOPLIST');
EXEC ctx_ddl.create_stoplist('MY_USER_STOPLIST', 'BASIC_STOPLIST');

