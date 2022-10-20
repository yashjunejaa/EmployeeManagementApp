package com.example.employeemanagement;

public class Transaction {

    long time;
    double amount;
    String comment;

    public Transaction(long time, double amount, String comment) {
        this.time = time;
        this.amount = amount;
        this.comment = comment;
    }

    public Transaction() {
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
