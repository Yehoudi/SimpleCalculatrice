package com.example.my_calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Enter une valeur".equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void supprime(View view){
      display.setText("");
    }
    public void parenthese(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar =0;
        int textLen = display.getText().length();

        for(int i = 0; i < cursorPos; i++ ){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openPar += 1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closedPar += 1;
            }

        }
        if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals(")")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void backspace(View view){
       int cursorPos = display.getSelectionStart();
       int textLen = display.getText().length();

       if (cursorPos != 0 && textLen != 0){
           SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
           selection.replace(cursorPos - 1, cursorPos,"");
           display.setText(selection);
           display.setSelection(cursorPos - 1);
       }
    }
    public void exposant(View view){
        updateText("^");
    }
    public void division(View view){
       updateText("÷");
    }
    public void sept(View view){
        updateText("7");
    }

    public void huit(View view){
        updateText("8");
    }
    public void neuf(View view){
        updateText("9");
    }
    public void multiplication(View view){
        updateText("x");
    }
    public void quatre(View view){
        updateText("4");
    }
    public void cinq(View view){
        updateText("5");
    }
    public void six(View view){
        updateText("6");
    }
    public void soustraction(View view){
        updateText("-");
    }
    public void un(View view){
        updateText("1");
    }
    public void deux(View view){
        updateText("2");
    }
    public void trois(View view){
        updateText("3");
    }
    public void additioon(View view){
        updateText("+");
    }
    public void zero(View view){
        updateText("0");
    }
    public void plusminus(View view){ updateText("-");
    }
    public void cosinus(View view){
       updateText("cos(");
    }
    public void sinus(View view){
       updateText("sin(");
    }
    public void point(View view){
         updateText(".");
    }
    public void egale(View view){
         String userExp = display.getText().toString();
         userExp = userExp.replaceAll("÷", "/");
         userExp = userExp.replaceAll("x", "*");

         Expression exp =  new Expression(userExp);
         String result = String.valueOf(exp.calculate());

         display.setText(result);
         display.setSelection(result.length());
    }
}