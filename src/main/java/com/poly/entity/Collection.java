package com.poly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection {
	@Id
	private String id;
	private String name;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date createDate = new Date();
	@JsonIgnore
	@OneToMany(mappedBy = "collection")
	private List<Product> products;
	@OneToMany(mappedBy = "collection")
	private List<Banner> banners;
}
