package com.idoorSys.devicecontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by leocai on 14-12-7.
 */

public class SocketClient {
    private String site;
    private int port;
    private static SocketClient ourInstance = new SocketClient();

    public static SocketClient getInstance() {
        return ourInstance;
    }

    private SocketClient() {
    }

    private Socket client;

    public void connect(String site, int port) {
        this.site = site;
        this.port = port;
        try {
            client = new Socket(site, port);
            System.out.println("Clinet is created");
        } catch (IOException e) {
            e.printStackTrace();
//            throw new IOException("connect failure!");
        }
    }

    public String sendMsg(String msg) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
            out.println(msg);
            out.flush();
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new IOException("send failure!");
        }
		return "";
    }

    public void closeSocket() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
//            throw new IOException("disconnect failure!");
        }
    }
}
