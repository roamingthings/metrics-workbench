package de.roamingthings.metricsworkbench.hello;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest {

    private MockMvc mockMvc;

    private Counter counter;

    @Before
    public void init(){
        MeterRegistry registry = mock(MeterRegistry.class);
        counter = mock(Counter.class);
        when(registry.counter(eq("hello.greeted"))).thenReturn(counter);

        HelloController helloController = new HelloController(registry);

        mockMvc = MockMvcBuilders
                .standaloneSetup(helloController)
                .build();
    }

    @Test
    public void sayHello_should_respond_with_a_message() throws Exception {
        // when
        ResultActions performed = mockMvc.perform(get("/hello"));

        // then
        performed.andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Duke!")));
        // and
        verify(counter, times(1)).increment();
    }

}