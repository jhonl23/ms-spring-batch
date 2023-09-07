package com.intercorp.msspringbatch.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intercorp.msspringbatch.kafka.producer.EventMsOneProducer;
import com.intercorp.msspringbatch.models.Root;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;

import java.util.List;

public class ItemProcessorStep implements Tasklet {

    @Autowired
    private EventMsOneProducer eventMsOneProducer;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Recuperamos la lista
        List<Root> listRoot = (List<Root>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext().get("listRoot");

        System.out.println("Before send data to MS-ONE");

        for (Root rootObj: listRoot) {
            System.out.println("send data to MS-ONE...");
            this.sendMessageEventOneConsumer(rootObj);
        }

        return RepeatStatus.FINISHED;
    }

    private void sendMessageEventOneConsumer(Root root) {
        try {
            eventMsOneProducer.sendMessage(root);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
