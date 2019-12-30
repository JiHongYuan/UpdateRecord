package com.example.update.record.domain;

import com.example.update.record.annotation.Update;

/**
 * @author jihongyuan
 * @date 2019/12/7 0:39
 */
public class UpdateField {

    private String field;
    private Update update;
    private Object property;

    public UpdateField() {
    }

    public UpdateField(String field, Update update, Object property) {
        this.field = field;
        this.update = update;
        this.property = property;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public Object getProperty() {
        return property;
    }

    public void setProperty(Object property) {
        this.property = property;
    }
}
