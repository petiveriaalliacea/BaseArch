package com.qakashilliacea.entity.embeddable;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
/**
 * I don't exactly know do we actually need this
 */
public class GenericPeriod <T> {
    private T from;
    private T to;
}
