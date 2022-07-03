package com.example.midproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity {
    AppCompatButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,dot,plus,minus,multiplication,division,equal,cancel,euler,clear,pie,prev;
    TextView type,ans;
    String data = "";
    String result = "";
    String previous="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        type = findViewById(R.id.type);
        ans = findViewById(R.id.ans);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        btn0 = findViewById(R.id.zero);
        dot = findViewById(R.id.dot);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiplication = findViewById(R.id.multiply);
        division = findViewById(R.id.division);
        equal = findViewById(R.id.equal);
        clear = findViewById(R.id.clear);
        cancel = findViewById(R.id.cancel);
        pie = findViewById(R.id.pie);
        euler = findViewById(R.id.euler);
        prev = findViewById(R.id.prev);

    }
    public void Click(View view) {
        AppCompatButton currentBtn = (AppCompatButton) view;

        String buttonValue = currentBtn.getText().toString();

        if(ans.getText().toString().equals("Syntax Error")) {
            ans.setText("");

        }
        if(buttonValue.equals("e")){
            buttonValue = "2.72";
        }
        if(buttonValue.equals("π")){
            buttonValue = "3.1416";
        }
        if(buttonValue.equals("AC")){
            data = "";
            type.setText("");
            ans.setText("");
            return;
        }
        if(buttonValue.equals("prev")){
            if(previous.equals("")) {
                return;
            }
            else{
                type.setText("");
                buttonValue = previous;
            }

        }
        if(buttonValue.equals("=")){
            previous = type.getText().toString();
            data = ans.getText().toString();
            if(!data.equals("Syntax Error")) {
                type.setText(data);
                return;
            }
        }
        if(buttonValue.equals("c")){
            if(data.equals("")) {
               data = "";
               ans.setText("");

            }
            else{
                data = data.substring(0, data.length() - 1);
            }



        }
        else {
            String screenText = type.getText().toString();
            data = screenText + buttonValue;

        }
        type.setText(data);
        if(data.equals("+") || data.equals("/") ||  data.equals("-") || data.equals("*") ||  data.equals("(") ){
            return;
        }
        else {
            result = calculation(data);
        }
        if(data.equals("")){
            ans.setText("");
        }

        else {

            ans.setText(result);
        }
    }

    String calculation(String data){
        while(data.endsWith("+") || data.endsWith("/") ||  data.endsWith("-") || data.endsWith("*") ||  data.endsWith("(") ){
            data = data.substring(0, data.length() - 1);
        }
       try{
           Context context = Context.enter();
           context.setOptimizationLevel(-1);
           Scriptable scriptable = context.initStandardObjects();
           String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
           if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
           }
           return finalResult;
       }catch(Exception e){
           return "Syntax Error";
       }
    }
}