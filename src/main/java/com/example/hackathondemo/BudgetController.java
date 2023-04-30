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


        actualList.getItems().clear();
        HelloApplication.budget.add(new BudgetData(expenseTypeList.getValue(), expenseInput.getText()));
        for(int i = 0; i < (HelloApplication.budget.size()); i++) {
            actualList.getItems().add(HelloApplication.budget.get(i));

        }

        buttonFinish.setDisable(false);

    }

    @FXML
    private Button buttonFinish;


    @FXML
    void finishCalc(ActionEvent event) {

        double monthlyIncome = Double.parseDouble(monthlyInput.getText());
        double housingCost, transCost, savingCost, foodCost, utilCost, insurCost, recCost, mediCost;
        housingCost = transCost = savingCost = foodCost = utilCost = insurCost = recCost = mediCost = 0;


        //Computes the total
        double calc = 0;
        for(BudgetData expense : HelloApplication.budget){
            calc += Double.parseDouble(expense.getBudgetAmount());
        }

        // housing 35%
        // Transportation 10%
        // Savings 15%
        // Food 15%
        // Utilities 10%
        // Insurance 5%
        // Recreation 5%
        // Medical 5%

//public static double calculatedCost(){
        String evaluationText = "";

        for (BudgetData expense : HelloApplication.budget) {
            switch (expense.getBudgetName()) {
                case "Housing":
                    housingCost = monthlyIncome * .35;
                    if (Double.parseDouble(expense.getBudgetAmount()) > housingCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= housingCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                case "Transportation":
                    transCost = monthlyIncome * .10;
                    if (Double.parseDouble(expense.getBudgetAmount()) > transCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= transCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                case "Savings":
                    savingCost = monthlyIncome * .15;
                    if (Double.parseDouble(expense.getBudgetAmount()) > savingCost) {
                        evaluationText += "You are saving over 15% of your monthly income.\n";

                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= savingCost) {
                        evaluationText += "Your savings each month of $" + expense.getBudgetAmount() + " is under 15% of your Monthly Income. You should save more money each month.\n";
                    }
                        break;

                case "Food":
                    foodCost = monthlyIncome * .15;
                    if (Double.parseDouble(expense.getBudgetAmount()) > foodCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= foodCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                case "Utilities":
                    utilCost = monthlyIncome * .10;
                    if (Double.parseDouble(expense.getBudgetAmount()) > utilCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= utilCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                case "Insurance":
                    insurCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > insurCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= insurCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                case "Recreation":
                    recCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > recCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= recCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                        
                    }
                        break;

                case "Medical":
                    mediCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > mediCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) <= mediCost) {
                        evaluationText += "Your "+expense.getBudgetName()+" cost of $" + expense.getBudgetAmount() + " is within budget.\n";
                    }
                        break;

                default:
                    break;
                }
            }

            evaluationBox.setText(evaluationText);
//        }

        String arrExpense[] = {"Housing","Transportation","Savings","Food","Utilities","Insurance","Recreation","Medical"};
        double amountArr[] = {housingCost,transCost,savingCost,foodCost,utilCost,insurCost,recCost,mediCost};

        expectedList.getItems().clear();

        for(int i=0;i<arrExpense.length;i++){
            if(amountArr[i]!=0){
                expectedList.getItems().add("$"+amountArr[i]+"\t| "+arrExpense[i]);
            }

        }


        //System.out.println(calc);

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
    void calcButtonPress(ActionEvent event) {

    }


    @FXML
    private TextArea evaluationBox;


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

        expenseTypeList.setOnAction(event -> {
            buttonAddExpense.setDisable(false);
        });

        buttonRemove.setOnAction(e -> {
            int selectedIndex = actualList.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                actualList.getItems().remove(selectedIndex);
                HelloApplication.budget.remove(selectedIndex);
            }
        });


//        for (int i = 0; i < items.size(); i++) {
//            System.out.println(items.get(i));
//        }
        //items.add("hi");

    }

    @FXML
    void expenseClicked(MouseEvent event) {
        System.out.println(expenseTypeList.getValue());
    }

    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;

    @FXML
    void loadPress(ActionEvent event) {

        try {
            Scanner input = new Scanner(new File("budget.csv"));
            while (input.hasNextLine()) {
                String savedSchedule = input.nextLine();
                String[] savedScheduleSplit = savedSchedule.split(",");
                String budgetAmount = savedScheduleSplit[0];
                String budgetName = savedScheduleSplit[1];
                BudgetData budget = new BudgetData(budgetName, budgetAmount);
                actualList.getItems().add(budget);
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
