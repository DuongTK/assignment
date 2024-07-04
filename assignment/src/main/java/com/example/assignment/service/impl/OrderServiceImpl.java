package com.example.assignment.service.impl;

import com.example.assignment.model.exception.BaseException;
import com.example.assignment.model.exception.ExceptionConstant;
import com.example.assignment.model.filter.OrderFilter;
import com.example.assignment.model.request.OrderDetailRQ;
import com.example.assignment.model.request.OrderRQ;
import com.example.assignment.model.response.OrderDetailRS;
import com.example.assignment.model.response.OrderRS;
import com.example.assignment.model.response.ProductRS;
import com.example.assignment.repo.dao.OrderDetailRepo;
import com.example.assignment.repo.dao.OrderRepo;
import com.example.assignment.repo.dao.ProductRepo;
import com.example.assignment.repo.entity.Order;
import com.example.assignment.repo.entity.OrderDetail;
import com.example.assignment.repo.entity.Product;
import com.example.assignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderDetailRepo orderDetailRepo;
    private final ModelMapper mapper;


    @Override
    public OrderRS createOrder(OrderRQ orderRQ) {
        Long nextOrderId = orderRepo.getNextSequenceValue();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailRQ orderDetailRQ : orderRQ.getOrderDetails()){
            Product product = productRepo.findById(orderDetailRQ.getId()).orElseThrow(() -> new BaseException(ExceptionConstant.PRODUCT_NOT_FOUND));
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(nextOrderId);
            orderDetail.setProductId(product.getId());
            orderDetail.setQuantity(orderDetailRQ.getQuantity());
            orderDetail.setPrice(product.getPrice());
            orderDetails.add(orderDetail);
        }
        Order order = new Order();
        order.setCustomerName(orderRQ.getCustomerName());
        order.setDeliveryAddress(orderRQ.getDeliveryAddress());
        order.setCustomerPhone(orderRQ.getCustomerPhone());
        order.setCustomerEmail(orderRQ.getCustomerEmail());
        orderDetailRepo.saveAll(orderDetails);
        orderRepo.save(order);
        List<OrderDetailRS> orderDetailRS = orderDetails.stream().map(o -> mapper.map(o,OrderDetailRS.class)).toList();
        OrderRS orderRS = mapper.map(order, OrderRS.class);
        orderRS.setOrderDetails(orderDetailRS);
        return orderRS;
    }

    @Override
    public OrderRS updateOrder(OrderRQ orderRQ) {
        Order order = orderRepo.findById(orderRQ.getId()).orElseThrow(() -> new BaseException(ExceptionConstant.ORDER_NOT_FOUND));
        order.setCustomerName(orderRQ.getCustomerName());
        order.setDeliveryAddress(orderRQ.getDeliveryAddress());
        order.setCustomerPhone(orderRQ.getCustomerPhone());
        order.setCustomerEmail(orderRQ.getCustomerEmail());
        orderRepo.save(order);
        OrderRS orderRS = mapper.map(order, OrderRS.class);
        return orderRS;
    }

    @Override
    public OrderRS getById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new BaseException(ExceptionConstant.ORDER_NOT_FOUND));
        List<OrderDetail> orderDetails = orderDetailRepo.findByOrderId(orderId);

        List<OrderDetailRS> orderDetailRS = orderDetails.stream().map(o -> mapper.map(o,OrderDetailRS.class)).toList();
        OrderRS orderRS = mapper.map(order, OrderRS.class);
        orderRS.setOrderDetails(orderDetailRS);
        return orderRS;
    }

    @Override
    public void deleteById(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new BaseException(ExceptionConstant.ORDER_NOT_FOUND));
        List<OrderDetail> orderDetails = orderDetailRepo.findByOrderId(orderId);
        orderDetailRepo.deleteAll(orderDetails);
        orderRepo.delete(order);
    }

    @Override
    public Page<OrderRS> search(OrderFilter filter) {
        Page<Order> orders = orderRepo.search(filter.getId(),filter.getKeywork(), PageRequest.of(filter.getPage(), filter.getLimit()));
        List<OrderRS> orderRS = orders.stream().map(p -> mapper.map(p, OrderRS.class)).toList();
        return new PageImpl<>(orderRS);
    }
}
