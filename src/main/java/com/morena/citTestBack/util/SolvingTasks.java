package com.morena.citTestBack.util;

import java.util.*;
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
     * @return sum|a[i] - b[i]| foreach i
     */
   private static long getCost(List<Long> l0, List<Long> l1) {
       if(l0.size() != l1.size())
           return -1;

       int cost = 0;
       for (int i = 0; i < l0.size(); i++)
               cost += Math.abs(l0.get(i) - l1.get(i));
       return cost;
   }

    /**
     * Solves square task
     * @param matrix 3 x 3
     * @return List<Long> where 0 element is cost and others - magic square
     */
    public static List<Long> solveSquare3X3 (List<Long> matrix) {
        return Arrays.stream(new MagicAndHalfMagicSquares().getSquares())
                .map(magicElem -> {
                    var elemAsList = new ArrayList<Long>();
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 3; j++)
                            elemAsList.add((long) magicElem[i][j]);
                    var costAndElem = new ArrayList<Long>();
                    costAndElem.add(getCost(matrix, elemAsList));
                    costAndElem.addAll(elemAsList);
                    return costAndElem;
                })
                .min(Comparator.comparing(list -> list.get(0)))
                .map(list -> (List<Long>) list)
                .orElse(Collections.emptyList());
    }
}
