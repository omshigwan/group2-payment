package com.demo.services;

import org.springframework.http.ResponseEntity;

import com.demo.entities.PaymentDto;

public interface PaymentService {

	ResponseEntity<PaymentDto> processPayment(PaymentDto dto);
}
