insert into rwd_core.rwd_ext_cal_clear_account_v(sett_month,eparchy_code,bs_id,policy_id,object_id,record_id,money_difference,result_difference, city_code, object_code,as_id,bs_name,object_name,rule_type,pay_month,create_time)
select sett_month,eparchy_code,bs_id,policy_id,object_id,record_id,money_difference,result_difference, city_code, object_code,as_id,bs_name,object_name,rule_type,#{payMonth},current_timestamp from (
    select
    clear.*,
    #{currentRecodeId}                                                 as record_id,
    b.bs_name,
    c.chnl_name                                                        as object_name,
    c.mgmt_county                                                      as city_code,
    c.chnl_code                                                        as object_code,
    b.as_id,
    s.pdata_id 														   as rule_type
    from(
    select
    coalesce(current.sett_month, last.sett_month)                         sett_month,
    coalesce(current.eparchy_code, last.eparchy_code)                     eparchy_code,
    coalesce(current.policy_id, last.policy_id)                           policy_id,
    coalesce(current.sett_obj, last.sett_obj)                          as object_id,
    coalesce(current.bs_id, last.bs_id)                                as bs_id,
    coalesce(current.total_money, 0) - coalesce(last.total_money, 0)   as money_difference,
    coalesce(current.total_result, 0) - coalesce(last.total_result, 0) as result_difference
    from
    (
    select
    r.sett_month,
    r.sett_obj,
    r.eparchy_code,
    r.policy_id,
    r.bs_id,
    sum(r.sett_money)  total_money,
    sum(r.sett_result) total_result
    from ${currentToTable} r
    where policy_id = #{policyId} and sett_month = #{settMonth} and eparchy_code = #{eparchyCode}
    group by r.sett_month, r.sett_obj, r.eparchy_code, r.policy_id, r.bs_id) current
    full join
    (
    select r.sett_month, r.sett_obj, r.eparchy_code, r.policy_id, r.bs_id, sum(r.sett_money) total_money, sum(r.sett_result) total_result
    from ${lastToTable} r
    where policy_id =#{policyId} and sett_month =#{settMonth} and eparchy_code =#{eparchyCode}
    group by r.sett_month, r.sett_obj, r.eparchy_code, r.policy_id, r.bs_id
    ) last on current.sett_obj = last.sett_obj  and current.bs_id = last.bs_id) clear
    left join rwd_data.sp_chnl c on clear.object_id = c.chnl_id
    left join rwd_core.rwd_ext_bizsubject b on clear.bs_id = b.bs_id
    left join rwd_core.rwd_core_policy p on clear.policy_id::numeric = p.policy_id and  p.state = 'F0A' and p.eparchy_codes like '%'||#{eparchyCode}||'%' and p.sett_month = #{settMonth}
    left join rwd_core.rwd_core_rule r  on p.rule_plan_id = r.plan_id and p.rule_id = r.rule_id
    left join rwd_core.rwd_core_static s on r.rule_type_code = s.data_id  and type_id='RULE_TYPE_CODE') foo