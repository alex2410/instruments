package ru.trushkin.instruments;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.trushkin.instruments.process.CalculationProcess;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(InstrumentsConfiguration.class);
        ctx.getBean(CalculationProcess.class).start();
    }

}
