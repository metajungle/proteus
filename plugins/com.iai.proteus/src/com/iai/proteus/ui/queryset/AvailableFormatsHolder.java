/*
 * Copyright (C) 2013 Intelligent Automation Inc. 
 * 
 * All Rights Reserved.
 */
package com.iai.proteus.ui.queryset;

import java.util.ArrayList;
import java.util.Collection;

public class AvailableFormatsHolder {

	/*
	 * Holds the available formats
	 */
	private Collection<String> availableFormats;

	/**
	 * Constructor
	 *
	 */
	public AvailableFormatsHolder() {

		availableFormats = new ArrayList<String>();
	}

	/**
	 * Sets the available formats
	 *
	 * @param formats
	 */
	public void setAvailableFormats(Collection<String> formats) {
		this.availableFormats = formats;
	}

	/**
	 * Returns the available formats
	 *
	 * @return
	 */
	public Collection<String> getAvailableFormats() {
		return availableFormats;
	}

}
