package com.auction.exception;

public class AuctionException extends Exception {
	
	private String reason;
	
	public AuctionException(String string) {
		super(string);
	}
	
	public AuctionException(String string,String reason) {
		super(string);
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
