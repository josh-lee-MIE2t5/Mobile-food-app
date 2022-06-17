package com.example.tinas_cafe;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;


public class Menu_pg2 extends AppCompatActivity {
   public static int[] quantity = {0,0,0,0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pg2);
        setTitle("내가 주문이 걸릴 수 있습니다?");
        TextView counter6 = findViewById(R.id.countBox6);
        counter6.setText(Integer.toString(quantity[0]));
        TextView counter7 = findViewById(R.id.countBox7);
        counter7.setText(Integer.toString(quantity[1]));
        TextView counter8 = findViewById(R.id.countBox8);
        counter8.setText(Integer.toString(quantity[2]));
        TextView counter9 = findViewById(R.id.countBox9);
        counter9.setText(Integer.toString(quantity[3]));
    }

    public void foodAdded(View v){
        Button b = (Button) v;
        String buttonText = getResources().getResourceEntryName(b.getId());
        String indAsString = Character.toString(buttonText.charAt(buttonText.length()-1));
        int x = Integer.parseInt(indAsString);
        quantity[x]++;
        TextView counter6 = findViewById(R.id.countBox6);
        counter6.setText(Integer.toString(quantity[0]));
        TextView counter7 = findViewById(R.id.countBox7);
        counter7.setText(Integer.toString(quantity[1]));
        TextView counter8 = findViewById(R.id.countBox8);
        counter8.setText(Integer.toString(quantity[2]));
        TextView counter9 = findViewById(R.id.countBox9);
        counter9.setText(Integer.toString(quantity[3]));
    }

    public void foodSubtracted(View v){
        Button b = (Button) v;
        String buttonText = getResources().getResourceEntryName(b.getId());
        String indAsString = Character.toString(buttonText.charAt(buttonText.length()-1));
        int x = Integer.parseInt(indAsString);
        if (quantity[x] > 0){
            quantity[x]--;
        }
        TextView counter6 = findViewById(R.id.countBox6);
        counter6.setText(Integer.toString(quantity[0]));
        TextView counter7 = findViewById(R.id.countBox7);
        counter7.setText(Integer.toString(quantity[1]));
        TextView counter8 = findViewById(R.id.countBox8);
        counter8.setText(Integer.toString(quantity[2]));
        TextView counter9 = findViewById(R.id.countBox9);
        counter9.setText(Integer.toString(quantity[3]));
    }
    public void sendToOrderPg(View v){
        Intent i = new Intent(this, PaymentPg.class);
        i.putExtra("pg2_stuff", quantity);
        i.putExtra("pg1_stuff",Menu.quantity);
        startActivity(i);
    }
    public void previousPg(View v){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

}