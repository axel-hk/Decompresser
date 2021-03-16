package com.company;

public class TestCase {
    private static final int MINOFFSET = 47;
    private static final int MAXOFFSET = 100;
    public  String process(String input) {

        StringBuilder result = new StringBuilder();

        decompress(input, 0, result);

        return result.toString();
    }
/*Рекурсивная функция распаковки строки с параметрами: входная строка, индекс, результирующая строка,
  возвращает индекс стркои*/
    private int  decompress(String input, int offset, StringBuilder result) {

        StringBuilder rightPointer = new StringBuilder();
        StringBuilder current = new StringBuilder();

        while(offset < input.length()) {

            if(input.charAt(offset) == '[') {
                offset = decompress(input, offset+1, current);
                repeat(rightPointer, current);
                result.append(current);
                rightPointer.delete(0, rightPointer.length());
                current.delete(0, current.length());
            }
            else if(input.charAt(offset) == ']') {
                break;
            }
            else if(input.charAt(offset) > MINOFFSET &&
                    input.charAt(offset) < MAXOFFSET) {
                rightPointer.append(input.charAt(offset));
            }
            else {
                current.append(input.charAt(offset));
            }
            offset++;
        }
        result.append(current);
        return offset;
    }
//Функция для добавления букв в скобках
    private void repeat(StringBuilder rightPointer, StringBuilder input) {

        if(rightPointer.length() > 0) {
            int times = Integer.parseInt(rightPointer.toString());
            input.append(String.valueOf(String.valueOf(input)).repeat(Math.max(0, times - 1)));
        }
    }
}
