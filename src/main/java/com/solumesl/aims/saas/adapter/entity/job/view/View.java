package com.solumesl.aims.saas.adapter.entity.job.view;

import java.util.HashMap;
import java.util.Map;

public class View {
	
	 public static final Map<Role, Class> MAPPING = new HashMap<>();

	    static {
	        MAPPING.put(Role.ROLE_SUPER_ADMIN, SuperAdmin.class);
	        MAPPING.put(Role.ROLE_ADMIN, Admin.class);
	    }
	    
	public static class Admin {};
	public static class SuperAdmin extends Admin {};
}
enum Role {
    ROLE_SUPER_ADMIN,
    ROLE_ADMIN
}