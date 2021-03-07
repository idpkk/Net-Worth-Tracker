package com.networth.model;

import lombok.Data;

@Data
public class AssetDebt {

	private String name;
	private Double currentAssetValue;
	private Double priorAssetValue;
	private Double priorDebtBalance;
	private Double currentDebtBalance;
	private Double netEquity;
}
