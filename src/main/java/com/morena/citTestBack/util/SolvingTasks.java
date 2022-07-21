package com.morena.citTestBack.util;

import java.util.List;
import java.util.stream.Collectors;

public class SolvingTasks {

    /**
     * Solves string task
     * @param arr1 substrings
     * @param arr2 strings
     * @return result array of strings of arr1 which a substrings of arr2
     */
    public static List<String> solveSubstring (List<String> arr1, List<String> arr2) {
        return arr1.stream()
                .distinct()
                .filter(str -> {
                    for (String arr2Elem : arr2)
                        if (arr2Elem.contains(str))
                            return true;
                    return false;
                })
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Solves square task
     * @param matrix 3 x 3
     * @return List<List<Integer>> magic square
     */
    public static List<List<Integer>> solveSquare (List<List<Integer>> matrix) {
        return null;
    }
}
