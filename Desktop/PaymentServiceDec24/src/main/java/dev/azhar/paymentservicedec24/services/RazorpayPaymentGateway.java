package dev.azhar.paymentservicedec24.services;


import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorpayPaymentGateway implements PaymentService{


     private final RazorpayClient razorpayClient;

     public RazorpayPaymentGateway(RazorpayClient razorpayClient) {
          this.razorpayClient = razorpayClient;
     }

     @Override
   public String generatePaymentLink(Long orderId) throws RazorpayException {
        //will I need to make a call to order details?
        // this order id is correct or note
        //  we will learn this authentication module after machine code is done
        // so, through rest template we can make a call to order service

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);  /// Rs = 10, bcoz any gateway take 2 digit number
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by", System.currentTimeMillis() + 10*60*1000); // epoch -> it's a timestamp
        paymentLinkRequest.put("reference_id",orderId.toString());
        paymentLinkRequest.put("description","Payment for EXIted batch dec24");
        JSONObject customer = new JSONObject();
        customer.put("name","Azhar Beg");
        customer.put("contact","+91 9058582425");
        customer.put("email","azharsubhan10@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://google.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }


}
