package com.xiaozhi.common.exception;

/**
 * Created by bxl on 2018/1/23.
 */
public enum ErrorCode {
    needLogin("-1", "登录超时或过期，请重新登录"),
    sysError("9999", "系统异常"),
    authError("9998", "系统异常"),
    sysCodeError("9997", "获取字典异常"),
    sysFieldError("9996", "系统字段异常"),
    sysOptionError("9995", "人才标签异常"),
    sysTemplateError("9994", "模板设置异常"),
    sysChannelError("9993", "推荐职位获取异常"),
    sysChinaError("9992", "地区获取异常"),
    
    NoneError("1000", "未知错误"),
    RunError1("1001", "系统异常1001"),
    RunError2("1002", "系统异常1002"),
    RunError3("1003", "系统异常1003"),
    ResumeSearchError("2010","简历筛选列表异常"),
    ResumeDictonaryError("2020","简历文件夹列表异常"),
    ResumeInviteError("2011","设置待邀约异常"),
    ResumeLinkJobError("2012","关联职位异常"),
    ResumeInfoViewError("2013","查看简历信息异常"),
    JobError("30001","查询职位列表异常"),
    JobDetailError("30002","查询职位详情异常"),
    SincerityLibError1("40001","查询诚信库列表异常"),
    SincerityLibError2("40002","添加或删除诚信库异常"),
    SincerityLibError3("40003","简历已存在诚信库中,请勿重复添加"),
    MessageSendError1("50001","查询未读消息总数异常"),
    MessageSendError2("50002","查询未读消息详情异常"),
    CallError("60001","通话记录异常"),
    
    
    INTERVIEW_ERROR("50000","面试接口异常"),
    INTERVIEW_RESUME_EMPTY("50001","简历ID不能为空"),
    INTERVIEW_ER_EMPTY("50002","面试官不能为空"),
    INTERVIEW_TIME_ERROR("50003","面试时间不能为空"),
    INTERVIEW_REFUSE_TYPE_EMPTY("50004","请选择拒绝类型"),
    INTERVIEW_PLAN_EMPTY("50005","请选择面试阶段"),
    INTERVIEW_FEEDBACK_EMPTY("50006","反馈信息不能为空"),
    INTERVIEW_TYPE_EMPTY("50007","面试阶段不能为空"),
    INTERVIEW_RESUME_NOT_EXIST("50008","简历不存在"),
    INTERVIEW_SMS_NOT_BALANCE("50009","面试信息保存成功，通信币余额不足系统无法发送短信通知！"),
    INTERVIEW_SMS_ERROR("50019","面试信息保存成功，短信接口错误，短信发送失败！"),
    
    EMAIL_ERROR("70000","判断是否绑定邮箱异常"),
    EMAIL_ERROR1("70001","发送邮箱绑定邮件异常"),
    EMAIL_ERROR2("70002","绑定邮件异常"),
    EMAIL_ERROR3("70003","解绑邮箱发送验证码失败"),
    EMAIL_ERROR4("70004","邮箱解绑验证码校验异常"),
    EMAIL_ERROR5("70005","发送邮件失败"),
    EMAIL_ERROR6("70006","邮箱用户名或密码不正确"),
    EMAIL_ERROR7("70007","验证邮箱密码发生未知错误!"),
    EMAIL_ERROR8("70008","发送绑定验证码失败!"),
    
    
    IMPORT_ERROR_SHOW("60001", "显示导入错误资源异常"),
    EXPORT_MEMMBER("60002", "员工导出异常"),
    MEMBER_HAVE_SAME_CARD("60003","已存在相同身份证员工"),
    MEMBER_HAVE_SAME_PHONE("60004","已存在相同移动电话员工"),
    MEMBER_ERROR("60005","员工异常"),
    
    TALENT_HAVE_SAME("80001","该简历已进入人才库"),
    TALENT_AHVE_RELATED_JOB("80002","该简历已与所选职位关联,请重新选择！"),
    
    ACCOUNT_LIST("90001","账户列表异常"),
    ACCOUNT_ADD("90002","新增账户失败"),
    ACCOUNT_EDIT("90003","编辑账户失败"),
    ACCOUNT_DELETE("90004","清除账户失败"),
    ACCOUNT_CHANGE_ADMIN("90005","管理员交接失败"),
    ACCOUNT_INFO("90006","获取账号失败"),
    ACCOUNT_REAL_NAME("90007","帐号实名认证失败"),
    ACCOUNT_WITHDRAW("90008","奖励金提现失败"),
    ACCOUNT_BOUNTY_RECORD("90009","奖励金记录获取失败"),
    ACCOUNT_BOUNTY_WITHDRAW_RECORD("90010","奖励金提现记录失败"),
    ACCOUNT_EXIST("90011","当前账号已存在于其他单位下"),
    ACCOUNT_CAN_NOT_ADD("90012","试用单位不允许添加HR，面试官等角色用户"),
    
    //robot_error
    MORE_THAN_TOTAL("20000","所加入资源大于机器人库容,请重新输入"),
    NOT_OWN_RES_TO_RES("20001","非自己资源无法取回"),
    NOT_OWN_RES_TO_INT("20002","非自己资源无法取回意向"),
    NOT_OWN_RES_TO_SEA("20003","非自己资源无法放入公海"),
    
    ;
    
    private String code;
    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code;
    }
}
