package com.rentsystemspring.controllers;
import com.google.gson.Gson;
import com.rentsystemspring.errors.OrderNotFound;
import com.rentsystemspring.errors.RentProviderNotFound;
import com.rentsystemspring.model.Orders;
import com.rentsystemspring.repository.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrdersController {
    @Autowired
    private OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = "/allOrders")
    public @ResponseBody Iterable<Orders> getAllOrders(){
        return orderRepository.findAll();
    }

    @GetMapping(value = "/orderById/{id}")
    public EntityModel<Orders> getOrderById(@PathVariable(name = "id") int id){
        Orders orders = orderRepository.findById(id).orElseThrow(() -> new OrderNotFound(id));
        return EntityModel.of(orders, linkTo(methodOn(OrdersController.class).getOrderById(id)).withSelfRel(),
                linkTo(methodOn(OrdersController.class).getAllOrders()).withRel("AllOrders"));
    }
    @PostMapping("/addOrder")
    Orders newOrder(@RequestBody Orders newOrder) {
        return orderRepository.save(newOrder);
    }
    @PutMapping(value = "/updateOrder/{id}")
    public @ResponseBody String updateOrder(@RequestBody String orderInfoToUpdate, @PathVariable int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(orderInfoToUpdate, Properties.class);
        Orders orders = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));
        orders.setOrderDate(LocalDate.parse(properties.getProperty("orderDate")));
        orderRepository.save(orders);
        return "Order updated";
    }


    @DeleteMapping(value = "/deleteOrder/{id}")
    public @ResponseBody String deleteOrder(@PathVariable(name = "id") int id){
        orderRepository.deleteById(id);
        return "Order Successfully deleted";
    }
}
