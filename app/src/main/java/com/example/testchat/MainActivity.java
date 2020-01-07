package com.example.testchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String nick;
    ChatClient chatClient = new ChatClient(nick);

    //    Button setNickButton = (Button) findViewById(R.id.set_nick_button);
    // Button sendButton = (Button) findViewById(R.id.send_button);
//    EditText nickName = findViewById(R.id.nickname);
    // EditText message = findViewById(R.id.message);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        System.out.println("test");
    }
}
