package ru.trushkin.instruments.consumer;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.trushkin.instruments.entity.Instrument;

public class InstrumentDataConsumer implements DataConsumer<Instrument> {

    private ThreadPoolTaskExecutor executor;
    private ConsumerTask<Instrument> calculator;

    @Override
    public void onData(Instrument data) {
        executor.execute(() -> calculator.execute(data));
    }

    @Override
    public void finish() {
        executor.shutdown();
        executor.setAwaitTerminationSeconds(Integer.MAX_VALUE);
    }

    public void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void setCalculator(ConsumerTask<Instrument> calculator) {
        this.calculator = calculator;
    }
}
