package com.interview.raihan.mycalculator;

/**
 * Created by user on 11-Oct-17.
 */

public class MyCalculator {
    private double  _firstNumber;
    private double _secondNumber;
    public char _expression;

    public MyCalculator() {
        Clear();
    }
    public void Clear() {
        _firstNumber = 0;
        _secondNumber = 0;
        _expression = '+';
    }

    public String Calculate(String s)
    {
        String result = "";
        try {
            double num = Double.parseDouble(s);
            _secondNumber = num;
        }
        catch (Exception e)
        {
            return "Syntex Error!!!";
        }

        try {
            double res = DoOperation(_firstNumber,_secondNumber);
            _firstNumber = res;
        }
        catch (Exception e)
        {
            return "Math Error!!!";
        }

        result = OutputFormat(_firstNumber);

        return result;
    }

    private String OutputFormat(double num) {
        return Double.toString(num);
    }

    private double DoOperation(double num1, double num2)
    {
        if(_expression == '+')
        {
            return num1 + num2;
        }
        else if(_expression == '-')
        {
            return num1 - num2;
        }
        else if(_expression == '*')
        {
            return num1 * num2;
        }
        else if(_expression == '/')
        {
            return num1 / num2;
        }
        else if(_expression == '^')
        {
            return Math.pow(num1,num2);
        }

        return 0;
    }
}
