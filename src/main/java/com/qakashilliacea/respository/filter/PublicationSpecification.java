package com.qakashilliacea.respository.filter;

import com.qakashilliacea.entity.Publication;
import org.springframework.data.jpa.domain.Specification;

public class PublicationSpecification {
    public static Specification<Publication> getAllPageableByDate() {
        return ((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("createdAt")));
            return null;
        });
    }

    public static Specification<Publication> getAllPageableByPublicationType(Long typeId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("typeId"), typeId));
    }

    public static Specification<Publication> getAllPageableByUserId(Long userId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("userId"), userId));
    }
}
