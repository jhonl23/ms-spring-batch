package com.intercorp.msspringbatch.configuration;

import com.intercorp.msspringbatch.steps.ItemProcessorStep;
import com.intercorp.msspringbatch.steps.ItemReaderStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    @JobScope
    public ItemReaderStep itemReaderStep(){
        return new ItemReaderStep();
    }

    @Bean
    @JobScope
    public ItemProcessorStep itemProcessorStep(){
        return new ItemProcessorStep();
    }

    @Bean
    public Step readFileStep(){
        return stepBuilderFactory.get("itemReaderStep")
                .tasklet(itemReaderStep())
                .build();
    }

    @Bean
    public Step processDataStep(){
        return stepBuilderFactory.get("itemProcessorStep")
                .tasklet(itemProcessorStep())
                .build();
    }

    @Bean
    public Job readXmlJob(){
        return jobBuilderFactory.get("readXmlJob")
                .start(readFileStep())
                .next(processDataStep())
                .build();
    }

}
