package com.nyanmyohtet.studentregistrationservice.config;

import com.nyanmyohtet.studentregistrationservice.annotation.CustomAnnotation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomAnnotationProcessor {

    private static final Logger logger = LogManager.getLogger(CustomAnnotationProcessor.class);

    public CustomAnnotationProcessor() throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomAnnotation.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("com.nyanmyohtet")) {
            String className = bd.getBeanClassName();

            try {
                Class<?> clazz = Class.forName(className);
                CustomAnnotation annotation = clazz.getAnnotation(CustomAnnotation.class);
                if (annotation != null) {
                    String value = annotation.value();
                    logger.info("Class: {}, Value: {}", clazz.getName(), value);
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage(), e);
                throw e;
            }
        }
    }
}