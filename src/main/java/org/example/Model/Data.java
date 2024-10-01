package org.example.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

    private String group;
    private String type;
    private long number;
    private long weight;


    public Data() {
    }

    public Data(String group, String type, long number, long weight) {
        this.group = group ;
        this.type = type;
        this.number = number;
        this.weight = weight;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("number")
    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @JsonProperty("weight")
    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

}
