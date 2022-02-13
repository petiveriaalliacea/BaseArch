package com.qakashilliacea.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class IntegerPeriod {
    private Integer from = 0;
    private Integer to = 0;
}
