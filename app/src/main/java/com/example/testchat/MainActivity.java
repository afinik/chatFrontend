package com.example.testchat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends Activity {

//    Connect connect = new Connect();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info1);
        TextView message = (TextView) findViewById(R.id.textViewMessage);

        info.post(()->{
        });

        Handler handler = new Handler(Looper.getMainLooper());
//        handler.post()

//        new Thread(() -> {
//            connection();
//            runOnUiThread(()->{});
//        }).start();
        info.setText("Запускаем процесс");
        testAsync();


    }

    private static class MyAsyncTask extends AsyncTask<Integer,Void,Integer>{
        WeakReference<MainActivity> ref;
        MyAsyncTask(MainActivity activity){
            ref = new WeakReference<>(activity);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 5;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if( ref.get()!= null){

            }
        }
    }

    private void testAsync() {
        new AsyncTask<Integer,Void,Integer>(){

            @Override
            protected Integer doInBackground(Integer... voids)  {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 5;
            }

            @Override
            protected void onPreExecute() {
                //ui

            }

            @Override
            protected void onPostExecute(Integer integer) {
                Log.e("LYOSHA", ""+integer);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onCancelled(Integer integer) {
                super.onCancelled(integer);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute(1,2,3,4,5);
    }
    private void connection() {

        try(Socket socket = new Socket("192.168.0.239", 4004);

//            System.out.println("test0");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println("test1");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String nick = "nick";
            runOnUiThread(()->{
                Log.d("ddw", "dsddw");
                System.out.println("ddwdwdw1111");
            });




            System.out.println("Соединение установлено");
            Thread.sleep(1000);
            PrintWriter pw = new PrintWriter(bw, true);
            pw.println(nick);
            Thread.sleep(50);
            String[] messages = {"Message1" + nick, "Message2" + nick, "exit"};//сообщения от клиента, последнее -команда выхода/завершенимя сеанса
            exit:
            while (true) {
                for (String clientMessage : messages) {
                    pw.println(clientMessage);

                    String messageServer = "";
                    while (((messageServer = br.readLine()) != null) && br.ready()) {
                        System.out.println(messageServer);
                    }
                    if (messageServer.equalsIgnoreCase("shutdown")) {
                        Thread.sleep(100);
                        break exit;
                    }

                    Thread.sleep(50);
                }

            }
        } catch (UnknownHostException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.out.println("Соединение разорвано");
        }
    }



}


