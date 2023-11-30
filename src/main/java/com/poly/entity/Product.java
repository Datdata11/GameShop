package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
	@Id
	private String id;
	private String name;
	private Double price;
	private String type;
	@ManyToOne
	@JoinColumn(name = "collection_id")
	private Collection collection;
	private Float length;
	private String material;
	private String line;
	private String form;
	@Column(name = "total_sold")
	private Integer totalSold;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	@OneToMany(mappedBy = "product")
	private List<Image> images;

}
