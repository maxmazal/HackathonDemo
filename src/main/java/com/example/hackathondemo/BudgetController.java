package com.example.hackathondemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

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
                        evaluationText += "Your housing cost of " + housingCost + "exceeds 35% of your Monthly Income.\n";

                    } else if (Double.parseDouble(expense.getBudgetAmount()) < housingCost) {
                        evaluationText += "Your housing cost of" + housingCost + "is within budget.\n";
                    }
                        break;

                case "Transportation":
                    transCost = monthlyIncome * .10;
                    if (Double.parseDouble(expense.getBudgetAmount()) > transCost) {
                        evaluationText += "Your transportation cost of" + transCost + "exceeds 10% of your Monthly Income.\n";

                    } else if (Double.parseDouble(expense.getBudgetAmount()) < transCost) {
                        evaluationText += "Your transportation cost of" + transCost + "is within budget.\n";
                    }
                        break;

                case "Savings":
                    savingCost = monthlyIncome * .15;
                    if (Double.parseDouble(expense.getBudgetAmount()) > savingCost) {
                        evaluationText += "You are saving over 15% of your monthly income.\n";

                    } else if (Double.parseDouble(expense.getBudgetAmount()) < savingCost) {
                        evaluationText += "Your savings each month of " + savingCost + " is under 15% of your Monthly Income. You should save more money each month.\n";
                    }
                        break;

                case "Food":
                    foodCost = monthlyIncome * .15;
                    if (Double.parseDouble(expense.getBudgetAmount()) > foodCost) {
                        evaluationText += "Your food cost of " + foodCost + "exceeds 15% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) < foodCost) {
                        evaluationText += "Your food cost of " + foodCost + "is within budget.\n";
                    }
                        break;

                case "Utilities":
                    utilCost = monthlyIncome * .10;
                    if (Double.parseDouble(expense.getBudgetAmount()) > utilCost) {
                        evaluationText += "Your utility cost of " + utilCost + "exceeds 10% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) < utilCost) {
                        evaluationText += "Your utility cost of " + utilCost + "is within budget.\n";
                    }
                        break;

                case "Insurance":
                    insurCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > insurCost) {
                        evaluationText += "Your insurance cost of " + insurCost + "exceeds 5% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) < insurCost) {
                        evaluationText += "Your insurance cost of " + insurCost + "is within budget.\n";
                    }
                        break;

                case "Recreation":
                    recCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > recCost) {
                        evaluationText += "Your recreational cost of " + recCost + "exceeds 5% of your Monthly Income.\n";
                    } else if (Double.parseDouble(expense.getBudgetAmount()) < recCost) {
                        evaluationText += "Your recreational cost of " + recCost + "is within budget.\n";
                        
                    }
                        break;

                case "Medical":
                    mediCost = monthlyIncome * .05;
                    if (Double.parseDouble(expense.getBudgetAmount()) > mediCost) {
                        evaluationText += "Your medical cost of " + mediCost + "exceeds 5% of your Monthly Income.\n";

                    } else if (Double.parseDouble(expense.getBudgetAmount()) < mediCost) {
                        evaluationText += "Your medical cost of " + mediCost + "is within budget.\n";
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
            expectedList.getItems().add("$"+amountArr[i]+"\t| "+arrExpense[i]);
        }

        /*
        ############################################################################################################
        */













        //System.out.println(calc);

    }

    @FXML
    private TextField expenseInput;

    @FXML
    private ListView<BudgetData> actualList;

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

        actualList.getItems().clear();

        for(int i = 0; i < HelloApplication.budget.size(); i++){
            actualList.getItems().add(HelloApplication.budget.get(i));
        }



        expenseTypeList.setItems(expenseItems);

        expenseTypeList.setValue("Expense Type");

        expenseTypeList.setOnAction(event -> {
            String selected = expenseTypeList.getValue();
            System.out.println("Selected: " + selected);
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

}
