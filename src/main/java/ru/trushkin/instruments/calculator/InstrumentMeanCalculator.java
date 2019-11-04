package ru.trushkin.instruments.calculator;

import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;

public class InstrumentMeanCalculator implements Calculator<Instrument, BigDecimal> {

    private MeanCalculator meanCalculator;

    public InstrumentMeanCalculator() {
        meanCalculator = new MeanCalculator();
    }

    @Override
    public void calculate(Instrument data) {
        meanCalculator.calculate(data.getValue());
    }

    @Override
    public BigDecimal getCurrentResult() {
        return meanCalculator.getCurrentResult();
    }
}
