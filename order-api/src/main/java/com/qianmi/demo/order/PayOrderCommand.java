package com.qianmi.demo.order;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by caozupeng on 17/7/12.
 */
@Value
public class PayOrderCommand {
    @TargetAggregateIdentifier
    private String orderId;
}
