SELECT * FROM (SELECT row_number() over () rownum_, t.* FROM ( SELECT *    FROM (SELECT A.*,                 CASE                   WHEN B.USE_OBJ IS NOT NULL THEN                    1                   ELSE                    2                 END SCOPE            FROM (SELECT *                    FROM (SELECT ROW_NUMBER() OVER(PARTITION BY VER.VER_OBJ_ID ORDER BY VER.SETT_MONTH DESC) RN,                                 MOD.MOD_ID,                                 MOD.MOD_NAME,                                 MOD.MOD_TYPE,                                 MOD.EPARCHY_CODE,                                 MOD.RULE_TYPE_CODE AS BUSI_TYPE_CODE,                                 MOD.DS_ID,                                 MOD.REMARK AS MOD_REMARK,                                 MOD.STATE,                                 VER.SETT_MONTH,                                 VER.VER_ID,                                 TO_CHAR(VER.RELEASE_TIME,                                         'YYYY-MM-DD HH24:MM:SS') RELEASE_TIME,                                 VER.RELEASE_STAFF_ID,                                 (SELECT COUNT(DISTINCT RULE_ID)                                    FROM V_MOD_INVOKE                                   WHERE MOD_ID = MOD.MOD_ID) INVOKE_NUM                            FROM RWD_CORE_VER VER, RWD_CORE_MOD_VER MOD                           WHERE 1 = 1                             AND VER.VER_ID = MOD.VER_ID                             AND VER.VER_OBJ_ID = MOD.MOD_ID                             AND VER.VER_OBJ_TYPE = 'MOD'                             AND VER.STATE = 'CAL'                             AND ('1' = coalesce(VER.SETT_MONTH, '1') OR                                 VER.SETT_MONTH <= coalesce( ? ,'205012') )) foo                   WHERE RN = 1) A left join                  (SELECT *                    FROM (SELECT DISTINCT A.USE_OBJ,                                          COUNT(1) OVER(PARTITION BY USE_OBJ) CNT,                                          SUM(case when D.DIMSCH_ID is not null then 1 else 0 end) OVER(PARTITION BY USE_OBJ) SUMRES                            FROM RWD_CORE_PARAM_USE_VER A                                 left join RWD_CORE_PARAM B on A.PARAM_ID::numeric = B.PARAM_ID                                left join RWD_CORE_MOD_VER C on A.USE_OBJ = C.MOD_ID                                 left join (SELECT C.SCH_ID DIMSCH_ID, B.SCH_ID                                    FROM RWD_CORE_DATASETDIM A,                                         RWD_CORE_DATASET    B,                                         RWD_CORE_DIM        C                                   WHERE A.DATASET_ID = B.DATASET_ID                                     AND C.DIM_ID = A.DIM_ID) D on  B.SCH_ID = D.DIMSCH_ID                                 left join RWD_CORE_VER VER on VER.VER_ID = C.VER_ID AND VER.VER_OBJ_ID = C.MOD_ID                          WHERE                              A.USE_OBJ_TYPE = 'MOD'                             AND VER.VER_OBJ_TYPE = 'MOD'                             AND VER.STATE = 'CAL'                             AND ('1' = coalesce(VER.SETT_MONTH, '1') OR                                 VER.SETT_MONTH <= coalesce( ? ,'205012') )                             AND USE_OBJ NOT IN                                 (SELECT USE_OBJ                                    FROM RWD_CORE_PARAM_USE_VER A,                                         RWD_CORE_PARAM         B                                   WHERE A.PARAM_ID::numeric = B.PARAM_ID                                     AND A.USE_OBJ_TYPE = 'MOD'                                     AND B.SRC_TYPE IN ('DS'))) too                   WHERE CNT = SUMRES) B           on A.MOD_ID = B.USE_OBJ) coo   WHERE 1 = 1  AND (EPARCHY_CODE = ? OR EPARCHY_CODE='*')  ) t) koo WHERE rownum_ >= ? AND rownum_ <= ?
