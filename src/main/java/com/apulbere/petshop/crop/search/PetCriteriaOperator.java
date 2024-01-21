package com.apulbere.petshop.crop.search;

import com.apulbere.crop.operator.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetCriteriaOperator {

    private LongCriteriaOperator id;
    private StringCriteriaOperator nickname;
    private StringCriteriaOperator type;
    private StringCriteriaOperator category;
    private LocalDateCriteriaOperator birthdate;
    private BigDecimalCriteriaOperator price;
    private BooleanCriteriaOperator active;
    private StringCriteriaOperator features;
}
