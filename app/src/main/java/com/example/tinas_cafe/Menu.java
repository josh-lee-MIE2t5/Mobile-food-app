package com.example.tinas_cafe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;



public class Menu extends AppCompatActivity {
    public static int[] quantity = {0,0,0,0,0,0};//set to zero initially
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("내가 주문이 걸릴 수 있습니다?");
        TextView counter0 = findViewById(R.id.countBox0);
        counter0.setText(Integer.toString(quantity[0]));
        TextView counter1 = findViewById(R.id.countBox1);
        counter1.setText(Integer.toString(quantity[1]));
        TextView counter2 = findViewById(R.id.countBox2);
        counter2.setText(Integer.toString(quantity[2]));
        TextView counter3 = findViewById(R.id.countBox3);
        counter3.setText(Integer.toString(quantity[3]));
        TextView counter4 = findViewById(R.id.countBox4);
        counter4.setText(Integer.toString(quantity[4]));
        TextView counter5 = findViewById(R.id.countBox5);
        counter5.setText(Integer.toString(quantity[5]));
    }

    public void foodAdded(View v){
        Button b = (Button) v;
        String buttonText = getResources().getResourceEntryName(b.getId());
        char index = buttonText.charAt(buttonText.length()-1);
        String indAsStr = Character.toString(index);
        int x;
        x = Integer.parseInt(indAsStr);
        quantity[x]++;
       TextView counter0 = findViewById(R.id.countBox0);//this is very tedious and ineffiecient but to change the count we set a view to each count box and set it with every iteration
       counter0.setText(Integer.toString(quantity[0]));
        TextView counter1 = findViewById(R.id.countBox1);
        counter1.setText(Integer.toString(quantity[1]));
        TextView counter2 = findViewById(R.id.countBox2);
        counter2.setText(Integer.toString(quantity[2]));
        TextView counter3 = findViewById(R.id.countBox3);
        counter3.setText(Integer.toString(quantity[3]));
        TextView counter4 = findViewById(R.id.countBox4);
        counter4.setText(Integer.toString(quantity[4]));
        TextView counter5 = findViewById(R.id.countBox5);
        counter5.setText(Integer.toString(quantity[5]));//come back to this later and make it more effecient
    }

    public void foodSubtracted(View v){
        Button b = (Button) v;
        String buttonText = getResources().getResourceEntryName(b.getId());
        char index = buttonText.charAt(buttonText.length()-1);
        String indAsStr  = Character.toString(index);
        int x;
        x = Integer.parseInt(indAsStr);
        if (quantity[x]>0) {
            quantity[x]--;
            TextView counter0 = findViewById(R.id.countBox0);//same idea as foodAdded
            counter0.setText(Integer.toString(quantity[0]));
            TextView counter1 = findViewById(R.id.countBox1);
            counter1.setText(Integer.toString(quantity[1]));
            TextView counter2 = findViewById(R.id.countBox2);
            counter2.setText(Integer.toString(quantity[2]));
            TextView counter3 = findViewById(R.id.countBox3);
            counter3.setText(Integer.toString(quantity[3]));
            TextView counter4 = findViewById(R.id.countBox4);
            counter4.setText(Integer.toString(quantity[4]));
            TextView counter5 = findViewById(R.id.countBox5);
            counter5.setText(Integer.toString(quantity[5]));
        }
    }

    public void sendToOrderPg(View v){
        Intent i = new Intent(this, PaymentPg.class);
        i.putExtra("pg1_stuff", quantity);
        i.putExtra("pg2_stuff", Menu_pg2.quantity);
        startActivity(i);
    }

    public void nextPg(View v){
        Intent i = new Intent(this, Menu_pg2.class);
        startActivity(i);
    }
}