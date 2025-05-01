import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ввод кол-во элементов в массиве
        System.out.print("Количество элементов в массиве:");
        int n = scanner.nextInt();

        //заполнение массива
        int[] array = generateRandomArray(n);

        //вывод массива, заполненного случайными числами
        System.out.println("Сгенерированный массив: " + Arrays.toString(array));

        //поиск минимального
        Integer minAbsValue = findMinAbsValue(array);

        //вывод результата
        if (minAbsValue != null) {
            System.out.println("Минимальное значение по модулю в массиве: " + minAbsValue);
        } else {
            System.out.println("Массив пустой");
        }
    }

    //метод для генерации массива случайных чисел
    private static int[] generateRandomArray(int size) {
        if (size <= 0) {
            return new int[0];
        }

        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(201) - 100; // числа от -100 до 100
        }
        return array;
    }

    //метод для поиска минимального значения в массиве по модулю
    private static Integer findMinAbsValue(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        int minAbsValue = Math.abs(array[0]);
        for (int i = 1; i < array.length; i++) {
            int currentValue = Math.abs(array[i]);
            if (currentValue < minAbsValue) {
                minAbsValue = currentValue;
            }
        }
        return minAbsValue;
    }
}
