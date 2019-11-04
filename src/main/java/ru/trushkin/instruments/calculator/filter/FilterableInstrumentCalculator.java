package ru.trushkin.instruments.calculator.filter;

import ru.trushkin.instruments.calculator.Calculator;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.List;

public class FilterableInstrumentCalculator implements Calculator<Instrument, BigDecimal> {

    private Calculator<Instrument, BigDecimal> calculator;
    private List<Filter<Instrument>> filters;

    public FilterableInstrumentCalculator(Calculator<Instrument, BigDecimal> calculator) {
        this.calculator = calculator;
    }

    @Override
    public void calculate(Instrument data) {
        if (filters.stream().allMatch(instrumentFilter -> instrumentFilter.isAcceptable(data))) {
            calculator.calculate(data);
        }
    }

    @Override
    public BigDecimal getCurrentResult() {
        return calculator.getCurrentResult();
    }

    public void setFilters(List<Filter<Instrument>> filters) {
        this.filters = filters;
    }
}
