package com.example.employeemanagement;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable {

    private String key;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String mobile;
    private long joinDate;
    private double salary;
    private double advanced;
    private String prevOccupation;
    private String prevAddress;
    private String address;
    private String villageAddress;
    private String reference;
    private String referenceNumber;
//    private String comments;
//
//    private String aadharCard;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(long joinDate) {
        this.joinDate = joinDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAdvanced() {
        return advanced;
    }

    public void setAdvanced(double advanced) {
        this.advanced = advanced;
    }

    public String getPrevOccupation() {
        return prevOccupation;
    }

    public void setPrevOccupation(String prevOccupation) {
        this.prevOccupation = prevOccupation;
    }

    public String getPrevAddress() {
        return prevAddress;
    }

    public void setPrevAddress(String prevAddress) {
        this.prevAddress = prevAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVillageAddress() {
        return villageAddress;
    }

    public void setVillageAddress(String villageAddress) {
        this.villageAddress = villageAddress;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Item() {  }

    public Item(String firstName, String lastName, String fatherName, String mobile, long joinDate,
                double salary, String prevOccupation, String prevAddress, String address,
                String villageAddress, String reference, String referenceNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.mobile = mobile;
        this.joinDate = joinDate;
        this.salary = salary;
        this.prevOccupation = prevOccupation;
        this.prevAddress = prevAddress;
        this.address = address;
        this.villageAddress = villageAddress;
        this.reference = reference;
        this.referenceNumber = referenceNumber;
    }
}
