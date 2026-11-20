package com.team.starsort.ui;

import com.team.starsort.collection.CustomArrayList;
import com.team.starsort.collection.CustomList;
import com.team.starsort.filling.DataFiller;
import com.team.starsort.filling.FileFiller;
import com.team.starsort.filling.ManualFiller;
import com.team.starsort.filling.RandomFiller;
import com.team.starsort.model.Star;
import com.team.starsort.model.StarComparators;
import com.team.starsort.sorting.BubbleSort;
import com.team.starsort.sorting.MergeSort;
import com.team.starsort.sorting.ParitySort;
import com.team.starsort.sorting.QuickSort;
import com.team.starsort.sorting.SortContext;
import com.team.starsort.sorting.SortStrategy;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Scanner;

public class ConsoleUI {

    private final Scanner scanner = new Scanner(System.in);
    private CustomList<Star> lastSorted;

    public void run() {
        while (true) {
            printMenu();
            int choice = readInt("Выбор: ");
            System.out.println();
            try {
                switch (choice) {
                    case 1 -> doFillAndSort();
                    case 2 -> doCountOccurrences();
                    case 3 -> doWriteToFile();
                    case 0 -> {
                        System.out.println("До свидания!");
                        return;
                    }
                    default -> System.out.println("Неверный выбор, попробуйте снова");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== Star Sorting App ===");
        System.out.println("1. Заполнить и отсортировать");
        System.out.println("2. Подсчитать вхождения (доп.4)");
        System.out.println("3. Записать в файл (доп.2)");
        System.out.println("0. Выход");
    }

    private void doFillAndSort() throws Exception {
        CustomList<Star> list = fillList();
        if (list.size() == 0) {
            System.out.println("Коллекция пуста, сортировать нечего");
            return;
        }
        System.out.println("Заполнено " + list.size() + " элементов");

        Comparator<Star> comparator = chooseComparator();
        SortStrategy<Star> strategy = chooseStrategy();

        System.out.println("\nПрименить чёт/нечет (доп.1, по полю distance)? 1) нет  2) да");
        if (readInt("Выбор: ") == 2) {
            strategy = new ParitySort<>(
                    Star::getDistanceLightYears,
                    () -> new CustomArrayList<>(),
                    strategy
            );
        }

        SortContext<Star> context = new SortContext<>(list, strategy, comparator);
        context.sort();

        System.out.println("\nРезультат:");
        printList(list);
        lastSorted = list;
    }

    private CustomList<Star> fillList() throws Exception {
        System.out.println("Способ заполнения: 1) рандом  2) вручную  3) из файла");
        int choice = readInt("Выбор: ");
        if (choice < 1 || choice > 3) {
            throw new IllegalArgumentException("Неверный способ заполнения");
        }

        int size = readInt("Длина коллекции: ");
        if (size < 0) {
            throw new IllegalArgumentException("Длина не может быть отрицательной");
        }

        DataFiller<Star> filler = switch (choice) {
            case 1 -> new RandomFiller();
            case 2 -> new ManualFiller(scanner);
            case 3 -> {
                System.out.print("Путь к файлу: ");
                yield new FileFiller(Path.of(scanner.nextLine().trim()));
            }
            default -> throw new IllegalStateException();
        };

        return filler.fill(size);
    }

    private Comparator<Star> chooseComparator() {
        System.out.println("\nПоле сортировки: 1) name  2) magnitude  3) distance");
        int choice = readInt("Выбор: ");
        return switch (choice) {
            case 1 -> StarComparators.BY_NAME;
            case 2 -> StarComparators.BY_MAGNITUDE;
            case 3 -> StarComparators.BY_DISTANCE;
            default -> throw new IllegalArgumentException("Неверное поле");
        };
    }

    private SortStrategy<Star> chooseStrategy() {
        System.out.println("\nАлгоритм: 1) Bubble  2) Quick  3) Merge");
        int choice = readInt("Выбор: ");
        return switch (choice) {
            case 1 -> new BubbleSort<>();
            case 2 -> new QuickSort<>();
            case 3 -> new MergeSort<>();
            default -> throw new IllegalArgumentException("Неверный алгоритм");
        };
    }

    private void doCountOccurrences() {
        if (lastSorted == null) {
            System.out.println("Сначала отсортируйте что-нибудь (пункт 1)");
            return;
        }
        // TODO: заменить на CounterService от #3 (доп.4)
        System.out.println("TODO: доп.4 — нужен CounterService от #3");
    }

    private void doWriteToFile() {
        if (lastSorted == null) {
            System.out.println("Сначала отсортируйте что-нибудь (пункт 1)");
            return;
        }
        // TODO: заменить на FileAppenderService от #4 (доп.2)
        System.out.println("TODO: доп.2 — нужен FileAppenderService от #4");
    }

    private void printList(CustomList<Star> list) {
        for (Star s : list) {
            System.out.println(s);
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число");
            }
        }
    }
}