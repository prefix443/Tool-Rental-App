package com.demo.toolrental.data;

import java.util.HashMap;

import com.demo.toolrental.domain.ToolType;
import com.demo.toolrental.domain.Tool;

/**
 * Retrieve tools (representing an example data layer for demo purposes)
 *
 * @author Andrew
 */
public class ToolData {

	/**
	 * Retrieve all available tools
	 * 
	 * @return a map of a tool key to the tool
	 */
	public static HashMap<String, Tool> getTools() {
		HashMap<String, Tool> tools = new HashMap<String, Tool>();
		tools.put("LADW", Tool.from("LADW", ToolType.LADDER, "Werner"));
		tools.put("CHNS", Tool.from("CHNS", ToolType.CHAINSAW, "Stihl"));
		tools.put("JAKR", Tool.from("JAKR", ToolType.JACKHAMMER, "Warner"));
		tools.put("JAKD", Tool.from("JAKD", ToolType.JACKHAMMER, "DeWalt"));
		return tools;
	}

}
