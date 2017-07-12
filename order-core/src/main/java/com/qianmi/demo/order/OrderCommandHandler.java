package com.qianmi.demo.order;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;

import java.util.concurrent.Callable;

/**
 * Created by caozupeng on 17/7/12.
 */
public class OrderCommandHandler {
    private Repository<Order> repository;
    private EventBus eventBus;

    public OrderCommandHandler(Repository<Order> repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(PayOrderCommand command) {
        Aggregate<Order> orderAggregate = repository.load(command.getOrderId());
        orderAggregate.execute(order -> order.payed(command));
    }

    @CommandHandler
    public void handle(OpenOrderCommand command) {
        try {
            repository.newInstance(new Callable<Order>() {
                @Override
                public Order call() throws Exception {
                    return new Order(command);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
