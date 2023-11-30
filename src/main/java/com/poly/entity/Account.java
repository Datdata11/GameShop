package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "account", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable{
	@Id
	private String username;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	private String email;
	private Boolean role = false;
	@Transient
	private Boolean remember = true;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
}
