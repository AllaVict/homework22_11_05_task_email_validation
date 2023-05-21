package org.example;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationService {

    UserEmailInput userEmailInput;
    private final int emailInputNumber = 3;

    public boolean emailValidate(InputStream inputStream) {
        userEmailInput = new UserEmailInput(inputStream);
        int emailInputCount = 1;
        String userEmail = "";
        while (emailInputCount <= this.emailInputNumber) {
            emailInputCount++;
            userEmail = userEmailInput.EmailInput();
            try {
                if (emailCheck(userEmail)) {
                    System.out.println("email validation is successful");
                    return emailCheck(userEmail);
                } else {
                    EmailValidationException(emailInputCount);
                }
            } catch (EmailValidationException exception) {
                System.out.println("Validation failed, reason: " + exception.getMessage());
                //  exception.printStackTrace();
            } finally {
                informAboutEmailInputAttemps(userEmail, emailInputCount);
            }

        }
        System.out.println("Finish!");
        return emailCheck(userEmail);
    }

    private static void informAboutEmailInputAttemps(String userEmail, int emailInputCount) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("the number of your attempts to enter an email " + emailInputCount + ", " +
                userEmail + ", " +
                localDateTime.toLocalDate() + ", " +
                localDateTime.toLocalTime());
    }

    private void EmailValidationException(int emailInputCount) {
        throw new EmailValidationException("Email is not valid, you have " + emailInputCount + " attempts");
    }


    public boolean emailCheck(String userEmail) {
        String regEx = "\\w+(\\.)?\\w+(\\d+)?@\\w+\\.(\\w{2,3})";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }
}
