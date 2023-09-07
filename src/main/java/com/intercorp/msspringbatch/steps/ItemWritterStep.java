package com.intercorp.msspringbatch.steps;

import com.intercorp.msspringbatch.models.Root;
import com.intercorp.msspringbatch.services.IRootService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemWritterStep implements Tasklet {

    @Autowired
    private IRootService rootService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        List<Root> rootList = (List<Root>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext().get("roots");

        return RepeatStatus.FINISHED;
    }

}
