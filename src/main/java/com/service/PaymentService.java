package com.service;

import com.milind.model.Order;
import com.response.PaymentResponse;


public interface PaymentService {
    public PaymentResponse createPaymentLink(Order order);
 
} 
