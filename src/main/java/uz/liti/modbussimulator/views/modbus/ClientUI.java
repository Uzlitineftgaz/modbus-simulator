package uz.liti.modbussimulator.views.modbus;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Router;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.ClientItem;
import uz.liti.modbussimulator.repository.ClientRepository;
import uz.liti.modbussimulator.service.ClientService;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@Route(value="/client",layout = ModbusLayout.class)
@PageTitle("Modbus clients")
@PermitAll
//@EnableScheduling
public class ClientUI extends VerticalLayout {

    private final ClientService clientService;

    private final ClientRepository clientRepository;



    Grid<Client> gridClient=new Grid<>(Client.class);
    Grid<ClientItem> item=new Grid<>(ClientItem.class);

    TextField id=new TextField("id");
    TextField name=new TextField("Name");
    TextField ipAddress=new TextField("Ip Address");
    NumberField port=new NumberField("Port");
    NumberField timeOut=new NumberField("Time out");
    Dialog dialog = new Dialog();

    ItemUI itemUI=new ItemUI();

    Button back=new Button("Back");



    Binder<Client> clientBinder=new BeanValidationBinder<>(Client.class);


    Button saveButton = new Button("Save");
    Button addClientButton=new Button("Add client");

    public ClientUI(ClientService clientService, ClientRepository clientRepository
//            , ItemUI
                    ) {
        this.clientService=clientService;
        this.clientRepository=clientRepository;

//        this.itemUI = itemUI;

        configureGrid();

    }


    public void updateClientList(){
        gridClient.setItems(clientRepository.findAll());
    }

    public HorizontalLayout getAddClientForm(String title){

        dialog.getElement().setAttribute("aria-label", title);

        VerticalLayout dialogLayout = createDialogLayout(dialog,title);
        dialog.add(dialogLayout);

        addClientButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        addClientButton.addClickListener(e -> dialog.open());
        HorizontalLayout layout=new HorizontalLayout(addClientButton,dialog);
        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        return layout;
    }

    public HorizontalLayout getClientList(){



        return new HorizontalLayout(gridClient);
    }


//    @Scheduled(fixedRate = 500)
//    public void update(){
////        if (itemUI!=null&&itemUI.client!=null){
////            itemUI.refresh();
////        }
//    }

    private void configureGrid() {
        addClassName("list-view");
        setSizeFull();


        HorizontalLayout layout = new HorizontalLayout(gridClient);
        layout.setSizeFull();
        add(getAddClientForm("Add modbus client"),layout);
        gridClient.addClassName("client-grid");
        gridClient.setSizeFull();
//        gridClient.setWidthFull();
//        gridClient.setHeightFull();
        gridClient.setColumns("id","name","ip","port","connectionTimeout");

        gridClient.getColumns().forEach(col->col.setAutoWidth(true));


        NativeButtonRenderer<Object> edit = new NativeButtonRenderer<>("Edit", clickedItem -> {
        });

        gridClient.addColumn(new NativeButtonRenderer<>("Edit", this::editForm)).setHeader("Edit");
        gridClient.addColumn(new NativeButtonRenderer<>("Items", this::getItems)).setHeader("Items");
        gridClient.addColumn(new NativeButtonRenderer<>("Delete", clickedItem -> {})).setHeader("Delete");
//        gridClient.addColumn(new NativeButtonRenderer<>("Delete", clickedItem -> {})).setHeader("Delete");
        updateClientList();
//        gridClient.asSingleSelect().addValueChangeListener(e->editContact(e.getValue()));
    }

    private void getItems(Client client) {
        removeAll();
        back.addClickListener(e->{
            removeAll();
            dialog.removeAll();
            configureGrid();
        });
        itemUI = new ItemUI(clientService, clientRepository, client,back);

        add(itemUI);
    }


    public VerticalLayout createDialogLayout(Dialog dialog, String title) {
        H2 headline = new H2(title);
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        VerticalLayout fieldLayout = new VerticalLayout(name,ipAddress,port,timeOut);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> clearAndClose(dialog));
        saveButton.addClickListener(e->{
            addClient();
            clearAndClose(dialog);
        });
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }


    public void clearAndClose(Dialog dialog){
        name.clear();
        ipAddress.clear();
        port.clear();
        timeOut.clear();
        id.clear();
        dialog.close();
    }

    public void addClient(){
        System.out.println();
        System.out.println(name.getValue());
        Client client = Client
                .builder()
                .ip(ipAddress.getValue())
                .name(name.getValue())
                .port(port.getValue().intValue())
                .connectionTimeout(timeOut.getValue().intValue())
                .ip(ipAddress.getValue())
                .build();

        if (id.getValue()!=null&&!id.getValue().isEmpty()) client.setId(Long.valueOf(id.getValue()));

        System.out.println(client);

        Notification notification=new Notification();
        notification.setDuration(1000);
        if (clientService.save(client)){
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setText("Saved");
            notification.open();
            updateClientList();
        }
        else {
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Error");
            notification.open();
        }
    }



    public void editForm(Client client){





        id.setValue(String.valueOf(client.getId()));

        name.setValue(client.getName());
        ipAddress.setValue(client.getIp());
        port.setValue((double) client.getPort());
        timeOut.setValue((double) client.getConnectionTimeout());

//        getAddClientForm("Edit modbus client");
        dialog.open();


//        System.out.println();
//        System.out.println(name.getValue());
//
//
//        Notification notification=new Notification();
//        notification.setDuration(1000);
//        if (clientService.save(client)){
//            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
//            notification.setText("Saved");
//            notification.open();
//            updateClientList();
//        }
//        else {
//            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//            notification.setText("Error");
//            notification.open();
//        }
    }





}
