package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.PaymentDto;
import com.demo.services.PaymentServiceImpl;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired
	PaymentServiceImpl paymentService;

	@PostMapping
	public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto dto) {
		return paymentService.processPayment(dto);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<PaymentDto> getPaymentInfo(@PathVariable("id") long paymentId) {
		return paymentService.paymentInfoById(paymentId);
	}

	@GetMapping("/all")
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		return paymentService.allPaymentsInfo();
	}
}
