package com.example.user22.rmserver.server;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 4/19/2018 at 11:47 AM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 4/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import com.example.user22.rmserver.App;
import com.example.user22.rmserver.AppLog;

import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class RmServer implements Runnable {
    private final int PORT_NUMBER = 5000;
    private Thread mThread;
    private boolean isRunning;
    private ServerSocket serverSocket;
    private Handler handler;
    private Context context;
    public RmServer(Context context) {
        this.context = context;
        try {
            handler = new Handler(Looper.getMainLooper());
            mThread = new Thread(this,"rmthread");
            mThread.setDaemon(true);
            serverSocket = new ServerSocket(PORT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean runServer() {
        if (isRunning) return false;
        isRunning = true;
        mThread.start();
        AppLog.d("Server started");
        return true;
    }

    public boolean stopServer() {
        if (!isRunning) return false;
        isRunning = false;
        mThread.interrupt();
        AppLog.d("Server stopped");
        return true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                AppLog.d("Server is running");
                Socket socket = serverSocket.accept();
                DataInputStream DIS = new DataInputStream(socket.getInputStream());
                byte[] recvBuf = IOUtils.toByteArray(DIS);
                String receivedData = new String(recvBuf);
                AppLog.d("Received message ="+getLocalAddress());
                processMessage(receivedData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processMessage(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(App.getContext(),msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    private String getLocalAddress() throws IOException {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        return ip;
    }
}
