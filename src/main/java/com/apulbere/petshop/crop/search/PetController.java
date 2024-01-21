package com.apulbere.petshop.crop.search;

import com.apulbere.crop.CriteriaOperatorOrder;
import com.apulbere.crop.CriteriaOperatorPage;
import com.apulbere.crop.service.CriteriaOperatorService;
import com.apulbere.petshop.crop.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PetController {

    private final PetMapper petMapper;
    private final CriteriaOperatorService cropService;

    @GetMapping("/pets")
    List<PetRecord> findAll(PetCriteriaOperator searchCriteria, CriteriaOperatorOrder order, CriteriaOperatorPage page) {
        return cropService.create(Pet.class, searchCriteria, order, page)
            .match(Pet_.id, PetCriteriaOperator::getId)
            .match(Pet_.name, PetCriteriaOperator::getNickname)
            .match(Pet_.birthdate, PetCriteriaOperator::getBirthdate)
            .match(Pet_.price, PetCriteriaOperator::getPrice)
            .join(Pet_.features)
                .match(PetFeature_.feature, PetCriteriaOperator::getFeatures)
            .endJoin()
            .match(Pet_.active, PetCriteriaOperator::getActive)
            .join(Pet_.petType)
                .match(PetType_.code, PetCriteriaOperator::getType)
                .join(PetType_.petCategory)
                    .match(PetCategory_.code, PetCriteriaOperator::getCategory)
                .endJoin()
            .endJoin()
            .getResultList()
            .stream()
            .map(petMapper::map)
            .toList();
    }

}
