package com.ritik.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView solution_tv,result_tv;
    MaterialButton btn_c,btn_open_bracket,btn_close_bracket,btn_divide,btn_7,btn_8,btn_9,btn_multiply,btn_4,btn_5,btn_6,btn_plus,btn_1,btn_2,btn_3,btn_minus,
            btn_AC,btn_Zero,btn_dot,btn_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solution_tv=findViewById(R.id.solution_tv);
        result_tv=findViewById(R.id.result_tv);
        assign(btn_1,R.id.btn_1);
        assign(btn_2,R.id.btn_2);
        assign(btn_3,R.id.btn_3);
        assign(btn_4,R.id.btn_4);
        assign(btn_5,R.id.btn_5);
        assign(btn_6,R.id.btn_6);
        assign(btn_7,R.id.btn_7);
        assign(btn_8,R.id.btn_8);
        assign(btn_9,R.id.btn_9);
        assign(btn_c,R.id.btn_c);
        assign(btn_open_bracket,R.id.btn_open_bracket);
        assign(btn_close_bracket,R.id.btn_close_bracket);
        assign(btn_divide,R.id.btn_divide);
        assign(btn_multiply,R.id.btn_multiply);
        assign(btn_plus,R.id.btn_plus);
        assign(btn_minus,R.id.btn_minus);
        assign(btn_AC,R.id.btn_AC);
        assign(btn_Zero,R.id.btn_Zero);
        assign(btn_dot,R.id.btn_dot);
        assign(btn_equal,R.id.btn_equal);

    }
    void assign(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton)view;
        String buttonTxt=button.getText().toString();
        String dataToCalculate=solution_tv.getText().toString();

        if(buttonTxt.equals("ac")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonTxt.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonTxt.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate=dataToCalculate+buttonTxt;
        }
        solution_tv.setText(dataToCalculate);

        String finalResult=getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            result_tv.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Error";
        }
    }
}