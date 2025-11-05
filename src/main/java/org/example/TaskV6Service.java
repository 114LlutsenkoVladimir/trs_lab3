package org.example;

import java.util.Arrays;

public class TaskV6Service {

    public String getAnswer(String request) {
        try {
            int[] array = getArray(request);
            int max = getMax(array);
            return ("max: " + max);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    private int[] getArray(String request) {
        try {
            int[] res = Arrays.stream(request.split(" "))
                    .filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt)
                    .toArray();
            if (res.length == 0)
                throw new RuntimeException("Порожній масив!");
            return res;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Некоректний ввід");
        }
    }

    private int getMax(int[] array) {
        return Arrays.stream(array).max().orElseThrow(
                () -> new RuntimeException("Помилка при знаходженні максимуму")
        );
    }
}
