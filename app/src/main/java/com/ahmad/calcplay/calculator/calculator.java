package com.ahmad.calcplay.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.calcplay.HelperAndAdabter.AnimateSwitchScreenCalculator;
import com.ahmad.calcplay.R;

import java.util.ArrayList;

public class calculator extends AppCompatActivity {
Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,dot,onBtn,offBtn,Ac,del,div,plus,subtraction,multiplier,equal;
TextView screenResult,screenOperation;
double first_number ;
String operations;
    String screenOperationvalue="";
boolean calculated=false,operationClicked=false,BtnNumClicked=false,equalClicked=false;
    AnimateSwitchScreenCalculator animateSwitchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //initialize btn numbers
        num0=findViewById(R.id.num0);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        num7=findViewById(R.id.num7);
        num8=findViewById(R.id.num8);
        num9=findViewById(R.id.num9);
        dot=findViewById(R.id.dot);
        //initialize operations
        onBtn=findViewById(R.id.onBtn);
        offBtn=findViewById(R.id.offBtn);
        Ac=findViewById(R.id.Ac);
        del=findViewById(R.id.del);
        div=findViewById(R.id.div);
        plus=findViewById(R.id.plus);
        subtraction=findViewById(R.id.subtraction);
        multiplier=findViewById(R.id.multiplier);
        equal=findViewById(R.id.equal);
        screenResult=findViewById(R.id.screenResult);
        screenOperation=findViewById(R.id.screenOperation);

        //turn on and off screen result
 animateSwitchBtn=new AnimateSwitchScreenCalculator(screenResult,screenOperation);
switchbtn();
//buttons numbrs and operation
Buttons();

    }
public void Buttons(){
    ArrayList<Button> numb=new ArrayList<>();
    numb.add(num0);
    numb.add(num1);
    numb.add(num2);
    numb.add(num3);
    numb.add(num4);
    numb.add(num5);
    numb.add(num6);
    numb.add(num7);
    numb.add(num8);
    numb.add(num9);
    //using for each sentences to get numbers every time press
    for (Button btnNums:numb) {
        btnNums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calculated){
                    screenResult.setText("0");
                    calculated=false;
screenOperationvalue="";
                }


                if (!screenResult.getText().toString().equals("0")){
                        screenResult.setText(screenResult.getText().toString()+btnNums.getText().toString());
                        screenOperationvalue= screenOperationvalue + btnNums.getText().toString();

                    }else {
                        screenResult.setText(btnNums.getText().toString());
if (!btnNums.getText().toString().equals("0")){
    screenOperationvalue= btnNums.getText().toString();

}
                    }
                    BtnNumClicked=true;
                }


        });

    }


        ArrayList<Button> opers=new ArrayList<>();
        opers.add(div);
        opers.add(multiplier);
        opers.add(plus);
        opers.add(subtraction);

        //using for each sentences to do operations every time press
        for (Button btnOpers:opers) {
            btnOpers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (operationClicked){
                        Toast.makeText(calculator.this, "you clicked already", Toast.LENGTH_SHORT).show();
                    }
                    //check if number button clicked   if not clicked you cant click on any operations button
                    if (BtnNumClicked){
                        first_number =first_number+Double.parseDouble(screenResult.getText().toString());
                        operations=btnOpers.getText().toString();
                        screenOperation.setText(screenOperation.getText().toString()+screenOperationvalue+ " "+btnOpers.getText().toString());
                        screenResult.setText("0");
                        screenOperationvalue="";
                        BtnNumClicked=false;
                    }else {
                        Toast.makeText(calculator.this, "click on number first", Toast.LENGTH_SHORT).show();
                    }


                }
            });
        }
//dot or point  to add point  to number  one time
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  if (screenResult.getText().toString().contains(".")){
                        Toast.makeText(calculator.this, "you added it already", Toast.LENGTH_SHORT).show();
                    }else {
                        screenResult.setText(screenResult.getText().toString()+".");
                    }
            }
        });
        /*
del btn remove  one char
0 start endex then add length numbs -1  to substring
 */
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (calculated){
                    first_number=0;
                    screenOperation.setText("");
                    screenResult.setText("0");
                    calculated=false;
                    screenOperationvalue="";

                }else {

                    String numbs=screenResult.getText().toString();
                if ( numbs.length()>1 ){
                    screenResult.setText(numbs.substring(0,numbs.length()-1));

screenOperationvalue=screenResult.getText().toString();
                }else {
screenResult.setText("0");
if (!screenOperationvalue.equals("")){
    screenOperationvalue="";
    BtnNumClicked=false;
}
                }

        }}});
        //equal button to calculate numbers
    equal.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (equalClicked){
                screenOperation.setText("0");
            }
            equalClicked=true;
         Double second_number  =  Double.parseDouble(screenResult.getText().toString());
            double result=0;
            if (operations!=null){
                if (operations.equals("/")) {
                    result = first_number / second_number;

                } else if (operations.equals("-")) {
                    result = first_number - second_number;
                } else if (operations.equals("x")) {
                    result = first_number * second_number;
                } else if (operations.equals("+")) {
                    result = first_number + second_number;
                }
            } else {
                result = first_number + second_number;
            }
            screenResult.setText(String.valueOf(result));

            screenOperation.setText(screenOperation.getText().toString()+screenOperationvalue+"="+String.valueOf(result));
            calculated=true;


        }});
    //ac to clear all screen
    Ac.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculated=false;
            operationClicked=false;
            BtnNumClicked=false;
            equalClicked=false;
            first_number=0;
            screenOperation.setText("");
            screenOperationvalue="";
            screenResult.setText("0");
        }
    });
    }

    public void switchbtn(){
        screenResult.setVisibility(View.INVISIBLE);
        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if (screenResult.getVisibility()==View.INVISIBLE){
    animateSwitchBtn.screenResultOn();
    screenResult.setText("0");
    first_number=0;
    operationClicked=false;
    BtnNumClicked=false;
    screenOperation.setText("");
}
            }
        });
        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (screenResult.getVisibility()==View.VISIBLE){
                    animateSwitchBtn.screenResultoff();
                    screenResult.setText("0");
                    first_number=0;
                    operationClicked=false;
                    BtnNumClicked=false;

                }
            }
        });
    }

}



