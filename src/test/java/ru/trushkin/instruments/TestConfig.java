package ru.trushkin.instruments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.trushkin.instruments.calculator.filter.CurrentDateFilter;
import ru.trushkin.instruments.calculator.filter.DateFilter;
import ru.trushkin.instruments.calculator.filter.Filter;
import ru.trushkin.instruments.calculator.filter.NormalDataFilter;
import ru.trushkin.instruments.entity.Instrument;
import ru.trushkin.instruments.reader.InstrumentReader;
import ru.trushkin.instruments.reader.Reader;

import java.io.FileNotFoundException;
import java.io.InputStream;

@Configuration
@PropertySource(value = "classpath:test.properties", encoding = "UTF-8")
public class TestConfig {
    @Bean
    public Filter<Instrument> dateFilter() {
        return new DateFilter("01-Nov-2014", "30-Nov-2014");
    }

    @Bean
    public Reader<Instrument> instrumentReader() {
        InstrumentReader reader = new InstrumentReader() {
            @Override
            protected InputStream getStream() throws FileNotFoundException {
                return this.getClass().getClassLoader().getResourceAsStream("test.txt");
            }
        };
        return reader;
    }

    @Bean
    public Filter<Instrument> currentDateFilter() {
        return new CurrentDateFilter();
    }

    @Bean
    public Filter<Instrument> normalDataFilter() {
        return new NormalDataFilter();
    }

}
