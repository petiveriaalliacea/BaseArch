package com.qakashilliacea.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private Double latitude = 0D;
    private Double longitude = 0D;
}
