package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Открыть файл");
            System.out.println("2. Редактировать файл");
            System.out.println("3. Сохранить файл");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); //чтение пустой строки после числа

            switch (choice) {
                case 1:
                    openFile(scanner);
                    break;
                case 2:
                    editFile(scanner);
                    break;
                case 3:
                    saveFile(scanner);
                    break;
                case 4:
                    System.out.println("Выход из редактора.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверное значение. Пожалуйста, выберите снова.");
            }
        }
    }

    private static void openFile(Scanner scanner) { //открытие
        System.out.println("Введите имя файла для открытия: ");
        String fileName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("--- Содержимое файла " + fileName + " ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при открытии файла: " + e.getMessage());
        }
    }

    private static void editFile(Scanner scanner) {//редактирование
        System.out.println("Введите имя файла для редактирования: ");
        String fileName = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            System.out.println("Введите текст для добавления в файл (для завершения введите строку 'exit'):");
            String input;
            while (!(input = scanner.nextLine()).equals("exit")) {
                writer.println(input);
            }
            System.out.println("Файл был отредактирован.");
        } catch (IOException e) {
            System.out.println("Ошибка при редактировании файла: " + e.getMessage());
        }
    }

    private static void saveFile(Scanner scanner) {//сохранение
        System.out.println("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();

        System.out.println("Введите текст для сохранения в файл (для завершения введите строку 'exit'):");

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            String input;
            while (!(input = scanner.nextLine()).equals("exit")) {
                writer.println(input);
            }
            System.out.println("Файл был успешно сохранен.");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }
}
