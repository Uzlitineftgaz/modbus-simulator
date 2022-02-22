package uz.liti.modbussimulator.views.list;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uz.liti.modbussimulator.data.Contact;
import uz.liti.modbussimulator.service.CrmService;
import uz.liti.modbussimulator.views.MainLayout;

import javax.annotation.security.PermitAll;
import java.util.Collections;

//@Component
@Component
@Scope("prototype")
@Route(value="",layout = MainLayout.class)
@PageTitle("Contacts | Vaadin CRM")
@PermitAll
public class ListView extends VerticalLayout {
    Grid<Contact> grid=new Grid<>(Contact.class);
    TextField filterText=new TextField();
    ContactForm form;
    private final CrmService service;

    public ListView(CrmService service){
        this.service=service;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(),getContent());
        updateList();
        closeEditor();
    }

    private void closeEditor(){
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllContact(filterText.getValue()));
    }


    private HorizontalLayout getToolbar(){
        filterText.setPlaceholder("Filtered by name. . .");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton=new Button("Add contact");
        addContactButton.addClickListener(e->addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addContact(){
        grid.asSingleSelect().clear();
        editContact(new Contact());
    }

    private HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2,grid);
        content.setFlexGrow(1,form);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.setColumns("firstName","lastName","email");
        grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col->col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(e->editContact(e.getValue()));
    }

    private void editContact(Contact contact){
        if (contact==null){
            closeEditor();
        }
        else {
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void configureForm(){
        form=new ContactForm(service.getAllCompanies(),service.getAllStatuses());
        form.setWidth("25em");
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());

    }

    private void saveContact(ContactForm.SaveEvent event) {
        service.saveContact(event.getContact());
        updateList();
        closeEditor();
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        service.deleteContact(event.getContact());
        updateList();
        closeEditor();
    }

}
