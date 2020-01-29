package com.example.testchat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends Activity {

//    Connect connect = new Connect();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info1);

        new Thread(() -> {connection();}).start();
        info.setText("Запускаем процесс");


    }

    private void connection() {
        try(Socket socket = new Socket("192.168.1.8", 4004)){
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // BufferedReader fileReader = new BufferedReader(new FileReader("./httpPackage"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}


