package ru.trushkin.instruments.consumer;

import ru.trushkin.instruments.calculator.Calculator;
import ru.trushkin.instruments.entity.Instrument;

import javax.annotation.Resource;
import java.util.Map;

public class InstrumentConsumerTask implements ConsumerTask<Instrument> {

    @Resource(name = "instrumentCalculators")
    private Map<String, Calculator<Instrument, ?>> calculatorsMap;

    @Override
    public void execute(Instrument data) {
        Calculator<Instrument, ?> calculator = calculatorsMap.get(data.getName());
        if (calculator != null) {
            calculator.calculate(data);
        }
    }
}
