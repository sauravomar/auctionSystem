package com.auction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.auction.beans.Bid;
import com.auction.beans.Bidder;
import com.auction.beans.BiddingTypes;
import com.auction.exception.AuctionException;
import com.auction.util.AuctionUtil;

public class Auctioning {
	
	List<Bidder> bidders = new ArrayList<Bidder>();
	List<BiddingTypes>bidType = new ArrayList<BiddingTypes>();
	
	public static void main(String[] args) {
		
		Auctioning auctioning = new Auctioning();
		Scanner scanner = new Scanner(System.in);
		
		try {
			auctioning.bidders = AuctionUtil.inputBidder(scanner);
			auctioning.bidType = AuctionUtil.inputBidTypes(scanner);
			auctioning.auction(scanner);
			
		} catch (AuctionException e) {
			System.err.println(" Error : " + e.getMessage());
			e.printStackTrace();
		}
	} 
	
	public void auction(Scanner scanner){
		
		Bid bid = new Bid();
		long money  = 0;
		int position = 0;
		
		Bidder highBidder = null;
		Map<Long, Bidder>biddersMap = AuctionUtil.getBiddersMap(this.bidders);
		
		System.out.print(" Enter Starting money  -> ");
		money = scanner.nextLong();
		bid.setStartBidMoney(money);
		
		
		AuctionUtil.displayBidTypes(this.bidType);
		System.out.print(" Select Bid type ");
		position = scanner.nextInt();
		
		String choice = "continue";
		
		while(true){
			
			AuctionUtil.displayBidders(this.bidders);
			System.out.print(" Select Id  of the customer ");
			long id =  scanner.nextLong();
			
			System.out.print(" Enter money ");
			long currentMoney = scanner.nextLong();
			
			if(currentMoney > money){
				money = currentMoney;
				highBidder = biddersMap.get(id);
				highBidder.setMoney(money);
				bid.setCurrentMoney(money);
			}else{
				System.out.println(" Money is should be  more than higest bidder money");
			}
			choice = scanner.next();
			System.out.print(" Type exit to exit ");
			
			if(choice.equalsIgnoreCase("exit")){
				break;
			}else{
				continue;
			}
		}
		
		money = AuctionUtil.getBidMoneytoPay(this.bidders, position);
		System.out.println(highBidder.getUserName() + " Has to pay  " + money );
	}
	
}
