package com.demo.toolrental.domain;

import javax.persistence.*;

/**
 * Tool used for rental
 * 
 * @author Andrew
 */

@Entity
@Table(name = "tools")
public class Tool {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tool_code")
	private String toolCode;

	@ManyToOne
	@JoinColumn(name = "tool_type_id")
	private ToolType toolType;

	@Column(name = "brand")
	private String brand;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
