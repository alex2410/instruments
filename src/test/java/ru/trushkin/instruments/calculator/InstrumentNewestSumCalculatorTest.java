package ru.trushkin.instruments.calculator;

import org.junit.Test;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class InstrumentNewestSumCalculatorTest {

    InstrumentNewestSumCalculator calculator = new InstrumentNewestSumCalculator(3);

    @Test
    public void calculate() throws InterruptedException {
        BigDecimal[] values = {new BigDecimal("11.22"), BigDecimal.ZERO, BigDecimal.ONE, new BigDecimal("11.22"), new BigDecimal("11.21")};
        Date currentDate = new Date();
        for (int i = 0; i < values.length; i++) {
            calculator.calculate(Instrument.of(null, getDate(currentDate, i), values[i]));
        }
        BigDecimal value = BigDecimal.ONE.add(new BigDecimal("11.22")).add(BigDecimal.ZERO);
        assertEquals(value, calculator.getCurrentResult());
    }

    private Date getDate(Date currentDate, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.HOUR, -1 * i);
        return calendar.getTime();
    }
}