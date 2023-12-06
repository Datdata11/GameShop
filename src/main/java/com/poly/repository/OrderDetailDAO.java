package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.CustomerReport;
import com.poly.entity.OrderDetail;
import com.poly.entity.ProductReport;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

	@Query(value = "SELECT new CustomerReport(o.order.account.username, o.order.account.email, o.order.account.phone, count(o), sum(o.product.price * o.quantity), month(o.order.orderDate), year(o.order.orderDate)) "
			+ "FROM OrderDetail o WHERE o.order.status = 'completed' GROUP BY o.order.account.username, o.order.account.email, o.order.account.phone, month(o.order.orderDate), year(o.order.orderDate) ORDER BY sum(o.product.price * o.quantity) DESC")
	List<CustomerReport> findAllSpentAccount();

	@Query(value = "SELECT new ProductReport(o.product.id, o.product.name, o.product.price, sum(o.quantity), month(o.order.orderDate), year(o.order.orderDate)) "
			+ "FROM OrderDetail o WHERE o.order.status = 'completed' GROUP BY o.product.id, o.product.name, o.product.price, month(o.order.orderDate), year(o.order.orderDate) ORDER BY sum(o.quantity)")
	List<ProductReport> findAllSoldProduct();
}
