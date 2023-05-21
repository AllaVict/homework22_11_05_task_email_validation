package org.example;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationServiceTest {

    private EmailValidationService emailValidationService;
    private UserEmailInput userEmailInput;


    @BeforeEach
    public void initiate() {
        userEmailInput = new UserEmailInput(System.in);
        emailValidationService = new EmailValidationService();
    }

    @Nested
    @DisplayName("test emailCheck and EmailInput functionality")
    @Tag("emailCheckandEmailInput")
    class emailCheckEmailInput {
        @Test
        void emailCheck_validEmailAddress_shouldReturnTrue() {
            String emailAddressToValidate = "name_777@gmail.com";
            boolean result = emailValidationService.emailCheck(emailAddressToValidate);
            assertTrue(result);
        }

        @Test
        void emailCheck_notValidEmailAddress_shouldReturnFalse() {
            String emailAddressToValidate = "name@gmail.";
            boolean result = emailValidationService.emailCheck(emailAddressToValidate);
            assertFalse(result);
        }

        @Test
        void EmailInput() {
            String email = "name_777@gmail.com" + "\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(email.getBytes());
            userEmailInput = new UserEmailInput(inputStream);
            String emailAddressToValidate = userEmailInput.EmailInput();
            Assert.assertEquals(emailAddressToValidate, "name_777@gmail.com");
        }

    }

    @Nested
    @DisplayName("test emailValidate functionality")
    @Tag("emailValidate")
    class EmailInput {
        @Test
        public void validate_allValidEmailAddress_shouldReturnTrue() {
            validate_validEmailAddress_shouldReturnTrue("name_777@gmail.com" + "\n");
            validate_validEmailAddress_shouldReturnTrue("name_johnson@gmail.com" + "\n");
            validate_validEmailAddress_shouldReturnTrue("name246@yahoo.lv" + "\n");
            validate_validEmailAddress_shouldReturnTrue("name247@mail.de" + "\n");
            validate_validEmailAddress_shouldReturnTrue("jessica@mail.net" + "\n");
        }

        @Test
        private void validate_validEmailAddress_shouldReturnTrue(String emailAddressToValidate) {
            emailAddressToValidate = emailAddressToValidate;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(emailAddressToValidate.getBytes());
            boolean actualResult = emailValidationService.emailValidate(inputStream);
            assertTrue(actualResult);
        }

        @Test
        public void validate_allNotValidEmailAddress_shouldThrowException() {
            validate_notValidEmailAddress_shouldThrowException(null + "\n");
            validate_notValidEmailAddress_shouldThrowException("" + "\n");
            validate_notValidEmailAddress_shouldThrowException("name" + "\n");
            validate_notValidEmailAddress_shouldThrowException("name@" + "\n");
            validate_notValidEmailAddress_shouldThrowException("name@gmail" + "\n");
            validate_notValidEmailAddress_shouldThrowException("name@gmail." + "\n");
            validate_notValidEmailAddress_shouldThrowException("name245@cyprus.com.cy" + "\n");
        }

        @Test
        private void validate_notValidEmailAddress_shouldThrowException(String emailAddressToValidate) {
            emailAddressToValidate = emailAddressToValidate;
            ByteArrayInputStream inputStream = new ByteArrayInputStream(emailAddressToValidate.getBytes());
            assertThrows(RuntimeException.class, () -> emailValidationService.emailValidate(inputStream));
        }


    }


}