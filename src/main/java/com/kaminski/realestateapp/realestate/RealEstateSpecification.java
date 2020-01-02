package com.kaminski.realestateapp.realestate;

import com.kaminski.realestateapp.address.Address;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RealEstateSpecification implements Specification<RealEstate> {

    private ParameterSearch criteria;

    public RealEstateSpecification(ParameterSearch params){
        criteria = params;
    }

    @Override
    public Predicate toPredicate(Root<RealEstate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {

        Path<Long> id = root.get(RealEstate_.id);
        Path<Long> area = root.get(RealEstate_.area);
        Path<Long> price = root.get(RealEstate_.price);
        Path<Long> pricePerSquareMeter = root.get(RealEstate_.pricePerSquareMeter);
        Path<RealEstateType> realEstateType = root.get(RealEstate_.realEstateType);
        Path<Address> address = root.get(RealEstate_.address);
        Path<String> city = address.get("city");
        Path<String> district = address.get("district");

        final List<Predicate> predicates = new ArrayList<>();
        if(criteria.getCity()!=null) {
            predicates.add(cb.equal(city, criteria.getCity()));
        }
        if(criteria.getDistrict()!=null) {
            predicates.add(cb.equal(district, criteria.getDistrict()));
        }
        if(criteria.getMaxArea()!=null || criteria.getMinArea()!=null) {
            long lowerBound = criteria.getMinArea()!=null ? criteria.getMinArea() : 0;
            long upperBound = criteria.getMaxArea()!=null ? criteria.getMaxArea() : Long.MAX_VALUE;
            predicates.add(cb.between(area, lowerBound, upperBound));
        }
        if(criteria.getMaxPrice()!=null || criteria.getMinPrice()!=null) {
            long lowerBound = criteria.getMinPrice()!=null ? criteria.getMinPrice() : 0;
            long upperBound = criteria.getMaxPrice()!=null ? criteria.getMaxPrice() : Long.MAX_VALUE;
            predicates.add(cb.between(price, lowerBound, upperBound));
        }
        if(criteria.getMaxPricePerM()!=null || criteria.getMinPricePerM()!=null) {
            long lowerBound = criteria.getMinPricePerM()!=null ? criteria.getMinPricePerM() : 0;
            long upperBound = criteria.getMaxPricePerM()!=null ? criteria.getMaxPricePerM() : Long.MAX_VALUE;
            predicates.add(cb.between(pricePerSquareMeter, lowerBound, upperBound));
        }
        if(criteria.getRealEstateType()!=null ) {
            predicates.add(cb.equal(realEstateType, RealEstateType.valueOf(criteria.getRealEstateType())));
        }
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));

    }

}
