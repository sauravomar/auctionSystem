package com.auction.util;

import java.util.Comparator;

import com.auction.beans.Bidder;

public class BidderSort implements Comparator<Bidder> {

	public int compare(Bidder o1, Bidder o2) {
		
		
		if(o1.getMoney() == null){
			return -1;
		}else if(o2.getMoney() == null){
			return 1;
		}
		
		if(o1.getMoney() > o2.getMoney() ){
			return 1;
		}else if(o1.getMoney() == o2.getMoney() ){
			return 0;
		}else{
			return -1;
		}
	}
}
