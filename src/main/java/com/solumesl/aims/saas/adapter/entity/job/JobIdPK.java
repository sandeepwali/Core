package com.solumesl.aims.saas.adapter.entity.job;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class JobIdPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long jobId;
}
