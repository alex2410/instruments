package ru.trushkin.instruments.calculator.filter;

import org.junit.Test;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class NormalDataFilterTest {

    NormalDataFilter filter = new NormalDataFilter();

    @Test
    public void isAcceptable() {
        assertFalse(filter.isAcceptable(Instrument.of(null, null, null)));
        assertFalse(filter.isAcceptable(Instrument.of("", null, null)));
        assertFalse(filter.isAcceptable(Instrument.of(null, new Date(), null)));
        assertFalse(filter.isAcceptable(Instrument.of(null, null, BigDecimal.ZERO)));
        assertTrue(filter.isAcceptable(Instrument.of("", new Date(), BigDecimal.ZERO)));
    }
}