package com.tailorpro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailorpro.dto.BillDTO;
import com.tailorpro.entity.Bill;
import com.tailorpro.entity.Order;
import com.tailorpro.entity.PaymentStatus;
import com.tailorpro.repository.BillRepository;
import com.tailorpro.repository.OrderRepository;
@Service
public class BillService {
	@Autowired
	private BillRepository billRepository;

	@Autowired
	private OrderRepository orderRepository;

	public BillDTO generateBill(BillDTO dto) {

	    Order order = orderRepository.findById(dto.getOrderId())
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    Bill bill = new Bill();

	    bill.setTotalAmount(dto.getTotalAmount());
	    bill.setAdvancePaid(dto.getAdvancePaid());

	    double balance = dto.getTotalAmount() - dto.getAdvancePaid();
	    bill.setBalanceAmount(balance);

	    //  PAYMENT LOGIC
	    if (balance == 0) {
	        bill.setPaymentStatus(PaymentStatus.PAID);
	    } else if (dto.getAdvancePaid() > 0) {
	        bill.setPaymentStatus(PaymentStatus.PARTIAL);
	    } else {
	        bill.setPaymentStatus(PaymentStatus.PENDING);
	    }

	    bill.setOrder(order);

	    Bill saved = billRepository.save(bill);

	    return new BillDTO(
	            saved.getBillId(),
	            saved.getTotalAmount(),
	            saved.getAdvancePaid(),
	            saved.getBalanceAmount(),
	            saved.getPaymentStatus().name(),
	            order.getOrderId()
	    );
	    
	    
	}
	
	public BillDTO updatePayment(Long billId, Double amountPaid) {

	    // Validate input FIRST
	    if (amountPaid == null || amountPaid <= 0) {
	        throw new RuntimeException("Invalid payment amount");
	    }

	    Bill bill = billRepository.findById(billId)
	            .orElseThrow(() -> new RuntimeException("Bill not found"));

	    double newAdvance = bill.getAdvancePaid() + amountPaid;

	    // Validate against total AFTER calculation
	    if (newAdvance > bill.getTotalAmount()) {
	        throw new RuntimeException("Payment exceeds total amount");
	    }

	    bill.setAdvancePaid(newAdvance);

	    double balance = bill.getTotalAmount() - newAdvance;
	    bill.setBalanceAmount(balance);

	    // STATUS UPDATE
	    if (balance <= 0) {
	        bill.setPaymentStatus(PaymentStatus.PAID);
	        bill.setBalanceAmount(0.0);
	    } else {
	        bill.setPaymentStatus(PaymentStatus.PARTIAL);
	    }

	    Bill updated = billRepository.save(bill);

	    return new BillDTO(
	            updated.getBillId(),
	            updated.getTotalAmount(),
	            updated.getAdvancePaid(),
	            updated.getBalanceAmount(),
	            updated.getPaymentStatus().name(),
	            updated.getOrder().getOrderId()
	    );
	}
	
	public BillDTO getBillByOrder(Long orderId) {

	    Bill bill = billRepository.findByOrderOrderId(orderId)
	            .orElseThrow(() -> new RuntimeException("Bill not found"));

	    return new BillDTO(
	            bill.getBillId(),
	            bill.getTotalAmount(),
	            bill.getAdvancePaid(),
	            bill.getBalanceAmount(),
	            bill.getPaymentStatus().name(),
	            bill.getOrder().getOrderId()
	    );
	}
}
