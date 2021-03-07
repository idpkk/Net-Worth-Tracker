package com.networth.pojo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AssetDebtRequest {

	@NotBlank
	private String name;
	private Double currentValue;
	private Double priorValue;
	private Double currentBalance;
	private Double priorBalance;

}

