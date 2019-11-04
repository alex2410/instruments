package ru.trushkin.instruments.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class MeanCalculator {

    private ConcurrentHashMap<Thread, Integer> count = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Thread, BigDecimal> sum = new ConcurrentHashMap<>();

    public void calculate(BigDecimal data) {
        count.compute(Thread.currentThread(), (thread, value) -> value == null ? 1 : value + 1);
        sum.compute(Thread.currentThread(), (thread, value) -> value == null ? data : value.add(data));
    }

    public BigDecimal getCurrentResult() {
        int countSum = count.values().stream().mapToInt(Integer::intValue).sum();
        Optional<BigDecimal> reduce = sum.values().stream().reduce(BigDecimal::add);
        return reduce.orElse(BigDecimal.ZERO).divide(new BigDecimal(countSum), 5, RoundingMode.HALF_UP);
    }
}
