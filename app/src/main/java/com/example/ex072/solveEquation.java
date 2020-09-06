package com.example.ex072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class solveEquation extends AppCompatActivity {

    boolean flag1,flag2,flag3;
    double [] solution;
    String strA,strB,strC,answer,part1,part2,part3,space;
    WebView view;
    TextView resultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_equation);

        Intent gi = getIntent();
        strA = gi.getStringExtra("strA");
        strB = gi.getStringExtra("strB");
        strC = gi.getStringExtra("strC");
        solution = new double[]{Double.NaN,Double.NaN};
        answer = "";
        part1 = "https://www.google.com/search?hl=iw&source=hp&ei=w22tXvf8NMmUlwS9hoUo&q=";
        part2 = "&oq=";
        part3 = "&gs_lcp=CgZwc3ktYWIQAzICCAAyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgQIABAeMgQIABAeOgUIABCDAToGCAAQBxAeOgQIABADOggIABAIEAcQHjoICAAQBxAFEB46BggAEAUQHjoKCAAQBxAFEAoQHjoGCAAQCBAeUJQgWPj4BGCMggVoFnAAeAGAAbMBiAGOH5IBBTI3LjE3mAEAoAEBqgEHZ3dzLXdperABAA&sclient=psy-ab&ved=0ahUKEwi36oeBnZXpAhVJyoUKHT1DAQUQ4dUDCAc&uact=5";
        space = "%";
        view = findViewById(R.id.view);
        resultDisplay = findViewById(R.id.resultDisplay);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyWebViewClient());
        checkAndOpenUrl(strA,strB,strC);
        makingAnAnswer(strA,strB,strC);
        gi.putExtra("answer",answer);
        setResult(RESULT_OK,gi);
    }

    private void checkAndOpenUrl(String strA, String strB, String strC) {
        String result,fullUrl;

        if (strA.equals("0") || strA.equals("-0")){
            resultDisplay.setText(R.string.aError);
        }
        else{
            flag1 = true;
        }

        if(strA.equals("0")|| strA.equals(".0") || strB.equals("0") || strB.equals("-0") || strB.equals(".0") || strC.equals("0") || strC.equals("-0") || strC.equals(".0")){
            resultDisplay.setText(R.string.again);
        }
        else{
            flag2 = true;
        }

        if (strA.endsWith("-") || strA.endsWith(".") || strA.startsWith(".") || strB.endsWith("-") || strB.endsWith(".") || strB.startsWith(".") || strC.endsWith("-") || strC.endsWith(".") || strC.startsWith(".")){
            resultDisplay.setText(R.string.again);
        }
        else{
            flag3 = true;
        }

        if (flag1 && flag2 && flag3) {
            result = buildUrl();
            fullUrl = part1 + result + part2 + result + part3;
            view.loadUrl(fullUrl);
        }
    }

    private String buildUrl() {
        String square, url, newB, newC;
        int len;
        square = "5E2";
        url = "";


        if (strB.startsWith("-")){
            newB = strB;
        }
        else{
            newB = space+"2B"+strB;
        }

        if (strC.startsWith("-")){
            newC = strC;
        }
        else{
            newC = space+"2B"+strC;
        }


        if (strA.equals("1")){
            if (strB.equals("0")){
                if (strC.equals("0")){
                    url = "x"+space+square;
                }
                else{
                    url = "x"+space+square+newC;
                }
            }
            else if (strB.equals("1")){
                if (strC.equals("0")){
                    url = "x"+space+square+space+"2B"+"x";
                }
                else{
                    url = "x"+space+square+space+"2B"+"x"+newC;
                }
            }
            else if (!(strB.equals("1"))){
                if (strC.equals("0")){
                    url = "x"+space+square+newB+"x";
                }
                else{
                    url = "x"+space+square+newB+"x"+newC;
                }
            }
        }
        else{
            if (strB.equals("0")){
                if (strC.equals("0")){
                    url = strA+"x"+space+square;
                }
                else{
                    url = strA+"x"+space+square+newC;
                }
            }
            else if (strB.equals("1")){
                if (strC.equals("0")){
                    url = strA+"x"+space+square+space+"2B"+"x";
                }
                else{
                    url = strA+"x"+space+square+space+"2B"+"x"+newC;
                }
            }
            else if (!(strB.equals("1"))){
                if (strC.equals("0")){
                    url = strA+"x"+space+square+newB+"x";
                }
                else{
                    url = strA+"x"+space+square+newB+"x"+newC;
                }
            }
        }
        return url;
    }

    private void makingAnAnswer(String strA, String strB, String strC) {
        double a = Double.parseDouble(strA);
        double b = Double.parseDouble(strB);
        double c = Double.parseDouble(strC);
        double discriminant = b*b-4*a*c;

        if (discriminant > 0){
            solution[0] = (-b-Math.sqrt(discriminant))/2*a;
            solution[1] = (-b+Math.sqrt(discriminant))/2*a;
            if ((Math.abs(solution[1] - (int) solution[1]) > 0) && ((Math.abs(solution[1] - (int) solution[1])) < 1) && (Math.abs(solution[0] - (int) solution[0]) == 0)){
                answer = "There are two solutions: " + (int) solution[0] + "   " + solution[1];
            }
            else if ((Math.abs(solution[0] - (int) solution[0]) > 0) && ((Math.abs(solution[0] - (int) solution[0])) < 1) && (Math.abs(solution[1] - (int) solution[1]) == 0)){
                answer = "There are two solutions: " + solution[0] + "   " + (int) solution[1];
            }
            else if ((Math.abs(solution[1] - (int) solution[1]) > 0) && ((Math.abs(solution[1] - (int) solution[1])) < 1) && ((Math.abs(solution[0] - (int) solution[0])) > 0) && ((Math.abs(solution[0] - (int) solution[0])) < 1)){
                answer = "There are two solutions: " + solution[0] + "   " + solution[1];
            }
            else{
                answer = "There are two solutions: " + (int) solution[0] + "   " + (int) solution[1];
            }
        }
        else if (discriminant < 0){
            answer = "There is no solution";
        }
        else{
            solution[0] = (-b-Math.sqrt(discriminant))/2*a;
            if (String.valueOf(solution[0]).endsWith(".0")){
                answer = "There is one solution: " + (int) solution[0];
            }
        }
        resultDisplay.setText(answer);
    }

    public void backToMain(View view) {
        finish();
    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}