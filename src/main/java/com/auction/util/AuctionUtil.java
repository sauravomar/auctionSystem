package com.auction.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.auction.beans.Bidder;
import com.auction.beans.BiddingTypes;
import com.auction.exception.AuctionException;

public class AuctionUtil {
	
	public static Bidder createBidder(long id, String name, String type){
		Bidder bidder = new Bidder();
		bidder.setId(id);
		bidder.setUserName(name);
		bidder.setType(type);
		return bidder;
	}
	
	public static List<Bidder> inputBidder(Scanner scanner) throws AuctionException{
		
		System.out.print(" Enter the no of Bidder - >  ");
		int totalBidder  = scanner.nextInt();
		System.out.println();
		
		if(totalBidder == 0){
			System.out.println(" Not a valid Bidders");
			throw new AuctionException("Not a valid Bidders");
		}
		
		List<Bidder>bidders = new ArrayList<Bidder>();
		String name = null;
		String type = null;
		
		for(int i=0;i<totalBidder;i++){
			System.out.println("Enter Bidder Info ");
			while(true){
				System.out.print("Enter name -> ");
				name = scanner.next();
				if(name != null) break;
				
			}
			
			while(true){
				System.out.print("Enter type -> ");
				type = scanner.next();
				if(type != null) break;
				
			}
			bidders.add(createBidder(i+1, name, type));
		}
		return bidders;
	}
	
	public static List<BiddingTypes> inputBidTypes(Scanner scanner) throws AuctionException{
		List<BiddingTypes> bidTypes = new ArrayList<BiddingTypes>();
		
		System.out.print(" Enter the no of Bid type - >  ");
		int totalBidType  = scanner.nextInt();
		int bidPosition;
		
		if(totalBidType == 0){
			System.out.println(" Not a valid Bid type");
			throw new AuctionException("Not a valid Bid type");
		}
		
		for(int i=0;i<totalBidType;i++){
			BiddingTypes type = new BiddingTypes();
			
			while(true){
				System.out.print("Enter position of Bid money winner has to pay -> ");
				bidPosition = scanner.nextInt();
				if(bidPosition != 0) break;
			}
			type.setPosition(bidPosition);
			bidTypes.add(type);
		}
		return bidTypes;
	}
	
	public static void displayBidders(List<Bidder> bidders){
		for(Bidder bidder:  bidders){
			if(!bidder.getType().equalsIgnoreCase("admin")){
				System.out.println("id -> "+ bidder.getId() + " name -> " + bidder.getUserName());
			}
		}
	}
	
	public static void displayBidTypes(List<BiddingTypes> bidTypes){
		for(BiddingTypes bidder:  bidTypes){
			System.out.println(" Position -> " + bidder.getPosition());
		}
	}
	
	public static Map<Long,Bidder> getBiddersMap(List<Bidder> bidders){
		
		Map<Long, Bidder> bidderMap =  new HashMap<Long, Bidder>();
		
		for(Bidder bidder:  bidders){
			bidderMap.put(bidder.id, bidder);
		}
		return bidderMap;
	}
	
	public static long getBidMoneytoPay(List<Bidder> bidders, int position){
		Collections.sort(bidders, new BidderSort());
		return bidders.get(position).getMoney();
	}
	
}
