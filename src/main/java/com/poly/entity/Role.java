package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable{
	@Id
	private String id;
	@Column(name = "role_name")
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Authority> authorities;
}
