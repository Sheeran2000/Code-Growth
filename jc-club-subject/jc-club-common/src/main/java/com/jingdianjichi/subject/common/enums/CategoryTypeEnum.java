package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum CategoryTypeEnum {

    PRIMARY(1, "岗位大类"),
    SECOND(2, "二级分类");

    public int code;

    public String desc;

    CategoryTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int codeValue){
        for (CategoryTypeEnum resultCodeEnum : CategoryTypeEnum.values()){
            if(resultCodeEnum.code == codeValue){
                return resultCodeEnum;
            }
        }
        return null;
    }

}
