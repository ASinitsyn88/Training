package ru.asinitsyn;

import javax.servlet.annotation.WebServlet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("ru.asinitsyn.MyAppWidgetset")
public class MyUI extends UI {

    private CustomerService service = CustomerService.getInstance();
    private Grid grid = new Grid();
    private TextField filterText = new TextField();
    private Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
    private CustomerForm customerForm = new CustomerForm(this);
    Button addCustomerBtn = new Button("Add new customer");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        final VerticalLayout layout = new VerticalLayout();

        CssLayout filterLayout = new CssLayout();
        filterLayout.addComponents(filterText, clearFilterTextBtn);
        filterLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        HorizontalLayout toolbar = new HorizontalLayout(filterLayout, addCustomerBtn);
        toolbar.setSpacing(true);

        HorizontalLayout dataLayout = new HorizontalLayout(grid, customerForm);
        dataLayout.setSpacing(true);
        dataLayout.setSizeFull();
        grid.setSizeFull();
        dataLayout.setExpandRatio(grid, 1);

        filterText.setInputPrompt("filter by name...");
        filterText.addTextChangeListener((event) -> {
            String text = event.getText();
            List<Customer> customers = service.findAll(text);
            grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
        });

        addCustomerBtn.addClickListener((event) -> {
            grid.select(null);
            customerForm.setCustomer(new Customer());
        });

        clearFilterTextBtn.setDescription("Clear the current filter");
        clearFilterTextBtn.addClickListener((event) -> {
            filterText.clear();
            updateList();
        });

        grid.setColumns("firstName", "lastName", "email");
        layout.addComponents(toolbar, dataLayout);

        updateList();

        layout.setMargin(true);
        setContent(layout);

        customerForm.setVisible(false);

        grid.addSelectionListener(event -> {
            if (event.getSelected().isEmpty()) {
                customerForm.setVisible(false);
            } else {
                Customer customer = (Customer) event.getSelected().iterator().next();
                customerForm.setCustomer(customer);
            }
        });
    }

    public void updateList() {

        List<Customer> customers = service.findAll();
        grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
