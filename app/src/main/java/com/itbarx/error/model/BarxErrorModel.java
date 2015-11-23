package com.itbarx.error.model;

import java.util.Date;

/**
 * TODO: Add a class header comment!
 */
public class BarxErrorModel<T> {

    String errResult;
    String errMessage;
    String errData;
    String errDataType;
    Date errDateTime;
Boolean isError;


    public Boolean getIsError() {
        return isError;
    }

    public BarxErrorModel setIsError(Boolean isError) {
        this.isError = isError;
        return this;
    }

    public Date getErrDateTime() {
        return errDateTime;
    }

    public BarxErrorModel setErrDateTime(Date errDateTime) {
        this.errDateTime = errDateTime;
        return this;
    }

    public String getErrResult() {
        return errResult;
    }

    public BarxErrorModel setErrResult(String errResult) {
        this.errResult = errResult;
        return this;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public BarxErrorModel setErrMessage(String errMessage) {
        this.errMessage = errMessage;
        return this;
    }

    public String getErrData() {
        return errData;
    }

    public BarxErrorModel setErrData(String errData) {
        this.errData = errData;
        return this;
    }

    public String getErrDataType() {
        return errDataType;
    }

    public BarxErrorModel setErrDataType(String errDataType) {
        this.errDataType = errDataType;
        return this;
    }


}
