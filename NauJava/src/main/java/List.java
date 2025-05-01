import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class List {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ввод кол-во элементов в массиве
        System.out.print("Количество элементов в списке:");
        int n = scanner.nextInt();

        //генерация списка случайных чисел
        ArrayList<Double> list = generateRandomList(n);

        //вывод исходного списка
        System.out.println("Исходный список: " + list);

        //сортировка списка
        quickSort(list, 0, list.size() - 1);

        // Вывод отсортированного списка
        System.out.println("Отсортированный список: " + list);
    }

    //метод для генерации списка случайных чисел
    private static ArrayList<Double> generateRandomList(int size) {
        ArrayList<Double> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            //генерация чисел от -100.0 до 100.0 с двумя знаками после запятой
            double value = Math.round((random.nextDouble() * 200 - 100) * 100) / 100.0;
            list.add(value);
        }

        return list;
    }

    //реализация быстрой сортировки
    private static void quickSort(ArrayList<Double> list, int low, int high) {
        if (low < high) {
            //индекс опорного элемента
            int pivotIndex = partition(list, low, high);

            //рекурсивная сортировка подсписков
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    //метод для разделения списка
    private static int partition(ArrayList<Double> list, int low, int high) {
        double pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;

                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);

        return i + 1;
    }

    //метод для обмена элементов в списке
    private static void swap(ArrayList<Double> list, int i, int j) {
        double temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
