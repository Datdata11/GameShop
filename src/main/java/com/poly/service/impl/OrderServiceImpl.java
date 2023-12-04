package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.entity.Account;
import com.poly.entity.CustomerReport;
import com.poly.entity.MailInfo;
import com.poly.entity.Order;
import com.poly.entity.OrderDetail;
import com.poly.entity.Product;
import com.poly.entity.ProductReport;
import com.poly.repository.AccountDAO;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.repository.ProductDAO;
import com.poly.service.MailerService;
import com.poly.service.OrderService;

import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;
	@Autowired
	OrderDetailDAO detailDao;
	@Autowired
	AccountDAO accDao;
	@Autowired
	ProductDAO productDao;
	@Autowired
	MailerService mailService;

	@Override
	public List<Order> findAll() {
		return dao.findAll();
	}

	@Override
	public Order update(Order order) {
		Order oldOrder = dao.findById(order.getId()).get();
		for (OrderDetail detail : oldOrder.getDetails()) {
			Boolean deleted = true;
			for (OrderDetail dt : order.getDetails()) {
				if (detail.getId() == dt.getId()) {
					dt.setOrder(order);
					detailDao.save(dt);
					deleted = false;
					break;
				}
			}
			if (deleted) {
				detailDao.delete(detail);
			}
		}
		accDao.save(order.getAccount());
		return dao.save(order);
	}

	@Override
	public void deleteById(Long id) {
		Order order = dao.findById(id).get();
		for (OrderDetail detail : order.getDetails()) {
			detailDao.delete(detail);
		}
		dao.deleteById(id);
	}

	@Override
	public Order findById(Long id) {
		return dao.findById(id).get();
	}

	@Override
	public List<CustomerReport> findSpentCustomers() {
		List<CustomerReport> list = detailDao.findAllSpentAccount();
		return list;
	}

	@Override
	public List<Order> findByAccount(Account logAcc) {
		return dao.findByAccount(logAcc, Sort.by(Direction.DESC, "id"));
	}

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		dao.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		List<OrderDetail> details = mapper.convertValue(orderData.get("details"), type)
				.stream().peek(detail -> detail.setOrder(order)).collect(Collectors.toList());
		detailDao.saveAll(details);
		
		try {
			MailInfo mail = new MailInfo();
			Account acc = accDao.findById(order.getAccount().getUsername()).get();
			mail.setTo(acc.getEmail());
			mail.setSubject("<Elise> Thông báo trạng thái đơn hàng");
			String str = "<div>\r\n"
					+ "        Thân gửi " + order.getAccount().getUsername() + ","
					+ "        Bạn vừa thực hiện đặt hàng tại Elise Shop. Thông tin chi tiết đơn hàng như sau:<br>"
					+ "        Mã đơn hàng: " + order.getId() +"<br>\r\n"
					+ "		   Ngày đặt: " + order.getOrderDate() +"<br>\r\n"
					+ "		   Trạng thái: " + order.getStatus() +"<br>\r\n"
					+ "		   Tổng tiền: " + order.getTotal() +"<br>\r\n"
					+ "		   Phương thức thanh toán: " + order.getPayment() +"<br>\r\n"
					+ "		   <table style=\"margin-top: 10px; text-align: center;\">\r\n"
					+ "				<tr style=\"background-color: green; color: white;\">\r\n"
					+ "					<th>Mã sản phẩm</th>\r\n"
					+ "					<th>Tên sản phẩm</th>\r\n"
					+ "					<th>Số lượng</th>\r\n"
					+ "					<th>Thành tiền</th>\r\n"
					+ "				</tr>\r\n";
			for (OrderDetail detail : details) {
				Product product = productDao.findById(detail.getProduct().getId()).get();
				str +=    "			<tr>\r\n"
						+ "				<td>" + product.getId() + "</td>\r\n"
						+ "				<td>" + product.getName() + "</td>\r\n"
						+ "				<td>" + detail.getQuantity() + "</td>\r\n"
						+ "				<td>" + (product.getPrice() * detail.getQuantity()) + " đ</td>\r\n"
						+ "			</tr>\r\n";
			}					
			str +=    "			</table>\r\n"
					+ "			<br><span>Tổng tiền đơn hàng đã tính cả phí giao hàng (nếu có)</span>\r\n"
					+ "     	<br><i>Lưu ý : Đây là email tự động vui lòng không phản hồi email này.</i>\r\n"
					+ "    </div>";
			mail.setBody(str);
			mailService.queue(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<ProductReport> findSoldProduct() {
		List<ProductReport> list = detailDao.findAllSoldProduct();
		return list;
	}

}
