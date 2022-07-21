package com.morena.citTestBack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum with codes of elements of substring task
 * Related dict entity - DictTaskSubstringElementTypes
 */
@RequiredArgsConstructor
public enum DictTaskSubstringElementTypesEnum {

    firstArrElem(1L),
    secondArrElem(2L);

    @Getter
    private final Long code;
}
