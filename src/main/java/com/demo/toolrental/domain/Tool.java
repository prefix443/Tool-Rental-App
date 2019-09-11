package com.demo.toolrental.domain;

/**
 * Tool used for rental
 * 
 * @author Andrew
 */
public class Tool {

	private String toolCode;
	private ToolType toolType;
	private String brand;

	public Tool(String toolCode, ToolType toolType, String brand) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.brand = brand;
	}

	public static Tool from(String toolCode, ToolType toolType, String brand) {
		return new Tool(toolCode, toolType, brand);
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public ToolType getToolType() {
		return toolType;
	}

	public void setToolType(ToolType toolType) {
		this.toolType = toolType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
