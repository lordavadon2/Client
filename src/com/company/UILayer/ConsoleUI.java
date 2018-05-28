package com.company.UILayer;

import com.company.ClientComm.*;
import com.company.ProgressBar.*;

import java.net.SocketException;
import java.util.Scanner;
import static com.company.UILayer.ConsoleStrings.*;

public class ConsoleUI implements IConsoleUI {

    IConnectionClient client;
    Scanner scan;
    Progress pb;

    public ConsoleUI(IConnectionClient client, Scanner scan, Progress pb) {
        this.client = client;
        this.scan = scan;
        this.pb = pb;
    }

    @Override
    public void transfer(String command, String path){
        transfer(command, path, "");
    }

    @Override
    public void transfer(String command, String path, String data){
        Thread progress = new Thread(pb);
        progress.start();
        client.write(command + "#\\" + path + "#" + data);
        try {
            String result = client.read();
            pb.setEnabled(false);
            Thread.sleep(100);
            String[] listResult = result.split("#");
            for (String str : listResult) {
                    System.out.println(str);
            }
        }catch (SocketException e){
            pb.setErrorFlag(true);
            pb.setEnabled(false);
            System.out.println("\rОшибка: " + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String preMenu(String text){
        System.out.println(text);
        return scan.nextLine();
    }

    @Override
    public void showDirFile() {
        transfer("show", preMenu(PATH));    // Показать файлы и директории
    }

    @Override
    public void createFile() {
        transfer("fcreate", preMenu(FILENAME) + ".txt");     //Создать файл
    }

    @Override
    public void changeFile() {
        transfer("fchange", preMenu(FILENAME) + ".txt", preMenu(TEXTCHANGE));   //Изменить текст в файле
    }

    @Override
    public void viewFile() {
        transfer("fshow", preMenu(FILENAME) + ".txt");       //Показать текст в файле
    }

    @Override
    public void removeFile() {
        transfer("frem", preMenu(FILENAME) + ".txt");        //Удалить файл
    }

    @Override
    public void createDir() {
        transfer("dcreate", preMenu(FILENAME));        //Создать директорию
    }

    @Override
    public void removeDir() {
        transfer("drem", preMenu(FILENAME));        //Удалить директорию
    }

    @Override
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
