package com.networth.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Document
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class NetWorth {

	@Id
	private String id;
	private List<AssetDebt> assetDebts;
	private List<YoyNetWorth> yoYs;
	private Double totalPriorAsset;
	private Double totalCurrentAsset;
	private Double lastYearTotalAssets;
	private Double lastYearTotalDebt;
	private Double assetYtdChange;
	private Double totalCurrentDebt;
	private Double totalPriorDebt;
	private Double debtPaidLastMonth;
	private Double debtPaidOffYTD;
	private Double totalNetEquity;
	private Double priorNetEquity;
	private Double changeInNetEquity;
	private Double lastYearNetEquity;
	private Double netEquityYtdChange;
	private Double networth;
	 
}
