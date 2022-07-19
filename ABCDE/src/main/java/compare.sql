INSERT INTO ODS.dws_ins_premium_d
(
channel_code,
goods_code,
risk_code,
class_code,
com_code,
lvl_1_com_code,
lvl_2_com_code,
lvl_3_com_code,
business_type_code,
business_net_code,
written_premium,
premium_income,
create_time,
update_time,
etl_update_time,
yr,
mo,
dt
)
select
channel_code,
goods_code,
risk_code,
class_code,
com_code,
lvl_1_com_code,
lvl_2_com_code,
lvl_3_com_code,
business_type_code,
business_net_code,
SUM(written_premium) AS written_premium,
SUM(premium_income) AS premium_income,
SYSDATE AS create_time,
SYSDATE AS update_time,
SYSDATE AS etl_update_time,
yr,
mo,
dt
from
(
--签单保费
select
t.businessnature as channel_code,--改取业务来源
t.goodscode as goods_code,
t.RISKCODE as risk_code,
t.CLASSCODE as class_code,
t.comcode as com_code,
t2.lvl_1_com_code as lvl_1_com_code,
t2.lvl_2_com_code as lvl_2_com_code,
t2.lvl_3_com_code as lvl_3_com_code,
t3.business_type_code as business_type_code,
DECODE(T.NETBUSINESSFLAG,'11','Y','N') AS business_net_code,
SUM(T.sumpremiumnt) AS written_premium,
0 AS premium_income,
SYSDATE AS create_time,
SYSDATE AS update_time,
SYSDATE AS etl_update_time,
TO_CHAR(T.underwriteenddate,'YYYY') AS yr,
TO_CHAR(T.underwriteenddate,'YYYY-MM') AS mo,
TO_CHAR(T.underwriteenddate,'YYYY-MM-DD') AS dt
from ODS.T_JCFX_PREMIUM_BFSR_SS_DAY t
left join ODS.DIM_PUB_COMPANY t2 on t.comcode = t2.com_code --机构维表
left join ODS.dim_pub_biz_type T3 ON T.BUSINESSCLASSMAPPING = T3.specific_business_type_code --业务类型维表
where 1=1
--and t.POLICYNO='200020020081101000287'
and t.underwriteenddate>=ADD_MONTHS(trunc(sysdate-1,'YY'),-36) --最近4个自然年的数据
and t.underwriteenddate<trunc(sysdate) --截止前一天的数据
and T.sumpremiumnt <>0 --金额不为0，避免一些普通批改的也进来，数据量比较大
group by t.businessnature,t.goodscode,t.RISKCODE,t.CLASSCODE,t.comcode,
t2.lvl_1_com_code,t2.lvl_2_com_code,t2.lvl_3_com_code,
t3.business_type_code,DECODE(T.NETBUSINESSFLAG,'11','Y','N') ,
TO_CHAR(T.underwriteenddate,'YYYY') ,
TO_CHAR(T.underwriteenddate,'YYYY-MM'),
TO_CHAR(T.underwriteenddate,'YYYY-MM-DD')
union all
--保费收入
select
t.businessnature as channel_code,
t.goodscode as goods_code,
t.RISKCODE as risk_code,
t.CLASSCODE as class_code,
t.comcode as com_code,
t2.lvl_1_com_code as lvl_1_com_code,
t2.lvl_2_com_code as lvl_2_com_code,
t2.lvl_3_com_code as lvl_3_com_code,
t3.business_type_code as business_type_code,
DECODE(T.NETBUSINESSFLAG,'11','Y','N') AS business_net_code,
0 AS written_premium,
SUM(T.sumpremiumnt) AS premium_income,
SYSDATE AS create_time,
SYSDATE AS update_time,
SYSDATE AS etl_update_time,
TO_CHAR(T.checkdate,'YYYY') AS yr,
TO_CHAR(T.checkdate,'YYYY-MM') AS mo,
TO_CHAR(T.checkdate,'YYYY-MM-DD') AS dt
from ODS.T_JCFX_PREMIUM_BFSR_SS_DAY t
left join ODS.DIM_PUB_COMPANY t2 on t.comcode = t2.com_code
left join ODS.dim_pub_biz_type T3 ON T.BUSINESSCLASSMAPPING = T3.specific_business_type_code
where 1=1
--and t.POLICYNO='264010020212303000033'
and t.checkdate>=ADD_MONTHS(trunc(sysdate-1,'YY'),-36) --最近4个自然年的数据
and t.checkdate<trunc(sysdate)
and T.sumpremiumnt <>0 --金额不为0
group by t.businessnature,t.goodscode,t.RISKCODE,t.CLASSCODE,t.comcode,
t2.lvl_1_com_code,t2.lvl_2_com_code,t2.lvl_3_com_code,
t3.business_type_code,DECODE(T.NETBUSINESSFLAG,'11','Y','N') ,
TO_CHAR(T.checkdate,'YYYY') ,
TO_CHAR(T.checkdate,'YYYY-MM'),
TO_CHAR(T.checkdate,'YYYY-MM-DD')
) mm
group by
channel_code,
goods_code,
risk_code,
class_code,
com_code,
lvl_1_com_code,
lvl_2_com_code,
lvl_3_com_code,
business_type_code,
business_net_code,
yr,
mo,
dt
;