package com.interview.raihan.mycalculator;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[] _numericButtons = {R.id.button_0,R.id.button_1,R.id.button_2,R.id.button_3,R.id.button_4,R.id.button_5,R.id.button_6,R.id.button_7,R.id.button_8,R.id.button_9};
    private int[] _operatorButtons = {R.id.button_Add,R.id.button_Subtract,R.id.button_Multiply,R.id.button_Divide,R.id.button_Braket_Open,R.id.button_Braket_Close,R.id.button_Sin,R.id.button_Cos,R.id.button_Tan,R.id.button_Sqrt,R.id.button_Power};

    private TextView _currentText;
    private boolean _haveDot;
    private boolean _haveError;
    private boolean _gotResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        SetNumericOnClickListener();
        SetOperationOnClickListener();
    }

    private void init()
    {
        this._currentText = (TextView) findViewById(R.id.CurrentText);
        this._haveDot = false;
        this._haveError = false;
        this._gotResult = false;
    }

    private void SetNumericOnClickListener()
    {
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if(!_gotResult)
                {
                    _currentText.append(button.getText());
                }
            }
        };

        for (int id : _numericButtons)
        {
            findViewById(id).setOnClickListener(listener);
        }


        //for dot check

        findViewById(R.id.button_Dot).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;

                if(!_haveDot && !_gotResult) {
                    _currentText.append(button.getText());
                    _haveDot = true;
                }
            }
        });
    }

    private void SetOperationOnClickListener()
    {
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;

                if(!_haveError)
                {
                    _gotResult = false;
                    _haveDot = false;
                    _currentText.append(button.getText());
                }

            }
        };

        for (int id : _operatorButtons)
        {
            findViewById(id).setOnClickListener(listener);
        }


        //for clear

        findViewById(R.id.button_Clear).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                _currentText.setText("");
                _haveDot = false;
                _gotResult = false;
                _haveError = false;
            }
        });

        //for back

        findViewById(R.id.button_Back).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!_gotResult)
                {
                    Button button = (Button) v;
                    String str = _currentText.getText().toString();
                    if (str != null && str.length() > 0) {
                        if(str.charAt(str.length() - 1) =='.') _haveDot = false;
                        str = str.substring(0, str.length() - 1);
                        _currentText.setText(str);
                    }
                }

            }
        });

        //for equal

        findViewById(R.id.button_Equal).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!_gotResult && !_haveError)
                {
                    Button button = (Button) v;
                    String str = _currentText.getText().toString();
                    if (str != null && str.length() > 0) {
                        _haveDot = false;
                        String output = EvaluateString.EvaluateExpression(str);
                        if(output.contains("Error") || output.contains("Infinity")) _haveError = true;
                        _currentText.setText(output);
                        _gotResult = true;
                    }
                }


            }
        });
    }
}
