package com.shenlanbao.sqldemo.common.model;

public enum OrderTypeEnum {
    FAMILY_INSUANCE("family_insuance", "家庭保险规划"),
    SINGLE_PRODUCT("single_product", "单产品"),
    CUSTOMER_SERVICE("customer_service", "客服"),
    OPERATION("operation", "运营");


    private String code;
    private String value;

    OrderTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getNameByCode(String code) {
        for(OrderTypeEnum type : OrderTypeEnum.values()){
            if(code.equals(type.getCode())){
                return type.getValue();
            }
        }
        return null;
    }

    public static String getCodeByName(String name) {
        for(OrderTypeEnum type : OrderTypeEnum.values()){
            if(name.equals(type.getValue())){
                return type.getCode();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


}
