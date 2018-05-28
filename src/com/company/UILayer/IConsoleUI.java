package com.company.UILayer;

public interface IConsoleUI {
    void transfer(String command, String path);

    void transfer(String command, String path, String data);

    String preMenu(String text);

    void showDirFile();

    void createFile();

    void changeFile();

    void viewFile();

    void removeFile();

    void createDir();

    void removeDir();

    boolean mainMenu();
}
