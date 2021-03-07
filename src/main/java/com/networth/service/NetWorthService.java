package com.networth.service;

import com.networth.entity.User;
import com.networth.model.NetWorth;
import com.networth.pojo.AssetDebtRequest;
import com.networth.pojo.LastYearDetails;

public interface NetWorthService {

	String addAsset(AssetDebtRequest ad, String id);

	NetWorth getNetWorth(String id);

	NetWorth lastYearDetails(LastYearDetails lastYearDetails);

}
