package com.company;

import com.company.ClientComm.*;
import com.company.ProgressBar.*;
import com.company.UILayer.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите адресс сервера: ");
            String adress = scan.nextLine();
            IConnectionClient client = new ConnectionClient(adress,2000);
//            ConnectionClient client = new ConnectionClient("localhost",2000);
            Progress pb = new Progress();
            System.out.println("Соединение с сервером установленно");
            IConsoleUI console = new ConsoleUI(client, scan, pb);
            while (console.mainMenu());
        } catch (IOException e) {
            System.out.println("Ошибка соединения с сервером");
        }
    }
}
