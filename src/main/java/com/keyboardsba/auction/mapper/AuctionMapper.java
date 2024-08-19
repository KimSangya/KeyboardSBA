package com.keyboardsba.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.keyboardsba.auction.domain.Auction;

@Mapper
public interface AuctionMapper {
	public List<Auction> selectAuctionListByItemId(int itemId);
	
	public void insertPaid(
			@Param("itemId") int itemId,
			@Param("userId") int userId,
			@Param("loginId") String loginId,
			@Param("paid") int paid);
	
	public int selectPaidByItemId(int itemId);
}
