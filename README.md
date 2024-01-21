# Demo project for [CriteriaOperator](https://github.com/apulbere/crop)

CriteriaOperator is a lightweight, zero dependency, and type-safe wrapper around Java Persistence Criteria API, which simplifies building queries, particularly useful within REST SQL (RSQL) context.

```java
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
```
