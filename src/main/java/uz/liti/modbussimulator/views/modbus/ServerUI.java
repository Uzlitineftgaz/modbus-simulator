package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uz.liti.modbussimulator.model.Server;
import uz.liti.modbussimulator.repository.ServerRepository;
import uz.liti.modbussimulator.service.ServerService;

import javax.annotation.security.PermitAll;
import java.util.Arrays;

@Component
@Scope("prototype")
@Route(value="",layout = ModbusLayout.class)
@PageTitle("Modbus servers")
@PermitAll
public class ServerUI extends VerticalLayout {
    private final ServerService service;

    private final ServerRepository serverRepository;



    RegisterUI registerUI;

    Grid<Server> gridServer=new Grid<>(Server.class);

    TextField id=new TextField("id");
    TextField name=new TextField("Name");
    NumberField port=new NumberField("Port");
//    NumberField timeOut=new NumberField("Time out");
    Dialog dialog = new Dialog();

//    NumberField integerSelect=new NumberField();
//    NumberField registerValue=new NumberField("value");
//
//
//
//    Binder<Client> clientBinder=new BeanValidationBinder<>(Client.class);


    Button saveButton = new Button("Save");
    Button addClientButton=new Button("Add server");

    public ServerUI(
//            , ItemUI
            ServerService service, ServerRepository serverRepository) {
        this.service = service;
        this.serverRepository = serverRepository;


//        this.itemUI = itemUI;

        configureGrid();
        closeEditor();


//        add(new H1("Servers"));
    }


    public void updateClientList(){
        gridServer.setItems(serverRepository.findAll());
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

    public HorizontalLayout getServerList(){



        return new HorizontalLayout(gridServer);
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

        registerUI=new RegisterUI(service);

//        HorizontalLayout layout = new HorizontalLayout(gridServer);
//        layout.setSizeFull();
        gridServer.addClassName("server-grid");
        gridServer.setSizeFull();
//        gridServer.setWidthFull();
//        gridClient.setHeightFull();
        gridServer.setColumns("id","name","port");

        gridServer.getColumns().forEach(col->col.setAutoWidth(true));

        gridServer.asSingleSelect().addValueChangeListener(e->viewRegisters(e.getValue()));

        NativeButtonRenderer<Object> edit = new NativeButtonRenderer<>("Edit", clickedItem -> {
        });

        gridServer.addColumn(new NativeButtonRenderer<>("Edit", this::editForm)).setHeader("Edit");
//        gridServer.addColumn(new NativeButtonRenderer<>("Edit", this::addRegisterValue)).setHeader("values");
        gridServer.addColumn(new NativeButtonRenderer<>("Delete", clickedItem -> {})).setHeader("Delete");
        add(getAddClientForm("Add modbus server"),getContent());

//        gridClient.addColumn(new NativeButtonRenderer<>("Delete", clickedItem -> {})).setHeader("Delete");
        updateClientList();
//        gridClient.asSingleSelect().addValueChangeListener(e->editContact(e.getValue()));
    }




    private HorizontalLayout getContent(){
        HorizontalLayout content = new HorizontalLayout(gridServer, registerUI);
        content.setFlexGrow(2,gridServer);
        content.setFlexGrow(1,registerUI);
        content.addClassName("content");
        content.setSizeFull();
        return content;
    }

    private void viewRegisters(Server server) {
        if (server==null){
            closeEditor();
        }
        else {
            registerUI.setServer(server);
            registerUI.setVisible(true);
            addClassName("editing");
        }
    }
    private void closeEditor(){
//        registerUI.setContact(null);
        registerUI.setVisible(false);
        removeClassName("editing");
    }


    public VerticalLayout createDialogLayout(Dialog dialog, String title) {
        dialog.removeAll();
        H2 headline = new H2(title);
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

//        integerSelect.setAutofocus(true);
//        integerSelect.setLabel("Register (0-65534) ");
//
//        integerSelect.addValueChangeListener(ch->{
//            registerValue.setValue();
//        });
//
//        int[] array=new int[65535];

//        for (int i = 0; i <65535 ; i++) {
//            array[i]=i;
//        }
//        integerSelect.setItems(Arrays.stream(array).boxed());
//


        VerticalLayout fieldLayout = new VerticalLayout(name,port);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> clearAndClose(dialog));
        saveButton.addClickListener(e->{
//            addClient();
            addServer();
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
        port.clear();
        id.clear();
        dialog.close();
//        dialog.removeAll();
    }

    public void addServer(){
        System.out.println();
        System.out.println(name.getValue());
        Server server = Server
                .builder()
                .name(name.getValue())
                .port(port.getValue().intValue())
                .build();

        if (id.getValue()!=null&&!id.getValue().isEmpty()) server.setId(Long.valueOf(id.getValue()));

        System.out.println(server);

        Notification notification=new Notification();
        notification.setDuration(1000);
        if (service.save(server)){
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



    public void editForm(Server server){
        id.setValue(String.valueOf(server.getId()));
        name.setValue(server.getName());
        port.setValue((double) server.getPort());
        dialog.open();
    }


//    public void addRegisterValue(Server server){
//        dialog.removeAll();
//
//
//
//
//        integerSelect.setAutofocus(true);
//        integerSelect.setLabel("Register (0-65534) ");
//
//        integerSelect.addValueChangeListener(ch->{
//            registerValue.setValue((double) server.getModbusServer().inputRegisters[ch.getValue().intValue()]);
//        });
////
////        int[] array=new int[65535];
////
////        for (int i = 0; i <65535 ; i++) {
////            array[i]=i;
////        }
////        integerSelect.setItems(Arrays.stream(array).boxed());
//
//        H2 headline = new H2("Register value add");
//        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
//                .set("font-size", "1.5em").set("font-weight", "bold");
//
//
//        VerticalLayout fieldLayout = new VerticalLayout(integerSelect,registerValue);
//        fieldLayout.setSpacing(false);
//        fieldLayout.setPadding(false);
//        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
//
//        Button cancelButton = new Button("Cancel", e -> clearAndClose(dialog));
//        saveButton.addClickListener(e->{
////            addClient();
//            addServer();
//            clearAndClose(dialog);
//        });
//        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
//                saveButton);
//        buttonLayout
//                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
//
//
//
//
//
//        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
//                buttonLayout);
//        dialogLayout.setPadding(false);
//        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
//        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");
//
//        dialog.getElement().setAttribute("aria-label", "value add");
//
//        dialog.add(dialogLayout);
//
//        addClientButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
//        addClientButton.addClickListener(e -> dialog.open());
//        HorizontalLayout layout=new HorizontalLayout(addClientButton,dialog);
//        layout.setDefaultVerticalComponentAlignment(Alignment.END);
//        add(layout);
//    }


}
