package com.example.user22.rmserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user22.rmserver.server.RmServer;

public class MainActivity extends AppCompatActivity {
    private RmServer rmServer;
    private RmClient rmClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rmServer = new RmServer(getApplicationContext());
        rmClient = new RmClient();
    }

    public void onStartServer(View v){
        rmServer.runServer();
    }

    public void onSendMessage(View v){
        rmClient.sendMessage("Hello from client");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rmServer.stopServer();
    }


}
