package com.morena.citTestBack.util;

import lombok.Getter;

public class ConstantUtil {

    /**
     * Param M of MxM matrix from task of square
     */
    @Getter
    private static final int matrixSize = 3;

    /**
     * Size of array holding matrix MxM
     */
    @Getter
    private static final int lineMatrixSize = matrixSize * matrixSize;

    /**
     * Normal magic squares, order 3
     */
    @Getter
    private static final int[][][] magicSquares3X3 = {
            {{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},

            {{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},

            {{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},

            {{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},

            {{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},

            {{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},

            {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}},

            {{6, 7, 2}, {1, 5, 9}, {8, 3, 4}}};
}
