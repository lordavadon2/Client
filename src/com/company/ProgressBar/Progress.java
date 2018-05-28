package com.company.ProgressBar;

public class Progress implements Runnable {

    boolean isEnabled;
    boolean errorFlag;

    public Progress() {
        this.isEnabled = true;
        this.errorFlag = false;
    }



    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    @Override
    public void run() {
        int i = 0;
        System.out.println("\nИдет передача данных");
        while (isEnabled){
            System.out.print("*");
            if (i == 50){
                System.out.print("\r");
                i = 0;
            }
            i++;
        }
        if (!errorFlag){
            System.out.println("\rВыполнено\n");
        }
    }
}
