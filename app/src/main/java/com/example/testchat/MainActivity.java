package com.example.testchat;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends Activity {

    Connect connect = new Connect();
    Thread thread = new Thread();

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info1);
        connect.doInBackground(new String[1]);
        info.setText("Запускаем процесс");


    }


}

class Connect extends AsyncTask<String, Void, Object> {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    @Override
    protected String doInBackground(String... strs) {

        try {
            // адрес - локальный хост, порт - 4004, такой же как у сервера
            Socket clientSocket = new Socket("192.168.0.239", 4004);
            //  у сервера доступ на соединение
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            for (String string : strs) {
                dout.writeUTF(string);
            }

            dout.flush();
            dout.close();
            clientSocket.close();


//            reader = new BufferedReader(new InputStreamReader(System.in));
            // читать соообщения с сервера
//            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // писать туда же
//            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            System.out.println("Вы что-то хотели сказать? Введите это здесь:");
            // если соединение произошло и потоки успешно созданы - мы можем
            //  работать дальше и предложить клиенту что то ввести
            // если нет - вылетит исключение
//            String word = reader.readLine(); // ждём пока клиент что-нибудь
            // не напишет в консоль
//            out.write(word + "\n"); // отправляем сообщение на сервер
//            out.flush();
//            String serverWord = in.readLine(); // ждём, что скажет сервер
//            System.out.println(serverWord); // получив - выводим на экран
        } catch (Exception e) {
            System.out.println("Увы, с сетью проблема"); //TODO тут что-то происходит, но что - неясно.
        }
        return null;

    }
}
