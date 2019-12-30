package com.example.update.record.enums;

/**
 * @author jihongyuan
 * @date 2019/12/4 18:45
 */
public enum UpdateRecordEnum {
    /**
     *  其他
     * */
    OTHER("0"),
    /**
     * 终端信息
     */
    RESOURCE("1"),
    ;
    private final String type;

    UpdateRecordEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
