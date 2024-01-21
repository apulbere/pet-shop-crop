package com.apulbere.petshop.crop.search;

import com.apulbere.petshop.crop.domain.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {

//    @Mapping(source = "petType.code", target = "type")
    PetRecord map(Pet pet);
}
