package com.morena.citTestBack.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DictTaskSubstringElementTypesEnum {

    firstArrElem(1L),
    secondArrElem(2L),
    resultArrElem(3L);

    @Getter
    private final Long code;
}
