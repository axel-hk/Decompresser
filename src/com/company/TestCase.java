package com.company;

public class TestCase {
    private static final int MINOFFSET = 47;
    private static final int MAXOFFSET = 100;
    public  String process(String input) {

        StringBuilder result = new StringBuilder();

        decompress(input, 0, result);

        return result.toString();
    }
/*Рекурсивная функция распаковки строки с параметрами: входная строка, индекс строки, результирующая строка,
  возвращает индекс стркои*/
    private int  decompress(String input, int index, StringBuilder result) {

        StringBuilder rightPointer = new StringBuilder();
        StringBuilder current = new StringBuilder();

        while(index < input.length()) {

            if(input.charAt(index) == '[') {
                index = decompress(input, index +1, current);
                repeat(rightPointer, current);
                result.append(current);
                rightPointer.delete(0, rightPointer.length());
                current.delete(0, current.length());
            }
            else if(input.charAt(index) == ']') {
                break;
            }
            else if(input.charAt(index) > MINOFFSET &&
                    input.charAt(index) < MAXOFFSET) {
                rightPointer.append(input.charAt(index));
            }
            else {
                current.append(input.charAt(index));
            }
            index++;
        }
        result.append(current);
        return index;
    }
//Функция для добавления букв в скобках
    private void repeat(StringBuilder rightPointer, StringBuilder input) {

        if(rightPointer.length() > 0) {
            int times = Integer.parseInt(rightPointer.toString());
            input.append(String.valueOf(String.valueOf(input)).repeat(Math.max(0, times - 1)));
        }
    }
}
