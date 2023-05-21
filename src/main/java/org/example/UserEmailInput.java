package org.example;

import java.io.InputStream;
import java.util.Scanner;

public class UserEmailInput {

    private String userEmail;
    private Scanner scanner;


    public UserEmailInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public String EmailInput() {
        // получение email от пользователя
        System.out.println("Enter email ");
        userEmail = scanner.nextLine();
        return userEmail;
    }


    public String getUserEmail() {
        return userEmail;
    }


//    @NotNull
//    private Scanner getScanner() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter email:");
//        return scanner;
//    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
