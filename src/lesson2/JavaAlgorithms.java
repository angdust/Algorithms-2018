package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    private static Set<String> result = new HashSet<>();

    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     *
     * Трудоемкость алгоритма: О(N*M), N, M - длины слов.
     * Ресурсоемкость алгоритма: O(N*M).
     */
    static public String longestCommonSubstring(String first, String second) {
        int[][] table = new int[first.length() + 1][second.length() + 1];
        //  Arrays.fill(table, 0);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = 0;
            }
        }
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                }
            }
        }
        int maxLength = 0;
        int indexX = 0;
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (maxLength < table[i][j]) {
                    maxLength = table[i][j];
                    indexX = i;
                }
            }
        }
        String result = "";
        if (indexX != 0) {
            result = first.substring(indexX - maxLength, indexX);
        }
        return result;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     * Трудоемкость алгоритма: О(N*M + K), N*M - матрица букв; K - количество слов.
     * Ресурсоемкость алгоритма: O(N*M + K).
     * В не самом хорошем варианте.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws Exception {
        List<String[]> linesOfMatrix = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputName)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            linesOfMatrix.add(line.split(" "));
        }
        String[][] table = new String[linesOfMatrix.size()][linesOfMatrix.get(0).length];
        Boolean[][] visitedLetters = new Boolean[linesOfMatrix.size()][linesOfMatrix.get(0).length];
        for (int i = 0; i < linesOfMatrix.size(); i++) {
            if (linesOfMatrix.get(0).length >= 0)
                System.arraycopy(linesOfMatrix.get(i), 0, table[i], 0, linesOfMatrix.get(0).length);
        }
        for (int i = 0; i < linesOfMatrix.size(); i++) {
            for (int j = 0; j < linesOfMatrix.get(0).length; j++) {
                visitedLetters[i][j] = false;
            }
        }
        String string = "";
        for (int i = 0; i < linesOfMatrix.size(); i++) {
            for (int j = 0; j < linesOfMatrix.get(0).length; j++) {
                findWords(table, visitedLetters, i, j, string, words);
            }
        }
        return result;
    }

    static private boolean isWord(String string, Set<String> words) {
        for (String word : words) {
            if (string.equals(word)) {
                return true;
            }
        }
        return false;
    }

    static private void findWords(String[][] tableOfLetters, Boolean[][] tableOfVisitedLetters,
                                  int column, int row, String string, Set<String> words) {
        tableOfVisitedLetters[column][row] = true;
        string = string + tableOfLetters[column][row];
        if (isWord(string, words)) {
            result.add(string);
            return;
        }
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if ((column - 1) >= 0 && !tableOfVisitedLetters[column - 1][row]) {
                        findWords(tableOfLetters, tableOfVisitedLetters, column - 1, row, string, words);
                    }
                    break;
                case 1:
                    if ((row - 1) >= 0 && !tableOfVisitedLetters[column][row - 1]) {
                        findWords(tableOfLetters, tableOfVisitedLetters, column, row - 1, string, words);
                    }
                    break;
                case 2:
                    if ((row + 1) < tableOfLetters[0].length && !tableOfVisitedLetters[column][row + 1]) {
                        findWords(tableOfLetters, tableOfVisitedLetters, column, row + 1, string, words);
                    }
                    break;
                case 3:
                    if ((column + 1) < tableOfLetters.length && !tableOfVisitedLetters[column + 1][row]) {
                        findWords(tableOfLetters, tableOfVisitedLetters, column + 1, row, string, words);
                    }
                    break;
            }
        }
        string = string.substring(0, string.length() - 1);
        tableOfVisitedLetters[column][row] = false;
    }
}
