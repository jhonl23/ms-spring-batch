package com.intercorp.msspringbatch.steps;

import com.intercorp.msspringbatch.kafka.producer.EventMsOneProducer;
import com.intercorp.msspringbatch.models.Root;
import org.springframework.batch.core.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemReaderStep implements Tasklet {

    @Autowired
    private EventMsOneProducer eventMsOneProducer;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // STEP (1) - INICIO FLUJO
        List<Root> listRoot = new ArrayList<>();
        Resource[] resources = resourcePatternResolver.getResources("xmls/*.xml");

        for (Resource resource: resources) {
            File xmlFile = resource.getFile();

            JAXBContext context = JAXBContext.newInstance(Root.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Root root = (Root) unmarshaller.unmarshal(xmlFile);

            listRoot.add(root);

        }
        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("listRoot", listRoot);

        return RepeatStatus.FINISHED;
    }

}