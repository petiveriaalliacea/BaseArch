package com.qakashilliacea.entity;

import com.qakashilliacea.entity.base.BaseEntity;
import com.qakashilliacea.util.constants.Constants;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Constants.DATABASE_PREFIX + "product")
public class Product extends BaseEntity<Long> {
}
