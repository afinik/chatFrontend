package com.example.testchat;


import android.app.Activity;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends Activity {



    Chat chat = new Chat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView info = (TextView) findViewById(R.id.info1);
        chat.run();
        info.setText("Запускаем процесс");
    }

//    MainActivity(String nick) {
//        this.nick = nick;
//    }





}

class Chat extends Thread{

    public String nick;

    @Override
    public void run() {

        System.out.println("Инициализация подключения к серверу");

        try (Socket socket = new Socket("localhost", 10001);
             BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            System.out.println("Соединение установлено");

//            info.setText("Соединение установлено");

            PrintWriter pw = new PrintWriter(bw, true);
            pw.println(nick);
            String[] messages = {"Message1", "Message2", "exit"};//сообщения от клиента, последнее -команда выхода/завершенимя сеанса
            AtomicInteger i = new AtomicInteger(0);
            while (true) {
                String clientMessage = messages[i.get()];
                pw.println(clientMessage);
                String messageServer = "";
                String st = " ";
                while ((st != null) && br.ready()) {
                    st = br.readLine();
                    messageServer += st + "\n";
                }
                System.out.println(messageServer);//ответ сервера
                i.getAndIncrement();
                if (i.intValue() > 2) break;
                Thread.sleep(50);
            }
        } catch (UnknownHostException | FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (NetworkOnMainThreadException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Соединение разорвано");
        }
    }

}