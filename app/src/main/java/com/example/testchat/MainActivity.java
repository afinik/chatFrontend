package com.example.testchat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.net.Socket;

public class MainActivity extends Activity {

//    Connect connect = new Connect();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info1);
        new Thread(() -> {connection();}).start();new Thread(() -> {connection();}).start();
        info.setText("Запускаем процесс");


    }

    private void connection() {
        try {
            Socket socket = new Socket("192.168.1.8", 4004);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}


