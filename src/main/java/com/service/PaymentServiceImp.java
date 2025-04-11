
package com.service;

import com.milind.model.Order;
import com.response.PaymentResponse;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService {

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    @Override
    public PaymentResponse createPaymentLink(Order order) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject options = new JSONObject();
            System.out.println("Creating Razorpay order with total: ₹" + order.getTotalPrice());
            double total = order.getTotalPrice();
            System.out.println("✅ Sending total to Razorpay: ₹" + total);
            if (total <1) {
                throw new RuntimeException("Invalid total price for Razorpay: " + total);
            }


            options.put("amount", (int) (order.getTotalPrice() * 100)); // amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + order.getId());
            options.put("payment_capture", 1);

            com.razorpay.Order razorpayOrder = razorpayClient.Orders.create(options);

            // Now return the Razorpay order ID to frontend
            PaymentResponse res = new PaymentResponse();
            res.setPayment_url(razorpayOrder.get("id")); // Send order_id to frontend
            return res;

        } catch (RazorpayException e) {
            throw new RuntimeException("Razorpay order creation failed", e);
        }
    }
}
