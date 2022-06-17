package com.example.tinas_cafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("안녕하세요 반갑습니다");
    }

    public void toMenu(View v){
        //launch a new activity
    Intent i = new Intent(this, Menu.class);
    startActivity(i);
    }
}
