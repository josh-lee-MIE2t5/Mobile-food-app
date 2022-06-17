package com.example.tinas_cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PaymentPg extends AppCompatActivity {
    public static int[] prices1 = {6, 8, 8, 9, 7, 7};//holds the price of each item with respect to the index
    public static int[] prices2 = {5, 5, 4, 4};
    public static String[] items1 = {"Kimchi Fried Rice with Fried Egg", "Saeng Sun Jun (Korean Pan-Fried Fish)", "Tong Dak (Korean BBQ Chicken Wings)", "Bossam (Korean Pork Belly)", "Kimchi Jjigae (Spicy Kimchi Stew)", "Bugo Gook (Dried Pollack Soup)"};
    public static String[] items2 = {"Pa Jun (Korean Pancake With Scallions)", "Gaeran Mari (Korean Rolled Egg Omelette)", "Tteok-bokki (Spicy Rice Cakes)", "Odeng (Fish Cakes with broth)"};
    int sumtotal = 0;
    EditText emailaddress, homeaddress;
    private Button b;
    public String msg;
    public String orderSummary = "";
    public String strAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_pg);
        setTitle("주문 세부정보는 다음과 같습니다");

        int[] quantity1 = getIntent().getIntArrayExtra("pg1_stuff");
        int[] quantity2 = getIntent().getIntArrayExtra("pg2_stuff");
        for (int x = 0; x < quantity1.length; x++) {
            sumtotal += prices1[x] * quantity1[x];
        }
        for (int x = 0; x < quantity2.length; x++) {
            sumtotal += prices2[x] * quantity2[x];
        }
        String totalAsString = "Total: $" + sumtotal;
        TextView ttl = findViewById(R.id.total);
        ttl.setText(totalAsString);


        for (int x = 0; x < quantity1.length; x++) {
            if (quantity1[x] > 0) {
                orderSummary += items1[x] + "  |  Quantity: " + quantity1[x] + "  |  $" + quantity1[x] * prices1[x] + "\n";
            }
        }

        for (int x = 0; x < quantity2.length; x++) {
            if (quantity2[x] > 0) {
                orderSummary += items2[x] + "  |  Quantity: " + quantity2[x] + "  |  $" + quantity2[x] * prices2[x] + "\n";
            }
        }
        TextView foodOrderedDisplay = findViewById(R.id.summaryOfOrder);
        foodOrderedDisplay.setText(orderSummary);


        emailaddress = findViewById(R.id.customers_email);
//        homeaddress = findViewById(R.id.customers_address);
//        strAddress = homeaddress.getText().toString();

        homeaddress = (EditText)findViewById(R.id.customers_address);
        b = findViewById(R.id.Placeord);

        msg = "Thank you for ordering from Tina's cafe\n" + "You have ordered:\n" + orderSummary + totalAsString + "\nAddress: ";

    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msg += homeaddress.getText().toString();
            sendplz();
        }
    });
    }

    public void sendplz(){
       EmailSend cnfrm = new EmailSend(this, emailaddress.getText().toString() ,msg);
       cnfrm.execute();
        Toast toast = Toast.makeText(this,"Order confirmed", Toast.LENGTH_SHORT);
        toast.show();
        Intent i = new Intent(this, MainActivity.class);
        for(int x = 0; x < Menu.quantity.length; x++){
            Menu.quantity[x] = 0;
        }
        for(int x = 0; x < Menu_pg2.quantity.length; x++){
            Menu_pg2.quantity[x] = 0;
        }
        startActivity(i);
    }
    }