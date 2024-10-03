package com.jingdianjichi.subject.common.enums;

import lombok.Getter;

@Getter
public enum DeletedFlagEnum {

    IS_DELETED(0, "已删除"),
    UN_DELETED(1, "未删除");

    public int code;

    public String desc;

    DeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DeletedFlagEnum getByCode(int codeValue){
        for (DeletedFlagEnum resultCodeEnum : DeletedFlagEnum.values()){
            if(resultCodeEnum.code == codeValue){
                return resultCodeEnum;
            }
        }
        return null;
    }

}
