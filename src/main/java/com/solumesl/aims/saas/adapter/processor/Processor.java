package com.solumesl.aims.saas.adapter.processor;

import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.solumesl.aims.saas.adapter.processor.steps.Step;
import com.solumesl.aims.saas.adapter.resourcebundle.AbstractResourceBundle;
import com.solumesl.aims.saas.adapter.util.SolumSaasConfigUtil;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author baskarmohanasundaram
 *
 */
@Slf4j
public abstract class Processor<T,V> extends AbstractResourceBundle {





	protected Map<String, String> readConfigDataFromXml(String configKeyName) {
		return SolumSaasConfigUtil.getMap(configKeyName);
	}

	protected   void executeSteps(Step[] steps) throws Exception {
		 executeLabel:for (Step step : steps) {
			log.info("Executing step: "+ step.getClass().getSimpleName());
			try {
				if(!step.execute()) {
					log.info("Terminating step: "+ step.getClass().getSimpleName());
					break executeLabel;
				}
			} catch (Exception e) {
				log.info("Terminating step: {}", step.getClass().getSimpleName());
				log.error("Error in Step-->{}, Exception {}", step.getClass().getSimpleName(), ExceptionUtils.getStackTrace(e));
				throw e;
			}
			log.info("The following step is completed: "+ step.getClass().getSimpleName());
		}
	}

	public abstract V processRequest(T requestParams) throws Exception;




}
