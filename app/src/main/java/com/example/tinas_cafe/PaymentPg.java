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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class PaymentPg extends AppCompatActivity {
    public static int[] prices1 = {6, 8, 8, 9, 7, 7};//holds the price of each item with respect to the index
    public static int[] prices2 = {5, 5, 4, 4};
    public static String[] items1 = {"Kimchi Fried Rice with Fried Egg", "Saeng Sun Jun (Korean Pan-Fried Fish)", "Tong Dak (Korean BBQ Chicken Wings)", "Bossam (Korean Pork Belly)", "Kimchi Jjigae (Spicy Kimchi Stew)", "Bugo Gook (Dried Pollack Soup)"};
    public static String[] items2 = {"Pa Jun (Korean Pancake With Scallions)", "Gaeran Mari (Korean Rolled Egg Omelette)", "Tteok-bokki (Spicy Rice Cakes)", "Odeng (Fish Cakes with broth)"};
    int sumtotal = 0;
    float fltTotal;
    EditText emailaddress, homeaddress;
    private Button b;
    public String msg;
    public String orderSummary = "";

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

        homeaddress = (EditText)findViewById(R.id.customers_address);
        b = findViewById(R.id.Placeord);

        msg = "Thank you for ordering from Tina's cafe\n" + "You have ordered:\n" + orderSummary + totalAsString + "\nAddress: ";

    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            msg += homeaddress.getText().toString();
            fltTotal = (float) sumtotal;
    if (isValidEmailAddress(emailaddress.getText().toString()) & (!homeaddress.getText().toString().equals("")) & fltTotal != 0){
            sendOrdData(quantity1 , quantity2); //order might matter in this case because new orderid is set based on the max orderid in customer_info table
            sendCustData(emailaddress.getText().toString(), homeaddress.getText().toString(), "preparing order", fltTotal);
            emailSend();
    }else if(!isValidEmailAddress(emailaddress.getText().toString())){
        emaildInvalid();
    }else{
        missingField();
    }
        }
    });
    }

    public void sendCustData(String email, String address, String status, float total){
        Log.d("address", address);
        Log.d("status", status);
        Log.d("total",Float.toString(total));
        StringRequest reguest_Cust = new StringRequest(Request.Method.POST,
                Constants.Url_Add_Cust,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("json obj", jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    Log.e("error", error.getMessage());
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("email_address", email);
                params.put("home_address", address);
                params.put("state_of_order", status);
                params.put("total", Float.toString(total));
                return params;
            }
        };
      RequestQueue requestQueue = Volley.newRequestQueue(this);
      requestQueue.add(reguest_Cust);
    }


    public void sendOrdData(int[] quantity1, int[] quantity2){
        StringRequest reguest_Ord = new StringRequest(Request.Method.POST,
                Constants.url_Add_Ord,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("json obj", jsonObject.getString("message"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", error.getMessage());
                    }
                }

        ){
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("item1", Integer.toString(quantity1[0]));
                params.put("item2", Integer.toString(quantity1[1]));
                params.put("item3", Integer.toString(quantity1[2]));
                params.put("item4", Integer.toString(quantity1[3]));
                params.put("item5", Integer.toString(quantity1[4]));
                params.put("item6", Integer.toString(quantity1[5]));
                params.put("item7", Integer.toString(quantity2[0]));
                params.put("item8", Integer.toString(quantity2[1]));
                params.put("item9", Integer.toString(quantity2[2]));
                params.put("item10", Integer.toString(quantity2[3]));

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(reguest_Ord);
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void emailSend(){
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

    public void emaildInvalid(){
        Toast toast = Toast.makeText(this, "Email invalid", Toast.LENGTH_SHORT);
        toast.show();
    }

    public  void missingField(){//change this to see if address if valid or not in a possible update
        Toast toast = Toast.makeText(this, "Missing fields or order is empty", Toast.LENGTH_SHORT);
        toast.show();
    }


}