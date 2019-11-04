package ru.trushkin.instruments.calculator;

import org.junit.Test;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class InstrumentMaxCalculatorTest {

    InstrumentMaxCalculator calculator = new InstrumentMaxCalculator();

    @Test
    public void calculate() {
        BigDecimal max = new BigDecimal("11.22");
        calculator.calculate(Instrument.of(null, null, BigDecimal.ZERO));
        calculator.calculate(Instrument.of(null, null, BigDecimal.ONE));
        calculator.calculate(Instrument.of(null, null, new BigDecimal("11.22")));
        calculator.calculate(Instrument.of(null, null, new BigDecimal("11.21")));
        assertEquals(max, calculator.getCurrentResult());
    }
}