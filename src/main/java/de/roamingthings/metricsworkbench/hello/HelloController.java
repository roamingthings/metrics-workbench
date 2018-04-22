package de.roamingthings.metricsworkbench.hello;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final Counter counter;

    public HelloController(MeterRegistry registry) {
        this.counter = registry.counter("hello.greeted");
    }

    @GetMapping("/hello")
    @Timed(value = "hello_service", extraTags = { "region", "de-east-1" })
    public ResponseEntity<String> sayHello() {
        this.counter.increment();

        return ResponseEntity.ok("Hello Duke!");
    }
}
