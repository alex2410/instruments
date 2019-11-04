package ru.trushkin.instruments.calculator;

import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

public class InstrumentNewestSumCalculator implements Calculator<Instrument, BigDecimal> {

    private static final Comparator<Instrument> comparator = Comparator.comparing(Instrument::getDate).reversed();
    private ConcurrentHashMap<Thread, ConcurrentSkipListSet<Instrument>> lastInstruments = new ConcurrentHashMap<>();
    private int max;

    public InstrumentNewestSumCalculator(int max) {
        this.max = max;
    }

    @Override
    public void calculate(Instrument data) {
        lastInstruments.compute(Thread.currentThread(), (thread, instruments) -> {
            if (instruments == null) {
                instruments = new ConcurrentSkipListSet<>(comparator);
            }
            instruments.add(data);
            if (instruments.size() > max) {
                instruments.remove(instruments.last());
            }
            return instruments;
        });
    }

    @Override
    public BigDecimal getCurrentResult() {
        List<Instrument> orderedLastValues = lastInstruments.values().stream().flatMap(Collection::stream).sorted(comparator).collect(Collectors.toList());
        return orderedLastValues.stream().limit(max).map(Instrument::getValue).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
