package com.solumesl.aims.saas.adapter.controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.solumesl.aims.saas.adapter.constants.AzureConstants;
import com.solumesl.aims.saas.adapter.constants.SaasConstants;
import com.solumesl.aims.saas.adapter.exception.ConstraintViolationException;
import com.solumesl.aims.saas.adapter.exception.InvalidCompany;
import com.solumesl.aims.saas.adapter.resourcebundle.AbstractResourceBundle;

public abstract class BaseController extends AbstractResourceBundle{
	
	protected boolean isSuperAdmin(String customerCode) {
		return SaasConstants.SUPER_ADMIN.equals(customerCode);
	}

	protected void validateRequest(String company, String country, boolean checkCountry, boolean checkCompany) throws ConstraintViolationException, InvalidCompany {
		
		if(checkCountry && country == null) {
			throw new ConstraintViolationException(SaasConstants.COUNTRY);
		}
		if(checkCompany && Objects.isNull(company)) {
			throw new ConstraintViolationException(SaasConstants.COMPANY);
		}
		
	}



	protected void checkUserBelongToCompany(String company) throws InvalidCompany {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		@SuppressWarnings("unchecked")
		Map<String,Object> tokenMap = (Map<String, Object>) authentication.getDetails();

		String customerCode = (String) tokenMap.get(AzureConstants.CUSTOMER_CODE);

		if( !customerCode.equals(company) && !isSuperAdmin(customerCode)) {
			throw new InvalidCompany(company);
		}
	}
}
