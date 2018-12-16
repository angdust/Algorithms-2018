package lesson6;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * Трудоемкость алгоритма: О(N^2), где N - кол-во эллементов.
     * Ресурсоемкость алгоритма: O(2*N).
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.size() == 0) return new ArrayList<>();
        int quantity = list.size();
        int[] prev = new int[quantity];
        int[] length = new int[quantity];
        Arrays.fill(prev, -1);
        Arrays.fill(length, 1);

        for (int i = 0; i < quantity; i++) {
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int position = 0;
        int lengthOfSequence = length[0];
        for (int i = 0; i < quantity; i++) {
            if (length[i] > lengthOfSequence) {
                position = i;
                lengthOfSequence = length[i];
            }
        }
        List<Integer> result = new ArrayList<>();
        while (position != -1) {
            result.add(list.get(position));
            position = prev[position];
        }
        for (int i = 0; i < Math.floor(result.size() / 2); i++) {
            int x = result.get(i);
            result.set(i, result.get(result.size() - i - 1));
            result.set(result.size() - i - 1, x);
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     * <p>
     * Трудоемкость алгоритма: О(N*M), где N - кол-во строк, M - кол-во столбцов.
     * Ресурсоемкость алгоритма: O(N*M).
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        ArrayList<ArrayList<Integer>> field = new ArrayList<ArrayList<Integer>>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputName)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] aLine = line.split(" ");
            ArrayList<Integer> lList = new ArrayList<Integer>();
            for (int i = 0; i < aLine.length; i++) {
                lList.add(Integer.parseInt(aLine[i]));
            }
            field.add(lList);
        }
        for (int i = 0; i < field.size(); i++) {
            for (int j = 0; j < field.get(0).size(); j++) {
                int x = field.get(i).get(j) + getMinBeside(i, j, field);
                field.get(i).set(j, x);
            }
        }
        return field.get(field.size() - 1).get(field.get(0).size() - 1);
    }

    private static int getMinBeside(int i, int j, ArrayList<ArrayList<Integer>> field) {
        if (i == 0 && j == 0) return 0;
        if (i == 0) return field.get(i).get(j - 1);
        if (j == 0) return field.get(i - 1).get(j);
        int x = field.get(i).get(j - 1);
        if (x > field.get(i - 1).get(j)) x = field.get(i - 1).get(j);
        if (x > field.get(i - 1).get(j - 1)) x = field.get(i - 1).get(j - 1);
        return x;
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
