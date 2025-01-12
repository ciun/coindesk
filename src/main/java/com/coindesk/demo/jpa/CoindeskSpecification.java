package com.coindesk.demo.jpa;

import com.coindesk.demo.dto.Coindesk;
import com.coindesk.demo.dto.CoindeskDetail;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CoindeskSpecification implements Specification<CoindeskDetail> {

    private List<SearchCriteria> list;

    public CoindeskSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }


    @Override
    public Predicate toPredicate(Root<CoindeskDetail> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equalsIgnoreCase("<")) {
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (criteria.getKey().equals("code")) {
                    Join<CoindeskDetail, Coindesk> join = root.join("ccy_code");
                    predicates.add(builder.equal(join.get("code"), criteria.getValue()));
                } else {
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                }
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
