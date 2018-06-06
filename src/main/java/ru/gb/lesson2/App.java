/*
 * @author  Pavel Dymov
 * @github  https://github.com/pvd7/pdymov-javacore-advanced.git
 */

package ru.gb.lesson2;

import org.apache.commons.lang3.StringUtils;
import ru.gb.lesson2.exception.MyArrayDataException;
import ru.gb.lesson2.exception.MyArraySizeException;

/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4,
 * при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
 * или текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией,
 * в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения
 * MySizeArrayException и MyArrayDataException и вывести результат расчета.
 */
public class App {
    private static final int MAX_LENGTH = 4;

    private static void arrTest(String[][] args) throws MyArraySizeException, MyArrayDataException {
        if (!((args.length == MAX_LENGTH) & (args[0].length == MAX_LENGTH))) throw new MyArraySizeException(String.format("массив не соответствует размеру [%1$d][%1$d]", MAX_LENGTH));
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length; j++) {
                if (!(StringUtils.isNumericSpace(args[i][j]))) throw new MyArrayDataException("в ячейке [" + i + "][" + j +"] не int");
            }
        }
    }

    public static void main(String[] args) {
        String[][] arr = {
                {"2", "1", "", ""}
                , {"1", "1", "", ""}
                , {"1", "1", "4 5", "4f4"}
                , {"3", "1", "", ""}
//                , {"qwe", "1", "", ""}
                };
        try {
            arrTest(arr);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }
    }
}
