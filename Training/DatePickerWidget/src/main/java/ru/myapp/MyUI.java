package ru.myapp;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ru.myapp.widget.YearMonthPicker;
import java.util.Date;

@Theme("mytheme")
@Widgetset("ru.myapp.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();
        YearMonthPicker yearMonthPicker = new YearMonthPicker(2010);

        Button addButton = new Button("Добавить виджет");
        addButton.addClickListener( e -> {
            layout.addComponent(yearMonthPicker);
        });

        Button getButton = new Button("Получить дату");
        getButton.addClickListener( e -> {
            Date date = yearMonthPicker.getDate();
        });
        
        layout.addComponents(addButton, getButton);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
