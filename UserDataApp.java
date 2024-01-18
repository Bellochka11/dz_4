import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите данные (фамилия имя отчество дата рождения номер телефона пол): ");
            String userData = scanner.nextLine();

            String[] userDataArray = userData.split(" ");

            if (userDataArray.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String surname = userDataArray[0];
            String name = userDataArray[1];
            String patronymic = userDataArray[2];
            String dateOfBirth = userDataArray[3];
            long phoneNumber = Long.parseLong(userDataArray[4]);
            char gender = userDataArray[5].charAt(0);

            if (!isValidDate(dateOfBirth)) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }

            if (!isValidGender(gender)) {
                throw new IllegalArgumentException("Неверное значение пола");
            }

            String filename = surname + ".txt";
            String userDataString = surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender;

            writeFile(filename, userDataString);
            System.out.println("Данные успешно записаны в файл " + filename);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean isValidDate(String date) {
        return date.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$");
    }

    private static boolean isValidGender(char gender) {
        return gender == 'f' || gender == 'm';
    }

    private static void writeFile(String filename, String userData) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(userData);
            writer.newLine();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}