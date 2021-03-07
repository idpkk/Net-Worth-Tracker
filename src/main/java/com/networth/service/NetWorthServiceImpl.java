package com.networth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.networth.model.AssetDebt;
import com.networth.model.NetWorth;
import com.networth.pojo.AssetDebtRequest;
import com.networth.pojo.LastYearDetails;
import com.networth.repo.NetWorthRepo;

@Service
public class NetWorthServiceImpl implements NetWorthService {

	@Autowired
	NetWorthRepo repo;

	@Override
	public String addAsset(AssetDebtRequest adr, String id) {
		List<NetWorth> nws = repo.findAll().stream().filter(data -> id.equals(data.getId())).collect(Collectors.toList());
		NetWorth nw = nws.get(0);
		AssetDebt ad = new AssetDebt();
		ad.setName(adr.getName());
		ad.setCurrentAssetValue(adr.getCurrentValue());
		ad.setPriorAssetValue(adr.getPriorValue());
		ad.setCurrentDebtBalance(adr.getCurrentBalance());
		ad.setPriorDebtBalance(adr.getPriorBalance());
		if (nw == null)
			nw = new NetWorth();
		List<AssetDebt> ads = nw.getAssetDebts() == null ? new ArrayList<>() : nw.getAssetDebts();
		// added Asset to existing one
		ads.add(ad);
		nw.setAssetDebts(ads);
		nw.setTotalCurrentAsset(
				nw.getTotalCurrentAsset() == null ? 0d : nw.getTotalCurrentAsset() + adr.getCurrentValue());
		nw.setTotalPriorAsset(nw.getTotalPriorAsset() != null ? nw.getTotalPriorAsset() : 0d + adr.getPriorValue());
		nw.setAssetYtdChange((nw.getTotalCurrentAsset() != null ? nw.getTotalCurrentAsset() : 0d)
				- (nw.getLastYearTotalAssets() != null ? nw.getLastYearTotalAssets() : 0d));
		nw.setTotalCurrentDebt(
				nw.getTotalCurrentDebt() != null ? nw.getTotalCurrentDebt() : 0d + adr.getCurrentBalance());
		nw.setTotalPriorDebt(nw.getTotalPriorAsset() != null ? nw.getTotalPriorAsset() : 0d + adr.getPriorBalance());
		nw.setDebtPaidOffYTD((nw.getTotalCurrentDebt() != null ? nw.getTotalCurrentDebt() : 0d)
				- (nw.getLastYearTotalDebt() != null ? nw.getLastYearTotalDebt() : 0d));
		nw.setDebtPaidLastMonth((nw.getTotalCurrentDebt() != null ? nw.getTotalCurrentDebt() : 0)
				- (nw.getTotalPriorDebt() == null ? nw.getTotalPriorDebt() : 0d));
		nw.setTotalNetEquity((nw.getTotalCurrentAsset() != null ? nw.getTotalCurrentAsset() : 0)
				- (nw.getTotalCurrentDebt() != null ? nw.getTotalCurrentDebt() : 0d));
		nw.setPriorNetEquity((nw.getTotalPriorAsset() != null ? nw.getTotalPriorAsset() : 0d)
				- (nw.getTotalPriorDebt() == null ? nw.getTotalPriorDebt() : 0d));
		nw.setChangeInNetEquity(nw.getTotalNetEquity() != null ? nw.getTotalNetEquity()
				: 0d - (nw.getPriorNetEquity() != null ? nw.getPriorNetEquity() : 0d));
		nw.setNetEquityYtdChange(nw.getTotalNetEquity() != null ? nw.getTotalNetEquity()
				: 0d - (nw.getLastYearNetEquity() != null ? nw.getLastYearNetEquity() : 0d));
		repo.save(nw);
		return "Your Net Worth is :" + nw.getTotalNetEquity();
	}

	@Override
	public NetWorth getNetWorth(String id) {
		List<NetWorth> nws = repo.findAll().stream().filter(data -> id.equals(data.getId())).collect(Collectors.toList());
		NetWorth nw = nws.get(0);
		return nw;
	}

	@Override
	public NetWorth lastYearDetails(LastYearDetails lastYearDetails) {
		NetWorth nw = new NetWorth();
		nw.setLastYearTotalAssets(lastYearDetails.getLastYearAsset());
		nw.setLastYearTotalDebt(lastYearDetails.getLastYearDebt());
		nw.setLastYearNetEquity(lastYearDetails.getLastYearAsset() - lastYearDetails.getLastYearDebt());
		return repo.save(nw);
	}

}
