package com.qakashilliacea.respository.filter;

import com.qakashilliacea.entity.Publication;
import org.springframework.data.jpa.domain.Specification;

public class PublicationSpecification {
    public static Specification<Publication> sortByDate() {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("createdDate")));
            return null;
        });
    }

    public static Specification<Publication> typeIdEquals(Long typeId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("typeId"), typeId));
    }

    public static Specification<Publication> authorIdEquals(Long userId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId));
    }
}
