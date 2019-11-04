package ru.trushkin.instruments.calculator;

import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class InstrumentMaxCalculator implements Calculator<Instrument, BigDecimal> {

    private AtomicReference<BigDecimal> max;

    public InstrumentMaxCalculator() {
        max = new AtomicReference<>(BigDecimal.ZERO);
    }

    @Override
    public void calculate(Instrument data) {
        BigDecimal newMax = null;
        BigDecimal currentMax = null;
        do {
            currentMax = max.get();
            int i = currentMax.compareTo(data.getValue());
            if (i < 0) {
                newMax = data.getValue();
            } else {
                return;
            }
        } while (max.compareAndSet(currentMax, newMax));
    }

    @Override
    public BigDecimal getCurrentResult() {
        return max.get();
    }
}
