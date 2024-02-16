package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private TextView showScreen;
    private float op1 = 0;
    private float op2 = 0;
    private boolean isCurrentlyOp1 = true;
    private Ops operator = null;

    public enum Ops {
        PLUS,
        MOINS,
        FOIS,
        DIV
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnComma = findViewById(R.id.btnComma);
        Button btnEqual = findViewById(R.id.btnEqual);
        Button btnSupp = findViewById(R.id.btnSupp);
        showScreen = findViewById(R.id.showScreen);
        btnSupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTextView();
                if(isCurrentlyOp1 == false){
                    isCurrentlyOp1 = true;
                    op2 = 0;
                    operator = null;
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        btnComma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScreen.append(".");
            }
        });
    }

    public void createToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void calculate(){
        if(!isCurrentlyOp1){
            op2 = Float.parseFloat(showScreen.getText().toString());
            if(operator == Ops.PLUS){
                op1 = op1 + op2;
            }
            else if (operator == Ops.FOIS){
                op1 = op1 * op2;
            }
            else if (operator == Ops.MOINS){
                op1 = op1 - op2;
            }
            else if (operator == Ops.DIV){
                op1 = op1 / op2;
            }


            if(op1 == Math.floor(op1)){
                updateTextViewInt((int)op1);
            }
            else{
                updateTextViewFloat(op1);
            }

            isCurrentlyOp1 = true;
            operator = null;
            op2 = 0;
        }
        else{
            createToast("Vous ne pouvez pas calculer avec un seul chiffre");
        }
    }

    public void setNumber(View v){
        int id = v.getId();
        Button btn = findViewById(id);
        if(showScreen.getText().toString().equals("0")){
            showScreen.setText(btn.getText());
        }
        else{
            showScreen.append(btn.getText());
        }

    }

    public void updateTextViewFloat(float op1){
        showScreen.setText(String.valueOf(op1));
    }

    public void updateTextViewInt(int op1){
        showScreen.setText(String.valueOf(op1));
    }

    public void updateTextView(){
        showScreen.setText("0");
    }

    public void setOperator(View v){
        int id = v.getId();
        if(id == R.id.btnPlus){
            operator = Ops.PLUS;
        }
        else if (id == R.id.btnMinus){
            operator = Ops.MOINS;
        }
        else if (id == R.id.btnMultiply){
            operator = Ops.FOIS;
        }
        else if (id == R.id.btnDivide){
            operator = Ops.DIV;
        }


        isCurrentlyOp1 = false;
        op1 = Float.parseFloat(showScreen.getText().toString());
        updateTextView();
    }
}