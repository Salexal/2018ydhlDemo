package com.example.a92385.a2018ydhldemo.PrePaidRecords;

import java.util.Date;

public class PrepaidRecord {

    private String recordId;
    private String carId;
    private String money;
    private String theOperator;
    private String recordTime;

    public PrepaidRecord(){

    }

    public PrepaidRecord(String recordId, String carId, String money, String theOperator, String recordTime) {
        this.recordId = recordId;
        this.carId = carId;
        this.money = money;
        this.theOperator = theOperator;
        this.recordTime = recordTime;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTheOperator() {
        return theOperator;
    }

    public void setTheOperator(String theOperator) {
        this.theOperator = theOperator;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
