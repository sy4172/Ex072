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

    boolean flag1,flag2;
    double solution0, solution1;
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
        solution0 = solution1 = Double.NaN;
        answer = "";
        part1 = "https://www.google.com/search?hl=iw&source=hp&ei=w22tXvf8NMmUlwS9hoUo&q=";
        part2 = "&oq=";
        part3 = "&gs_lcp=CgZwc3ktYWIQAzICCAAyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgQIABAeMgQIABAeOgUIABCDAToGCAAQBxAeOgQIABADOggIABAIEAcQHjoICAAQBxAFEB46BggAEAUQHjoKCAAQBxAFEAoQHjoGCAAQCBAeUJQgWPj4BGCMggVoFnAAeAGAAbMBiAGOH5IBBTI3LjE3mAEAoAEBqgEHZ3dzLXdperABAA&sclient=psy-ab&ved=0ahUKEwi36oeBnZXpAhVJyoUKHT1DAQUQ4dUDCAc&uact=5";
        space = "%";
        view = findViewById(R.id.view);
        resultDisplay = findViewById(R.id.resultDisplay);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyWebViewClient());
        checkAndOpenUrl();
        makingAnAnswer(strA,strB,strC);
        gi.putExtra("answer",answer);
        setResult(RESULT_OK,gi);
    }

    private void checkAndOpenUrl() {
        String result,fullUrl;
        result = buildUrl();
        fullUrl = part1 + result + part2 + result + part3;
        view.loadUrl(fullUrl);
    }

    private String buildUrl() {
        String square, url, newB, newC;
        int len;
        square = "5E2";
        url = "";

        if (strA.contains(".") && strA.endsWith("0")){
            while ((strA.endsWith("0") && !(strA.endsWith(".")))){
                len = strA.length();
                strA = strA.substring(0,len-1);
            }
            if (strA.endsWith(".")){
                len = strA.length();
                strA = strA.substring(0,len-1);
            }
        }

        if (strB.contains(".") && strB.endsWith("0")){
            while ((strB.endsWith("0") && !(strB.endsWith(".")))){
                len = strB.length();
                strB = strB.substring(0,len-1);
            }
            if (strB.endsWith(".")){
                len = strB.length();
                strB = strB.substring(0,len-1);
            }
        }

        if (strC.contains(".") && strC.endsWith("0")){
            while ((strC.endsWith("0") && !(strC.endsWith(".")))){
                len = strC.length();
                strC = strC.substring(0,len-1);
            }
            if (strC.endsWith(".")){
                len = strC.length();
                strC = strC.substring(0,len-1);
            }
        }

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
            solution0 = (-b+Math.sqrt(discriminant))/(2*a);
            solution1 = (-b-Math.sqrt(discriminant))/(2*a);
            if ((Math.abs(solution1 - (int) solution1) > 0) && ((Math.abs(solution1 - (int) solution1)) < 1) && (Math.abs(solution0 - (int) solution0) == 0)){
                answer = "There are two solutions: " + (int) solution0 + "   " + solution1;
            }
            else if ((Math.abs(solution0 - (int) solution0) > 0) && ((Math.abs(solution0 - (int) solution0)) < 1) && (Math.abs(solution1 - (int) solution1) == 0)){
                answer = "There are two solutions: " + solution0 + "   " + (int) solution1;
            }
            else if ((Math.abs(solution1 - (int) solution1) > 0) && ((Math.abs(solution1 - (int) solution1)) < 1) && ((Math.abs(solution0 - (int) solution0)) > 0) && ((Math.abs(solution0 - (int) solution0)) < 1)){
                answer = "There are two solutions: " + solution0 + "   " + solution1;
            }
            else{
                answer = "There are two solutions: " + (int) solution0 + "   " + (int) solution1;
            }
        }
        else if (discriminant < 0){
            answer = "There is no solution";
        }
        else{
            solution0 = (-b-Math.sqrt(discriminant))/2*a;
            if (String.valueOf(solution0).endsWith(".0")){
                answer = "There is one solution: " + (int) solution0;
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