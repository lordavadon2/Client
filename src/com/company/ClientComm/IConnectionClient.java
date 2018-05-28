package com.company.ClientComm;

import java.net.SocketException;

public interface IConnectionClient {
    String read()throws SocketException;

    void write(String data);
}
