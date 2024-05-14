package com.solumesl.aims.saas.adapter.processor.steps;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
public interface Step extends Tasklet {
	boolean execute() throws Exception;

	public default RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception 
	{

		execute();

		return RepeatStatus.FINISHED;
	} 
}
