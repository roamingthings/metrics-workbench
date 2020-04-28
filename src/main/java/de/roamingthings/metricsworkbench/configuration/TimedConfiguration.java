package de.roamingthings.metricsworkbench.configuration;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// See https://micrometer.io/docs/concepts#_the_timed_annotation
@Configuration
@ConditionalOnClass(name = "org.aspectj.lang.ProceedingJoinPoint")
public class TimedConfiguration {
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
