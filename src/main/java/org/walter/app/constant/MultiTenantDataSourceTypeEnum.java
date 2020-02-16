package org.walter.app.constant;

import lombok.Getter;

@Getter
public enum MultiTenantDataSourceTypeEnum {

    FUND("fund", "清结算");

    private String code;
    private String desc;

    MultiTenantDataSourceTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
