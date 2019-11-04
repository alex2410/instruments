package ru.trushkin.instruments.calculator;

import org.junit.Test;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class InstrumentMeanCalculatorTest {

    InstrumentMeanCalculator calculator = new InstrumentMeanCalculator();

    @Test
    public void calculate() {
        BigDecimal[] values = {BigDecimal.ZERO, BigDecimal.ONE, new BigDecimal("11.22"), new BigDecimal("11.21")};
        for (BigDecimal value : values) {
            calculator.calculate(Instrument.of(null, null, value));
        }
        Optional<BigDecimal> reduce = Arrays.stream(values).reduce(BigDecimal::add);
        BigDecimal value = reduce.get().divide(new BigDecimal(values.length), 5, RoundingMode.HALF_UP);
        assertEquals(value, calculator.getCurrentResult());
    }
}