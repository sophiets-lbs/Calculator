package com.javacodegeeks.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JPanel calculatorView;
    private JTextField results;
    private JButton clearButton;
    private JButton signButton;
    private JButton percentButton;
    private JButton divideButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton multiplyButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton minusButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton addButton;
    private JButton zeroButton;
    private JButton digitButton;
    private JButton equalButton;

    private Double leftOperand;
    private Double rightOperand;
    private Operation calcOperation;


    public Calculator(){
        sevenButton.addActionListener(new NumberButtonClicked(sevenButton.getText()));
        eightButton.addActionListener(new NumberButtonClicked(eightButton.getText()));
        nineButton.addActionListener(new NumberButtonClicked(nineButton.getText()));
        fourButton.addActionListener(new NumberButtonClicked(fourButton.getText()));
        fiveButton.addActionListener(new NumberButtonClicked(fiveButton.getText()));
        sixButton.addActionListener(new NumberButtonClicked(sixButton.getText()));
        oneButton.addActionListener(new NumberButtonClicked(oneButton.getText()));
        twoButton.addActionListener(new NumberButtonClicked(twoButton.getText()));
        threeButton.addActionListener(new NumberButtonClicked(threeButton.getText()));
        zeroButton.addActionListener(new NumberButtonClicked(zeroButton.getText()));

        percentButton.addActionListener(new OperationButtonClicked(Operation.PERCENTAGE));
        multiplyButton.addActionListener(new OperationButtonClicked(Operation.MULTIPLICATION));
        divideButton.addActionListener(new OperationButtonClicked(Operation.DIVISION));
        minusButton.addActionListener(new OperationButtonClicked(Operation.SUBTRACTION));
        addButton.addActionListener(new OperationButtonClicked(Operation.ADDITION));
        equalButton.addActionListener(new EqualButtonClicked());
        clearButton.addActionListener(new ClearButtonClicked());
        signButton.addActionListener(new SignButtonClicked());
        digitButton.addActionListener(new DigitButtonClicked());

        results.setMargin(new Insets(5, 10, 5, 10));
    }

    private class NumberButtonClicked implements ActionListener {

        private String value;

        public NumberButtonClicked(String value){
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(leftOperand == null || leftOperand == 0.0) {
                value = results.getText() + value;
            } else {
                rightOperand = Double.valueOf(value);
            }
            results.setText(value);
        }
    }

    private class OperationButtonClicked implements ActionListener {

        private Operation operation;

        public OperationButtonClicked(Operation operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOperation = operation;
            leftOperand = Double.valueOf(results.getText());
        }
    }

    private class ClearButtonClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class DigitButtonClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText(results.getText() + ".");

        }
    }

    private class EqualButtonClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Double output = calcOperation.getOperator().applyAsDouble(leftOperand, rightOperand);
            results.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class SignButtonClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText("-"+ results.getText());
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator().calculatorView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
