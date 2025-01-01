package dev.azhar.paymentservicedec24.controllers;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import dev.azhar.paymentservicedec24.dtos.GeneratePaymentLinkRequestDto;
import dev.azhar.paymentservicedec24.services.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(@Qualifier("razorpay") PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/payment")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws RazorpayException, StripeException {

        // return the payment link
        return paymentService.generatePaymentLink(generatePaymentLinkRequestDto.orderId());
    }
}
