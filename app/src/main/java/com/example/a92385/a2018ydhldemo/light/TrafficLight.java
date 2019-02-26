package com.example.a92385.a2018ydhldemo.light;

public class TrafficLight {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String lightId;
    private String redLight;
    private String yellowLight;
    private String greenLight;

    public TrafficLight(){

    }

    public TrafficLight(String lightId, String redLight, String yellowLight, String greenLight) {
        this.lightId = lightId;
        this.redLight = redLight;
        this.yellowLight = yellowLight;
        this.greenLight = greenLight;
    }

    public String getLightId() {
        return lightId;
    }

    public void setLightId(String lightId) {
        this.lightId = lightId;
    }

    public String getRedLight() {
        return redLight;
    }

    public void setRedLight(String redLight) {
        this.redLight = redLight;
    }

    public String getYellowLight() {
        return yellowLight;
    }

    public void setYellowLight(String yellowLight) {
        this.yellowLight = yellowLight;
    }

    public String getGreenLight() {
        return greenLight;
    }

    public void setGreenLight(String greenLight) {
        this.greenLight = greenLight;
    }
}
