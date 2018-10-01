package com.example.user22.rmserver;

/*
*  ****************************************************************************
*  * Created by : Md. Azizul Islam on 4/19/2018 at 12:50 PM.
*  * Email : azizul@w3engineers.com
*  * 
*  * Last edited by : Md. Azizul Islam on 4/19/2018.
*  * 
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>  
*  ****************************************************************************
*/


import android.util.Log;

import com.example.user22.rmserver.server.RmServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class RmClient {
    private Socket socket;
    private final int PORT_NUMBER = 9999;
    private String localHostIp = "192.168.2.19";

    public void sendMessage(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket();
                    AppLog.d("Start sending message");
                    InetAddress addr = InetAddress.getByName(localHostIp);
                    SocketAddress sockaddr = new InetSocketAddress(addr, PORT_NUMBER);
                    socket.connect(sockaddr, 8000);
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.write(msg.getBytes());
                    //dos.writeUTF(msg);
                    dos.flush();
                    dos.close();
                    socket.close();
                    AppLog.d("Close sender socket");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
