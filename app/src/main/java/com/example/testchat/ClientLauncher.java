package com.example.testchat;

public class ClientLauncher { //класс для запуска n-го количества заглушек клиентский частей
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {

            ChatClient c = new ChatClient("nick" + i);
            c.start();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
