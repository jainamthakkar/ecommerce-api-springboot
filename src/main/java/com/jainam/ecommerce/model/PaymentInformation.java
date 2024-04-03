package com.jainam.ecommerce.model;

import jakarta.persistence.Column;

public class PaymentInformation {
	
	@Column(name="cardholder_name")
	private String cardholderName;
	
	@Column(name="cardholder_number")
	private String cardholderNumber;
	
	@Column(name="expiration_date")
	private String expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
	public PaymentInformation() {
		// TODO Auto-generated constructor stub
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardholderNumber() {
		return cardholderNumber;
	}

	public void setCardholderNumber(String cardholderNumber) {
		this.cardholderNumber = cardholderNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public PaymentInformation(String cardholderName, String cardholderNumber, String expirationDate, String cvv) {
		super();
		this.cardholderName = cardholderName;
		this.cardholderNumber = cardholderNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	

}
