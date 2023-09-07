package com.intercorp.msspringbatch.steps;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ItemReaderStepTest {

    @Mock
    private ResourceLoader resourceLoader;

    @Test
    void execute() throws Exception {

        MockitoAnnotations.openMocks(this);

        String mockXml = "<root>\n" +
                "  <person\n" +
                "  firstname=\"Nonnah\"\n" +
                "  lastname=\"Waite\"\n" +
                "  city=\"Minsk\"\n" +
                "  country=\"France\"\n" +
                "  firstname2=\"Myriam\"\n" +
                "  lastname2=\"Kronfeld\"\n" +
                "  email=\"Myriam.Kronfeld@yopmail.com\"\n" +
                "  />\n" +
                "  <random>22</random>\n" +
                "  <random_float>17.059</random_float>\n" +
                "  <bool>true</bool>\n" +
                "  <date>1998-12-11</date>\n" +
                "  <regEx>hellooooooooooooooooooooooooooooooooooooooooo to you</regEx>\n" +
                "  <enum>generator</enum>\n" +
                "  <elt>Jaime</elt><elt>Kimberley</elt><elt>Caryl</elt><elt>Basia</elt><elt>Thalia</elt>  \n" +
                "  <Myriam>\n" +
                "    <age>56</age>\n" +
                "  </Myriam>\n" +
                "</root>";

        InputStream inputStream = new ByteArrayInputStream(mockXml.getBytes());

        Mockito.when(resourceLoader.getResource("classpath:static/files/myXMLFile0.xml").getInputStream()).thenReturn(inputStream);

        ItemReaderStep itemReaderStep = new ItemReaderStep();
        StepContribution stepContribution = new StepContribution(new StepExecution("test", new JobExecution(new JobInstance(1L, "testjob"), null)));
        ChunkContext chunkContext = new ChunkContext(null);

        itemReaderStep.execute(stepContribution, chunkContext);

    }
    
}