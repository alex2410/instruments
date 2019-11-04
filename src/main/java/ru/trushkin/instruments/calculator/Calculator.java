package ru.trushkin.instruments.calculator;

/**
 * Калькулятор значений сущности
 *
 * @param <T> - тип сущности
 * @param <R> - тип результата
 */
public interface Calculator<T, R> {

    /**
     * Вычисляет значение
     *
     * @param data - сущность для вычисления
     */
    void calculate(T data);

    /**
     * Получает текущий результат
     *
     * @return - текущий результат
     */
    R getCurrentResult();
}
