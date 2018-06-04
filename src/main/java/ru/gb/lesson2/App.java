/*
 * @author  Pavel Dymov
 * @github  https://github.com/pvd7/pdymov-javacore-advanced.git
 */

package ru.gb.lesson2;

import org.apache.commons.lang3.StringUtils;
import ru.gb.lesson2.exception.MyArrayDataException;
import ru.gb.lesson2.exception.MyArraySizeException;

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
