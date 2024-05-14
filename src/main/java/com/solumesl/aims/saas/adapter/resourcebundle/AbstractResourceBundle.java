package com.solumesl.aims.saas.adapter.resourcebundle;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
public  abstract class AbstractResourceBundle {
	@Autowired
	protected ResourceBundleMessageSource resourceBundleMessageSource;

	public String getMessage(String key) {
		if(resourceBundleMessageSource == null) {
			return key;
		}
		return resourceBundleMessageSource.getMessage(key, null, Locale.US);
	}
}
