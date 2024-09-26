package org.example.Model;

public class Data {
    private String group;

    private String type;

    private long number;

    private long weight;

    public Data (){}

    public Data(String group, String type, long number, long weight) {
        this.group = group;
        this.type = type;
        this.number = number;
        this.weight = weight;
    }

    public String getGroup(){
        return group;
    }

    public void setGroup(String group){
        this.group = group;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public long getNumber(){
        return number;
    }

    public void setNumber(long number){
        this.number = number;
    }

    public long getWeight(){
        return weight;
    }

    public void setWeight(long weight){
        this.weight = weight;
    }

}
