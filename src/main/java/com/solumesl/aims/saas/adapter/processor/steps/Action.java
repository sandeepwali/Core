package com.solumesl.aims.saas.adapter.processor.steps;

public abstract class Action<T>  implements Step,StepOutput<T> {

	private T returnValue;

	protected void setReturnValue(T returnValue) {
		this.returnValue = returnValue;
	}

	public T getOutput() {
		return returnValue;
	}

	 
}	
