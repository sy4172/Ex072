package com.example.ex072;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    final int SOURCE = 1;
    EditText eTA, eTB, eTC;
    TextView showResult;
    Random rnd;
    String answer;
    boolean flag1,flag2,flag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTA = findViewById(R.id.eTA);
        eTB = findViewById(R.id.eTB);
        eTC = findViewById(R.id.eTC);
        showResult = findViewById(R.id.showResult);
        rnd = new Random();
        answer = "";
    }

    public void goToSolve(View view) {
        if ((eTA.getText().toString().isEmpty()) || (eTB.getText().toString().isEmpty()) || (eTC.getText().toString().isEmpty())){
            Toast.makeText(this, "Enter all parameters", Toast.LENGTH_SHORT).show();
        }
        else{
            if (eTA.getText().toString().equals("0") || eTA.getText().toString().equals("-0")){
                Toast.makeText(this, "The parameter A should be bigger or smaller than zero. Try again.", Toast.LENGTH_SHORT).show();
                flag1 = false;
            }
            else{
                flag1 = true;
            }

            if(eTB.getText().toString().equals("-0") || eTB.getText().toString().equals(".0") || eTC.getText().toString().equals("-0") || eTC.getText().toString().equals(".0")){
                Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
                flag2 = false;
            }
            else{
                flag2 = true;
            }

            if (eTA.getText().toString().endsWith("-") || eTA.getText().toString().endsWith(".") || eTA.getText().toString().startsWith(".") || eTB.getText().toString().endsWith("-") || eTB.getText().toString().endsWith(".") || eTB.getText().toString().startsWith(".") || eTC.getText().toString().endsWith("-") || eTC.getText().toString().endsWith(".") || eTC.getText().toString().startsWith(".")){
                Toast.makeText(this, "Try again.", Toast.LENGTH_SHORT).show();
                flag3 = false;
            }
            else{
                flag3 = true;
            }

            if (flag1 && flag2 && flag3){
                Intent si = new Intent(this,solveEquation.class);
                si.putExtra("strA",eTA.getText().toString());
                si.putExtra("strB",eTB.getText().toString());
                si.putExtra("strC",eTC.getText().toString());
                startActivityForResult(si,SOURCE);
            }
        }
    }

    public void fillRandomChoises(View view) {
        eTA.setText(String.valueOf(rnd.nextDouble()*(100-(-100))+(-100)));
        eTB.setText(String.valueOf(rnd.nextDouble()*(100-(-100))+(-100)));
        eTC.setText(String.valueOf(rnd.nextDouble()*(100-(-100))+(-100)));
    }

    @Override
    protected void onActivityResult(int source, int good, @Nullable Intent data_back) {
        super.onActivityResult(source, good, data_back);

        if ((source == SOURCE) && (good == RESULT_OK)){
            answer = data_back.getStringExtra("answer");
            if (data_back != null){
                showResult.setText(answer);
            }
        }
    }

}