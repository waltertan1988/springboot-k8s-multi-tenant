package org.walter.app.constant;

import lombok.Getter;

@Getter
public enum MultiTenantDataSourceTypeEnum {

    FUND("fund", "清结算"),
    PRODUCT("product", "商品");

    private String code;
    private String desc;

    MultiTenantDataSourceTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
