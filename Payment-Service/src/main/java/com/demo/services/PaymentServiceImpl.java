package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.entities.Payment;
import com.demo.entities.PaymentDto;
import com.demo.exceptions.PaymentNotFoundException;
import com.demo.repositories.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepo;

	@Autowired
	ModelMapper mapper;

	// method to save payment information
	@Override
	public ResponseEntity<PaymentDto> processPayment(PaymentDto dto) {
		Payment payment = mapToPayment(dto);
		String transactionId = generateTransactionId();
		payment.setTransactionId(transactionId);
		Payment savePayment = paymentRepo.save(payment);
		PaymentDto responsedDto = mapToDto(savePayment);
		return new ResponseEntity<>(responsedDto, HttpStatus.CREATED);
	}

	// method to create custom transaction id
	private String generateTransactionId() {
		return "TXI" + System.currentTimeMillis();
	}

	// method to retrieve payment by id
	public ResponseEntity<PaymentDto> paymentInfoById(long id) {
		Payment existingPayment = paymentRepo.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment with Id " + id + " not found"));
		PaymentDto responseDto = mapToDto(existingPayment);
		return new ResponseEntity<PaymentDto>(responseDto, HttpStatus.OK);
	}

	// method to retrieve all the payments from db
	public ResponseEntity<List<PaymentDto>> allPaymentsInfo() {
		List<Payment> payments = paymentRepo.findAll();
		List<PaymentDto> responseList = payments.stream().map(payment -> mapToDto(payment)).toList();
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}

	// mapping methods
	public Payment mapToPayment(PaymentDto dto) {
		Payment payment = mapper.map(dto, Payment.class);
		return payment;
	}

	public PaymentDto mapToDto(Payment payment) {
		PaymentDto dto = mapper.map(payment, PaymentDto.class);
		return dto;
	}

}
