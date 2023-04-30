package com.example.hackathondemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;


import java.io.*;
import java.util.Scanner;

public class BudgetController {


    @FXML
    private Button buttonAddExpense;

    @FXML
    private TextField monthlyInput;

    @FXML
    void addExpense(ActionEvent event) {

        //refreshes left column and adds items
        actualList.getItems().clear();
        HelloApplication.budget.add(new BudgetData(expenseTypeList.getValue(), expenseInput.getText()));
        for(int i = 0; i < (HelloApplication.budget.size()); i++) {
            actualList.getItems().add(HelloApplication.budget.get(i));

        }

        //enables the button after choice is selected
        buttonFinish.setDisable(false);

    }

    @FXML
    private Button buttonFinish;


    @FXML
    void finishCalc(ActionEvent event) {


        double monthlyIncome = Double.parseDouble(monthlyInput.getText());

        //Computes the total
        double calc = 0;
        for (BudgetData expense : HelloApplication.budget) {
            calc += Double.parseDouble(expense.getBudgetAmount());
        }

        //initializes string variable to append to
        String evaluationText = "";

        double housingCost, transCost, savingCost, foodCost, utilCost, insurCost, recCost, mediCost;
        housingCost = transCost = savingCost = foodCost = utilCost = insurCost = recCost = mediCost = 0;

        Double budgetLimit = null;

        //multiplies based on percentage of monthly income
        for (BudgetData expense : HelloApplication.budget) {
            switch (expense.getBudgetName()) {
                case "Housing" -> {
                    housingCost = monthlyIncome * .35;
                    budgetLimit = housingCost;
                }
                case "Transportation" -> {
                    transCost = monthlyIncome * .10;
                    budgetLimit = transCost;
                }
                case "Savings" -> {
                    savingCost = monthlyIncome * .15;
                    budgetLimit = savingCost;
                }
                case "Food" -> {
                    foodCost = monthlyIncome * .15;
                    budgetLimit = foodCost;
                }
                case "Utilities" -> {
                    utilCost = monthlyIncome * .10;
                    budgetLimit = utilCost;
                }
                case "Insurance" -> {
                    insurCost = monthlyIncome * .05;
                    budgetLimit = insurCost;
                }
                case "Recreation" -> {
                    recCost = monthlyIncome * .05;
                    budgetLimit = recCost;
                }
                case "Medical" -> {
                    mediCost = monthlyIncome * .05;
                    budgetLimit = mediCost;
                }
            }
            double budgetAmount = Double.parseDouble(expense.getBudgetAmount());
            String formattedBudgetAmount = String.format("%.2f", budgetAmount);
            String budgetName = expense.getBudgetName();
            
            if (budgetName.equals("Savings")) {
                if (budgetAmount > budgetLimit) {
                    evaluationText = "[+]You are saving $" + formattedBudgetAmount + ", which is over 15% of your Monthly Income.\n";
                } else if (budgetAmount <= budgetLimit) {
                    evaluationText = "[-]Your savings of $" + formattedBudgetAmount + " is below 15% of your Monthly Income.\n";
                }
            } else if (budgetAmount > budgetLimit) {
                evaluationText = "[-]Your " + budgetName + " cost of $" + formattedBudgetAmount + " exceeds 15% of your Monthly Income.\n";
            } else if (budgetAmount <= budgetLimit) {
                evaluationText = "[+]Your " + budgetName + " cost of $" + formattedBudgetAmount + " is within budget.\n";
            }
            evaluationBox.appendText(evaluationText);
    }

        if(calc>monthlyIncome){
            String deficit = String.format("%.2f", calc-monthlyIncome);
            budgetTotalText.setStyle("-fx-text-fill: red");
            budgetTotalText.setText("You are -$" + deficit + " in deficit.\nSee analysis below for assistance.");
        }else{
            String saving = String.format("%.2f",monthlyIncome-calc);
            budgetTotalText.setStyle("-fx-text-fill: green");
            budgetTotalText.setText("You have $" + saving + " in additional savings.");
        }

        evaluationBox.setText(evaluationText);

        String[] arrExpense = {"Housing","Transportation","Savings","Food","Utilities","Insurance","Recreation","Medical"};
        double[] amountArr = {housingCost,transCost,savingCost,foodCost,utilCost,insurCost,recCost,mediCost};

        expectedList.getItems().clear();

        for(int i=0;i<arrExpense.length;i++){
            if(amountArr[i]!=0){
                String sugCost = String.format("%.2f",amountArr[i]);
                expectedList.getItems().add("$"+sugCost+"\t| "+arrExpense[i]);
            }

        }

    }

    @FXML
    private TextField expenseInput;

    @FXML
    private ListView<BudgetData> actualList;

    @FXML
    private Button buttonRemove;

    @FXML
    void removeExpense(ActionEvent event) {
        int selectedIndex = actualList.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            BudgetData removedItem = actualList.getItems().remove(selectedIndex);
            HelloApplication.budget.remove(removedItem);
        }
    }

    @FXML
    private ListView<String> expectedList;

    @FXML
    private TextArea evaluationBox;


    @FXML
    private TextArea budgetTotalText;


    ObservableList<String> expenseItems = FXCollections.observableArrayList("Food","Housing","Insurance","Medical","Recreation","Savings","Transportation","Utilities");
    @FXML
    private ChoiceBox<String> expenseTypeList;

    public void initialize(){

        buttonFinish.setDisable(true);

        buttonAddExpense.setDisable(true);

        actualList.getItems().clear();

        for(int i = 0; i < HelloApplication.budget.size(); i++){
            actualList.getItems().add(HelloApplication.budget.get(i));
        }

        expenseTypeList.setItems(expenseItems);

        expenseTypeList.setValue("Expense Type");

        expenseTypeList.setOnAction(event -> buttonAddExpense.setDisable(false));

        buttonRemove.setOnAction(e -> {
            int selectedIndex = actualList.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                actualList.getItems().remove(selectedIndex);
                HelloApplication.budget.remove(selectedIndex);
            }
        });

    }

    @FXML
    void expenseClicked(MouseEvent event) {
        System.out.println(expenseTypeList.getValue());
    }

    @FXML
    void loadPress(ActionEvent event) {

        HelloApplication.budget.clear();
        actualList.getItems().clear();

        try {
            Scanner input = new Scanner(new File("budget.csv"));
            while (input.hasNextLine()) {
                String savedSchedule = input.nextLine();
                String[] savedScheduleSplit = savedSchedule.split(",");
                String budgetAmount = savedScheduleSplit[0];
                String budgetName = savedScheduleSplit[1];
                BudgetData budget = new BudgetData(budgetName, budgetAmount);
                actualList.getItems().add(budget);
                HelloApplication.budget.add(budget);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

    }


    @FXML
    void savePress(ActionEvent event) {
        try {
            new PrintWriter("budget.csv").close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file!");
        }
        File output = new File("budget.csv");
        try(FileWriter writer = new FileWriter("budget.csv")){
            for(int i = 0; i < HelloApplication.budget.size(); i++){
                HelloApplication.budget.get(i).setChg2Str(true);
                writer.write((HelloApplication.budget.get(i)) + ", ");
                HelloApplication.budget.get(i).setChg2Str(false);
                writer.write("\n");
            }
        }catch(IOException e){
            System.out.println("Couldn't find csv file!");
        }
        actualList.getItems().clear();
        //HelloApplication.budget.clear();


    }

}
