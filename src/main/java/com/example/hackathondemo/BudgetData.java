package com.example.hackathondemo;

public class BudgetData {

    public String getBudgetName() {
        return budgetName;
    }

    private String budgetName;
    private String budgetAmount;

    public String getBudgetAmount() {
        return budgetAmount;
    }

    public BudgetData(String budgetName, String budgetAmount) {
        this.budgetName = budgetName;
        this.budgetAmount = budgetAmount;
    }
    private boolean chg2Str;


    public boolean isChg2Str() {
        return chg2Str;
    }

    public void setChg2Str(boolean chg2Str) {
        this.chg2Str = chg2Str;
    }

    @Override
    public String toString() {
        if (chg2Str) {
            return budgetAmount + ", " + budgetName;
        } else {
            return "$" + budgetAmount + "\t| " + budgetName;
        }
    }
}
