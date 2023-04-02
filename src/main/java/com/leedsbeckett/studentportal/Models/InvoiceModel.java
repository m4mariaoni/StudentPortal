package com.leedsbeckett.studentportal.Models;

import java.time.LocalDate;

public class InvoiceModel {

    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AccountModel getAccount() {
        return accounts;
    }

    public void setAccount(AccountModel accounts) {
        this.accounts = accounts;
    }

    private LocalDate dueDate;
    private int type;
    private AccountModel accounts;

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    private String StudentId;

    public enum Type{
        LIBRARY_FINE,
        TUITION_FEES
    }

    public enum Status{
        OUTSTANDING,
        PAID,
        CANCELLED
    }
}
