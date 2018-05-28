package com.company.ClientComm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Brainacad4 on 24.05.2018.
 */
public class ConnectionClient implements IConnectionClient {

    Socket socket;
    PrintWriter writer;
    BufferedReader reader;

    public ConnectionClient(String host, int port) throws IOException {
        socket = new Socket(host,port);
        writer = new PrintWriter(socket.getOutputStream(),true);
        InputStreamReader stream = new InputStreamReader(socket.getInputStream());
        reader = new BufferedReader(stream);
    }

    @Override
    public String read()throws SocketException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new SocketException("соединение с сервером отсутствует");
        }
    }

    @Override
    public void write(String data)
    {
        writer.println(data);
    }
}
