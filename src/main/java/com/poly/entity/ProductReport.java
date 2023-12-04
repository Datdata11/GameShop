package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductReport implements Serializable {
	@Id
	private String id;
	private String name;
	private Double price;
	private Long totalQty;
	private Integer month;
	private Integer year;
}
