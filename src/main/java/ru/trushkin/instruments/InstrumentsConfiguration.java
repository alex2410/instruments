package ru.trushkin.instruments;

import org.springframework.context.annotation.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.trushkin.instruments.consumer.InstrumentConsumerTask;
import ru.trushkin.instruments.consumer.InstrumentDataConsumer;
import ru.trushkin.instruments.entity.Instrument;
import ru.trushkin.instruments.process.CalculationProcess;
import ru.trushkin.instruments.process.InstrumentCalculationProcess;
import ru.trushkin.instruments.reader.InstrumentReader;
import ru.trushkin.instruments.reader.Reader;

@Configuration
@ComponentScan("ru.trushkin.instruments")
@PropertySource(value = "classpath:instruments.properties", encoding = "UTF-8")
@Import(InstrumentCalculatorsConfiguration.class)
public class InstrumentsConfiguration {

    @Bean
    public Reader<Instrument> instrumentReader() {
        return new InstrumentReader();
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        executor.setThreadNamePrefix("threadPoolExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    @Bean
    public InstrumentDataConsumer instrumentDataConsumer() {
        InstrumentDataConsumer consumer = new InstrumentDataConsumer();
        consumer.setExecutor(taskExecutor());
        consumer.setCalculator(instrumentCalculator());
        return consumer;
    }

    @Bean
    public InstrumentConsumerTask instrumentCalculator() {
        return new InstrumentConsumerTask();
    }

    @Bean
    public CalculationProcess instrumentCalculationProcess() {
        InstrumentCalculationProcess process = new InstrumentCalculationProcess();
        process.setReader(instrumentReader());
        process.setConsumer(instrumentDataConsumer());
        return process;
    }
}
