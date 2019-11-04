package ru.trushkin.instruments;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.trushkin.instruments.calculator.Calculator;
import ru.trushkin.instruments.calculator.InstrumentMaxCalculator;
import ru.trushkin.instruments.calculator.InstrumentMeanCalculator;
import ru.trushkin.instruments.calculator.InstrumentNewestSumCalculator;
import ru.trushkin.instruments.calculator.filter.*;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class InstrumentCalculatorsConfiguration {

    private static final String INSTRUMENT1 = "INSTRUMENT1";
    private static final String INSTRUMENT2 = "INSTRUMENT2";
    private static final String INSTRUMENT3 = "INSTRUMENT3";
    private static final String INSTRUMENT4 = "INSTRUMENT4";

    @Bean
    public Calculator<Instrument, BigDecimal> instrument1Calculator() {
        FilterableInstrumentCalculator calculator = new FilterableInstrumentCalculator(new InstrumentMeanCalculator());
        calculator.setFilters(commonFilters());
        return calculator;
    }

    @Bean
    public Calculator<Instrument, BigDecimal> instrument2Calculator() {
        FilterableInstrumentCalculator calculator = new FilterableInstrumentCalculator(new InstrumentMeanCalculator());
        List<Filter<Instrument>> filters = new ArrayList<>(commonFilters());
        filters.add(dateFilter("01-Nov-2014", "30-Nov-2014"));
        calculator.setFilters(filters);
        return calculator;
    }

    @Bean
    public Calculator<Instrument, BigDecimal> instrument3Calculator() {
        FilterableInstrumentCalculator calculator = new FilterableInstrumentCalculator(new InstrumentMaxCalculator());
        List<Filter<Instrument>> filters = new ArrayList<>(commonFilters());
        filters.add(dateFilter("01-Jun-2014", "31-Dec-2014"));
        calculator.setFilters(filters);
        return calculator;
    }

    @Bean
    public Calculator<Instrument, BigDecimal> instrument4Calculator() {
        FilterableInstrumentCalculator calculator = new FilterableInstrumentCalculator(new InstrumentNewestSumCalculator(10));
        calculator.setFilters(commonFilters());
        return calculator;
    }

    @Bean
    public List<Filter<Instrument>> commonFilters() {
        List<Filter<Instrument>> filters = new ArrayList<>();
        filters.add(normalDataFilter());
        filters.add(currentDateFilter());
        filters.add(businessDatesFilter());

        return filters;
    }

    @Bean
    public Filter<Instrument> businessDatesFilter() {
        return new BusinessDatesFilter();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Filter<Instrument> dateFilter(String startDate, String endDate) {
        return new DateFilter(startDate, endDate);
    }

    @Bean
    public Filter<Instrument> currentDateFilter() {
        return new CurrentDateFilter();
    }

    @Bean
    public Filter<Instrument> normalDataFilter() {
        return new NormalDataFilter();
    }

    @Bean
    public Map<String, Calculator<Instrument, ?>> instrumentCalculators() {
        Map<String, Calculator<Instrument, ?>> map = new HashMap<>();
        map.put(INSTRUMENT1, instrument1Calculator());
        map.put(INSTRUMENT2, instrument2Calculator());
        map.put(INSTRUMENT3, instrument3Calculator());
        map.put(INSTRUMENT4, instrument4Calculator());
        return map;
    }
}
