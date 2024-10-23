import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

class CustomEmailFormatException extends Exception {
    public CustomEmailFormatException(String message) {
        super(message);
    }
}

public class EmailValidator {
    public static void main(String[] args) {
        String email = "pavel.zamorskiy@";
        
        try {
            validateEmail(email);
        } catch (CustomEmailFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
            logErrorToFile(e.getMessage());
        }
    }
    
    public static void validateEmail(String email) throws CustomEmailFormatException {
        if (!email.contains("@")) {
            throw new CustomEmailFormatException("Некорректный формат email-адреса. ВЫ ПРОПУСТИЛИ @");
        }
        else if (!email.matches(".+\\.[a-zA-Z]{2,}$")) {
            throw new CustomEmailFormatException("Некорректный формат email-адреса. ВЫ НЕ УКАЗАЛИ ИЛИ НЕПРАВИЛЬНО УКАЗАЛИ ДОМЕННОЕ ИМЯ");
        }
        else if (!email.matches("^.+@*")) {
            throw new CustomEmailFormatException("Некорректный формат email-адреса. ВЫ НЕ УКАЗАЛИ ПОЛЬЗОВАТЕЛЯ");
        }
        else {
            System.out.println("Email - " + email + " корректен.");
        }
    }

    public static void logErrorToFile(String errorMessage) {
        String logMessage = "Ошибка: " + errorMessage + "\n";
        try (FileOutputStream fos = new FileOutputStream("errors.log", true)) {
            fos.write(logMessage.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Не удалось записать ошибку в файл: " + e.getMessage());
        }
    }
}

