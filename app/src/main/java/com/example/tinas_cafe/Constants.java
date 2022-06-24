package com.example.tinas_cafe;

import android.os.AsyncTask;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.channels.AsynchronousChannelGroup;
//extends AsyncTask<Void, Void, Void>
public class Constants {
//  InetAddress ip;
//  String hostname;
//  String IpAddress;
private static final String rootUrl = "http://192.168.2.16/Tinas_cafe/v1/";
static final String Url_Add_Cust = rootUrl + "registerCust.php";
static final String url_Add_Ord = rootUrl + "registerOrd.php";
private static final String user = "tinas_cafe_from_app";
private static final String pass = "L-]B_JRd1EKJrnpP";
//    @Override
//    protected Void doInBackground(Void... voids) {
//        try {
//            ip = InetAddress.getLocalHost();
//            hostname = ip.getHostName();
//            IpAddress = ip.getHostAddress();
//            Log.d("hostname",hostname);
//            Log.d("IpAddress", IpAddress);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }



}
