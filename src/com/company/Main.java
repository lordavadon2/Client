package com.company;

import com.company.ClientComm.ConnectionClient;
import com.company.UILayer.ConsoleUI;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите адресс сервера: ");
            String adress = scan.nextLine();
            ConnectionClient client = new ConnectionClient(adress,2000);
//            ConnectionClient client = new ConnectionClient("localhost",2000);
            System.out.println("Соединение с сервером установленно");
            ConsoleUI console = new ConsoleUI(client, scan);
            while (console.mainMenu());
        } catch (IOException e) {
            System.out.println("Ошибка соединения с сервером");
        }
    }
}
