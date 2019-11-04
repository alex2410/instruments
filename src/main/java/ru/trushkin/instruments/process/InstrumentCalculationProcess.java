package ru.trushkin.instruments.process;

import ru.trushkin.instruments.calculator.Calculator;
import ru.trushkin.instruments.consumer.DataConsumer;
import ru.trushkin.instruments.entity.Instrument;
import ru.trushkin.instruments.reader.Reader;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InstrumentCalculationProcess implements CalculationProcess {

    private Reader<Instrument> reader;
    private DataConsumer<Instrument> consumer;

    @Resource(name = "instrumentCalculators")
    private Map<String, Calculator<Instrument, ?>> calculatorsMap;

    @Override
    public void start() {
        reader.readData(consumer);
        consumer.finish();
        printResults();
    }

    private void printResults() {
        calculatorsMap.forEach((s, instrumentCalculator) -> {
            System.out.println("Calculator name: " + s + ", result: " + instrumentCalculator.getCurrentResult());
        });
    }

    public void setReader(Reader<Instrument> reader) {
        this.reader = reader;
    }

    public void setConsumer(DataConsumer<Instrument> consumer) {
        this.consumer = consumer;
    }
}
