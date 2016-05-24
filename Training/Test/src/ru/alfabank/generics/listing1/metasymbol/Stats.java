package ru.alfabank.generics.listing1.metasymbol;

/** This comment must be only in branch testing */
public class Stats<T extends Number> {

    private T[] nums;

    // Конструктор
    Stats(T[] o) {

        nums = o;
    }

    /**
     * Получить среднее арифметическое из объекта переданного в конструкторе
     * @return double
     */
    public double average() {

        double sum = 0.0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }

    /**
     * Сравнить среднее арифметическое двух объектов
     * несмотря на то что объекты имеют разную параметризацию
     * @param ob
     * @return
     */
    boolean sameAvg(Stats<?> ob) {

        if(average() == ob.average()) {
            return true;
        }

        return false;
    }
}
