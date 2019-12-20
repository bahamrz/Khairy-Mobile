package com.example.bahaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Num1;
    EditText Num2;
    TextView Res;
    int Result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Num1=findViewById(R.id.num1);
        Num2=findViewById(R.id.num2);
        Res=findViewById(R.id.res);




    }
//
//    int Num1F=Integer.parseInt(Num1.getText().toString());
//    int Num2F=Integer.parseInt(Num2.getText().toString());


    public void mul(View view) {

        int Num1F=Integer.parseInt(Num1.getText().toString());
        int Num2F=Integer.parseInt(Num2.getText().toString());
//        if ((Num1F="") || (Num2F="")){ }
        Result=Num1F*Num2F;
        Res.setText(String.valueOf(Result));
    }

    public void div(View view) {

        int Num1F=Integer.parseInt(Num1.getText().toString());
        int Num2F=Integer.parseInt(Num2.getText().toString());
        if(Num2F==0){
            Toast.makeText(this, "Cannot Devide by Zero", Toast.LENGTH_SHORT).show();
            return;

        }
        Result=Num1F/Num2F;
        Res.setText(String.valueOf(Result));
    }

    public void sub(View view) {
        int Num1F=Integer.parseInt(Num1.getText().toString());
        int Num2F=Integer.parseInt(Num2.getText().toString());
        Result=Num1F-Num2F;
        Res.setText(String.valueOf(Result));
    }

    public void add(View view) {
        int Num1F=Integer.parseInt(Num1.getText().toString());
        int Num2F=Integer.parseInt(Num2.getText().toString());
        Result=Num1F+Num2F;
        Res.setText(String.valueOf(Result));
    }
}
