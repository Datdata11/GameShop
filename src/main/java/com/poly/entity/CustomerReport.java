package com.poly.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CustomerReport {
	@Id
	Serializable username;
	String email;
	String phone;
	Long count; // tổng lượt mua
	Double total; // tổng số tiền đã tiêu
}
