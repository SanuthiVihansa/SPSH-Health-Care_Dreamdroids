package com.spsh.spshhealthcare.models;

public class Report {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String nic;
    private String date;
    private String time;
    private double cost;
    private double hemoglobin;
    private int wbc;
    private double neutrophils;
    private double lymphocytes;
    private double eosinophils;
    private double rbc;
    private double pcb ;
    private int platelet;

    public Report(int id, String name, int age, String gender, String nic, String date, String time, double cost, double hemoglobin, int wbc, double neutrophils, double lymphocytes, double eosinophils, double rbc, double pcb, int platelet){
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.nic = nic;
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.hemoglobin = hemoglobin;
        this.wbc = wbc;
        this.neutrophils = neutrophils;
        this.lymphocytes = lymphocytes;
        this.eosinophils = eosinophils;
        this.rbc = rbc;
        this.pcb = pcb;
        this.platelet = platelet;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getNic() {
        return nic;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getCost() {
        return cost;
    }

    public double getHemoglobin() {
        return hemoglobin;
    }

    public int getWbc() {
        return wbc;
    }

    public double getNeutrophils() {
        return neutrophils;
    }

    public double getLymphocytes() {
        return lymphocytes;
    }

    public double getEosinophils() {
        return eosinophils;
    }

    public double getRbc() {
        return rbc;
    }

    public double getPcb() {
        return pcb;
    }

    public int getPlatelet() {
        return platelet;
    }
}
