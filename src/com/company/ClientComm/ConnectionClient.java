package com.company.ClientComm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Brainacad4 on 24.05.2018.
 */
public class ConnectionClient {

    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public ConnectionClient(String host, int port) throws IOException {
        socket = new Socket(host,port);
        writer = new PrintWriter(socket.getOutputStream(),true);
        InputStreamReader stream = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(stream);
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String data)
    {
        writer.println(data);
    }
}
