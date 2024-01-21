package com.apulbere.petshop.crop.search;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PetRecord(
    Long id,
    LocalDate birthdate,
    String type,
    String name,
    BigDecimal price,
    boolean active
) {

}
