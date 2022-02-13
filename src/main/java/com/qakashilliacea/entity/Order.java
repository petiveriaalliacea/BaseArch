package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.AuditingEntity;
import com.qakashilliacea.entity.enums.OrderStatus;
import com.qakashilliacea.util.constants.Constants;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order extends AuditingEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
