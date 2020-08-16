package com.shenlanbao.sqldemo.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class ApiResult<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final ApiResult SUCCESS = new ApiResult(STATE.SUCCESS);
    private int code;
    private String message;
    private T data;
    private Integer ttl;

    private ApiResult() {
    }

    public ApiResult(STATE state) {
        this.code = state.getCode();
        this.message = state.getMessage();
    }

    public ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(STATE state, String message) {
        this.code = state.getCode();
        this.message = message;
    }

    public ApiResult(STATE state, T data) {
        this.code = state.getCode();
        this.message = state.getMessage();
        this.data = data;
        this.message = state.getMessage();
    }

    @Getter
    @AllArgsConstructor
    public enum STATE {
        SUCCESS(200, "请求成功"),
        FAIL(408,"请求失败"),
        INTERNAL_SYSTEM_ERROR(500, "internal system error"),
        INVALID_PARAM(400, "Invalid param"),
        INTERNAL_AUTHORIZATION_ERROR(401, "认证无效"),
        INTERNAL_NOT_PATH_ERROR(404, "接口不存在"),
        PERMISSION_DENIED(9998, "没有权限"),
        SYSTEM_ERROR(9999, "System error"),
        REPETITIVE_OPERATION(999, "请求重复或失效"),
        SIGN_IN_LIMIT(996, "节假日签入的限制"),

        /*
         *   100        001        001
         * 标识系统     标识模块    标识业务
         */

        /**
         * 用户相关
         **/
        OPERATE_PARAM_EXCEPTION(100020016, "运营创建家保二线订单传入参数异常"),
        INVALID_USERNAME_OR_PASSWORD(100001001, "用户名或密码错误"),
        INVALID_OLD_PASSWORD(100001002, "旧密码错误"),
        WX_MA_LOGIN_ERROR(100001003, "小程序登录接口异常"),
        WX_MP_LOGIN_ERROR(100001004, "公众号登录异常"),
        USER_DISABLED(100001005, "您无权访问该站点.\n 若需要访问请联系管理员将\"账号状态\"改为\"启用\""),
        USER_NONEXISTENT(100001006, "用户名不存在，请核对用户名"),
        INVALID_PASSWORD(100001007, "密码错误，请重新输入"),
        SIGN_IN_FIRST(100001008, "请先签入"),
        REPEAT_SIGN(100001008, "您已经签入过了"),
        USER_NOT_FOUND(100001009, "没有找到该用户"),
        SIGN_IN_SUCCESS(100001010, "签入成功"),
        SIGN_OUT_SUCCESS(100001011, "签出成功"),
        UPDATE_SIGN_OUT_TIME_SUCCESS(100001012, "更新签出时间成功"),

        /**
         * 订单相关
         **/
        ORDER_CANCELLED(100002001, "订单已取消"),
        ORDER_UNPAID(100002002, "订单尚未支付"),
        UPDATE_FOLLOW_UP_FAILED(100002003, "更新订单跟进状态失败"),
        DUPLICATED_ORDER(100002004, "重复订单，同一个客户有多笔已支付订单"),
        ORDER_NOT_FOUND(100002005, "未找到该订单"),
        TRANSFER_ORDER_ERROR(100002006, "该客户无家保订单，不能转出"),
        TASK_ORDER_ERROR(100002007, "任务单创建失败，具体原因需要联系IT进行核查"),
        REPEAT_ORDER_ERROR(100002008, "该手机已有订单，无法创建客服订单。已提醒相关业务人员跟进"),
        /**
         * 制定方案相关
         **/
        FORMEMBER_NOT_BELONG_TO_FAMILYMEMBER(100003001, "该角色不属于家庭成员"),
        FORMEMBER_RELATIONSHIP_ERROR(100003002, "被保人relationship不能为SON,DAUGHTER"),
        MUST_ONE_MEMBER_HAVE_PRODUCT(100003003, "至少需要一个家庭成员有配置产品"),
        PHONE_DUPLICATE(100003004, "当前手机号已被其他订单占用，请与管理员核实"),
        INSURED_PERIOD_LOST(100003005, "获取保障期限失败，请点击“确认”重试"),

        /**
         * 问卷
         **/
        MOBILE_DUPLICATE(100004001, "手机号已经存在"),
        UN_COMPLETE_PHONE(100004002, "该用户未填写手机号"),

        /**
         * 分单
         **/
        CONSULTANT_NOT_FOUND(100005001, "规划师不存在"),
        AUDIT_TAG_MISMATCH(100005002,"复核人员和订单不匹配"),
        CONSULTANT_GROUP_TAG_MISMATCH(100005003,"分组和订单不匹配"),
        CONSULTANT_TAG_MISMATCH(100005004,"规划师和订单不匹配"),

        /**
         * /**
         * 绑定
         **/
        NOT_FOUND_JSJ_CUSTOMER(100006001, "没有查询到该手机号码对应的订单，请重新绑定"),
        SMS_CODE_ERROR(100006002, "发送短信失败"),
        VALIDATE_CODE_ERROR(100006003, "验证码错误，请重新输入"),
        SEND_SMS_ERROR(100006004, "短信服务异常"),
        SEND_SMS_ERROR_PHONE_NOT_FOUND(100006005, "发送短信失败，未找到该客户的手机号"),

        /**
         * 人员管理
         */
        GROUP_EXISTED(100007001, "该小组名称已存在"),
        UPLOAD_IMG_FAILD(100007002, "上传图片失败"),
        GROUP_DIRECTOR_EXISTED(100007003, "一个小组只可有一位主管.\n\n 该小组已有主管,请重新选择小组."),
        GROUP_DIRECTOR_EMPTY(100007004, "小组组长不能为空"),
        USER_EXISTED(100007005, "用户名已经存在"),
        USERNAME_ILLEGAL(100007006, "登录名含有禁用字符，请重新输入。禁用字符有：空格"),
        UPDATE_INFO_FAILD(100007007, "更新用户信息失败"),


        /**
         * 异常订单绑定
         */
        INSURANCE_NOT_FOUNT(100008001, "没有查询到该保单"),
        INSURANCE_MATCHED(100008002, "该保单已经匹配，请重新输入"),
        INSURANCE_APPROVE(100008003, "该保单已被规划师提交审核"),
        INSURANCE_APPROVE_SUCCESS(100008004, "审核操作成功"),
        INSURANCE_APPROVE_FAILD(100008005, "审核操作失败"),
        UPLOAD_EXCEL_FAILD(100008006, "上传EXCEL失败"),
        PARSE_EXCEL_FAILD(100008007, "解析EXCEL失败"),
        INSURANCE_UNPAID(100008008, "该投保单未支付，请支付后再提交"),

        /**
         * 单产品
         */
        SINGLE_PRODUCT_APPOINTMENT_EXITED(100010001, "您已提交过预约,我们的规划师会尽快与您联系"),
        SINGLE_PRODUCT_ORDER_EXISTED(100010002, "该客户已经存在单产品订单"),
        FAMILY_INSURANCE_ORDER_EXISTED(100010003, "该客户已经存在家保订单"),
        PHONE_IS_NULL(100010004, "单产品预约客户手机号为空"),
        CUSTOMER_ID_IS_NULL(100010005, "单产品预约客户id为空"),

        /**
         * RBAC
         */
        PERMISSION_ACTION_EXISTED(100009001, "权限action已经存在"),
        PERMISSION_ROLE_EXISTED(100009002, "角色中文名称已经存在"),
        PERMISSION_ROLE_CODE_EXISTED(100009003, "角色英文名称已经存在"),
        PERMISSION_ROLE_CANT_DELETE(100009004, "该角色不能删除"),
        PERMISSION_ROLE_NAME_CODE_EXISTED(100009005, "角色名称和角色英文名称已经存在"),

        /**
         * 话术模板
         */
        TITLE_OR_CONTENT_EMPTY(100010001, "异议的标题或内容为空"),
        CONSULTANT_ONLY(100010002, "只有规划师可以添加异议"),
        ORDER_CUSTOMER_NOT_FOUND(100010003, "没有找到该订单对应的客户"),
        ORDER_CONSULTANT_NOT_FOUND(100010004, "没有找到该订单对应的规划师"),
        OBJECTION_UPDATE_FAILED(100010005, "异议编辑失败"),
        PLAN_NOT_FOUND(100010006, "未找到该订单下的方案"),

        /**
         * common
         */
        ORDER_CUSTOMER_IS_NULL(100020001, "订单对应的customerId为空"),
        ORDER_INFO_IS_NULL(100020002, "订单客户信息为空"),
        PHONE_WECHAR_IS_NULL(100020003, "电话和微信为空"),
        OBJECT_IS_NULL(100020004, "对象为空"),
        LAUNCH_PARAM_EXCEPTION(100020006, "创建投放名单传入参数异常"),
        LAUNCH_ORDER_REPEAT(100020007, "重复创建订单"),
        UPLOAD_EXCEL_DATA_EXCEPTION(100020005, "表格数据异常"),
        THIRD_PARAM_EXCEPTION(100020008, "创建第三方名单传入参数异常"),
        THIRD_ORDER_REPEAT(100020009, "重复创建订单"),
        GET_TOKEN_ERROR(1000200010, "获取Token异常"),
        INS_COMPANY_ERROR(1000200011, "保司数据操作异常"),
        DUPLICATE_BATCH_NUMBER(1000200012, "批次号已存在，请重新输入"),
        FILE_FORMAT_FALSE(1000200013, "文件格式错误"),
        THIRD_INTEFACE_ERROR(1000200014, "调用第三方接口失败"),
        WX_CODE_EXPIRE_ERROR(1000200015, "code过期"),




        /**
         *  健康信息
         */
        HEALTH_INFO_INSERT(100030014, "健康信息插入失败"),
        HEALTH_INFO_UPDATE(100030015, "健康信息更新失败"),
        HEALTH_INFO_DELETE(100030016, "健康信息删除失败"),

        /**
         *  健康报告
         */
        HEALTH_REPORT_INSERT(100030017, "健康报告插入失败"),
        HEALTH_REPORT_UPDATE(100030018, "健康报告更新失败"),
        HEALTH_REPORT_DELETE(100030019, "健康报告删除失败"),
        HEALTH_REPORT_FILE_DELETE(100030020, "健康报告附件删除失败");


        private int code;
        private String message;

        public void setMessage(String message) {
            this.message = message;
        }

    }


}
