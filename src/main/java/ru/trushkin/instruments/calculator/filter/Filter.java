package ru.trushkin.instruments.calculator.filter;

/**
 * Фильтр сущностей
 *
 * @param <T> - тип сущности
 */
public interface Filter<T> {

    /**
     * Проверяет, что фильтр пропускает сущность
     *
     * @param data - сущность для проверки
     * @return true, если сущность должна остаться после фильтра
     */
    boolean isAcceptable(T data);
}
