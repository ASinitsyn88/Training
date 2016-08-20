package ru.myapp.widget;

import com.vaadin.ui.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

public class YearMonthPicker extends CustomComponent {

    private NativeSelect yearSelect;
    private NativeSelect monthSelect;
    private List<String> monthList;

    /**
     * Конструктор
     * @param startYear - год,с которого начинается выпадающий список
     */
    public YearMonthPicker(int startYear) {

        HorizontalLayout selectLayout = new HorizontalLayout();
        selectLayout.setSizeUndefined();
        selectLayout.setSpacing(true);

        configureLayoutComponents(startYear);

        selectLayout.addComponent(yearSelect);
        selectLayout.addComponent(monthSelect);

        setCompositionRoot(selectLayout);
    }

    /**
     * Настроить компоненты
     * @param startYear - год,с которого начинается выпадающий список
     */
    private void configureLayoutComponents(int startYear) {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        final List<String> yearList = generateYearListFromDiapason(startYear, currentYear);
        String month = "Январь,Февраль,Март,Апрель,Май,Июнь,Июль,Август,Сентябрь,Октябрь,Ноябрь,Декабрь";
        monthList = Arrays.asList(month.split(","));
        int currentYearIndex = getCurrentYearIndexFromList(currentYear, yearList);

        yearSelect = new NativeSelect();
        monthSelect = new NativeSelect();

        yearSelect.addItems(yearList);
        yearSelect.setValue(new ArrayList<Object>(yearSelect.getItemIds()).get(currentYearIndex));
        yearSelect.setNullSelectionAllowed(false);

        monthSelect.addItems(monthList);
        monthSelect.setValue(new ArrayList<Object>(monthSelect.getItemIds()).get(currentMonth));
        monthSelect.setNullSelectionAllowed(false);
    }

    /**
     * Сформировать список годов из диапазона(от "год начала" до "текущий год" + 1)
     * @return List<String>
     */
    private List<String> generateYearListFromDiapason(int startYear, int currentYear) {

        List<String> yearList = new ArrayList<>();
        if (startYear > currentYear || startYear < 0) {
            startYear = currentYear;
        }
        for (int i = startYear; i <= currentYear + 1; i++) {
            yearList.add(String.valueOf(i));
        }
        return yearList;
    }

    /**
     * Получить индекс текущего года из списка годов
     * @param currentYear - текущий год
     * @param yearList - список годов
     * @return int
     */
    private int getCurrentYearIndexFromList(int currentYear, List<String> yearList) {

        int index = yearList.indexOf(String.valueOf(currentYear));
        return (index != -1) ? index : 0;
    }

    /**
     * Установить высоту и ширину выпадающего списка "Год" в указанных единицах
     * @param height - высота
     * @param width - ширина
     */
    public void setYearSizePixels(float height, float width) {

        yearSelect.setHeight(height, Unit.PIXELS);
        yearSelect.setWidth(width, Unit.PIXELS);
    }

    /**
     * Установить высоту и ширину выпадающего списка "Месяц" в указанных единицах
     * @param height - высота
     * @param width - ширина
     */
    public void setMonthSizePixels(float height, float width) {

        monthSelect.setHeight(height, Unit.PIXELS);
        monthSelect.setWidth(width, Unit.PIXELS);
    }

    /**
     * Получить выставленную дату
     * @return Date
     */
    public Date getDate() {

        String year = (String) yearSelect.getValue();
        int month = monthList.indexOf((String) monthSelect.getValue()) + 1;

        Date date = null;
        String strDate = year + "." + String.valueOf(month) + ".02";
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
