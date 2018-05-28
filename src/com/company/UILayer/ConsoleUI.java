package com.company.UILayer;

import com.company.ClientComm.ConnectionClient;

import java.util.Scanner;
import static com.company.UILayer.ConsoleStrings.*;

public class ConsoleUI {

    ConnectionClient client;
    Scanner scan;

    public ConsoleUI(ConnectionClient client, Scanner scan) {
        this.client = client;
        this.scan = scan;
    }

    private void transfer(String command, String path){
        transfer(command, path, "");
    }

    private void transfer(String command, String path, String data){
        client.write(command + "#\\" + path + "#" + data);
        String result = client.read();
        String[] listResult = result.split("#");
        for (String str: listResult) {
            System.out.println(str);
        }
    }

    private String preMenu(String text){
        System.out.println(text);
        return scan.nextLine();
    }

    public void showDirFile(){
        transfer("show", preMenu(PATH));    // Показать файлы и директории
    }

    public void createFile(){
        transfer("fcreate", preMenu(FILENAME) + ".txt");     //Создать файл
    }

    public void changeFile(){
        transfer("fchange", preMenu(FILENAME) + ".txt", preMenu(TEXTCHANGE));   //Изменить текст в файле
    }

    public void viewFile(){
        transfer("fshow", preMenu(FILENAME) + ".txt");       //Показать текст в файле
    }

    public void removeFile(){
        transfer("frem", preMenu(FILENAME) + ".txt");        //Удалить файл
    }

    public void createDir(){
        transfer("dcreate", preMenu(FILENAME));        //Создать директорию
    }

    public void removeDir(){
        transfer("drem", preMenu(FILENAME));        //Удалить директорию
    }

    public boolean mainMenu() {
        while (true) {
            System.out.println(GETMAINMENUTEXT);
            try {
                Integer res = Integer.parseInt(scan.nextLine());
                switch (res) {
                    case 0:
                        showDirFile();
                        return true;
                    case 1:
                        createFile();
                        return true;
                    case 2:
                        changeFile();
                        return true;
                    case 3:
                        viewFile();
                        return true;
                    case 4:
                        removeFile();
                        return true;
                    case 5:
                        createDir();
                        return true;
                    case 6:
                        removeDir();
                        return true;
                    case 7:
                        return false;
                    default:
                        System.out.println(WARNING);
                }
            }catch (NumberFormatException e){
                System.out.println(WARNING);
            }
        }
    }
}
