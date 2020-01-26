package com.multi.singleton;

import java.util.HashMap;
import java.util.Map;

public class Data {
	private Map<String, String> messages;
	private static Data self;

	/**
	 * 
	 */
	private Data() {
		messages = new HashMap<String, String>();
	}

	public static Data getInstance() {
		if (self == null) {
			self = new Data();
		}
		return self;
	}

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

}
