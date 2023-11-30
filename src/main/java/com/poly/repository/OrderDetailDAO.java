package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.CustomerReport;
import com.poly.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

	@Query(value = "SELECT new CustomerReport(o.order.account.username, o.order.account.email, o.order.account.phone, count(o), sum(o.product.price * o.quantity)) "
			+ "FROM OrderDetail o GROUP BY o.order.account.username, o.order.account.email, o.order.account.phone ORDER BY sum(o.product.price * o.quantity) DESC")
	List<CustomerReport> findAllSpentAccount();
}
