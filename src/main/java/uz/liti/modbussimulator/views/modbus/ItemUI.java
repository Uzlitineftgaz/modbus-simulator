package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
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
import com.vaadin.flow.router.RouterLink;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.ClientItem;
import uz.liti.modbussimulator.repository.ClientRepository;
import uz.liti.modbussimulator.service.ClientService;

import javax.annotation.security.PermitAll;

//@Component
//@Scope("prototype")
//@Route(value="/items",layout = ModbusLayout.class)
//@PageTitle("Modbus client items")
//@PermitAll
//@EnableScheduling
@NoArgsConstructor
//@Getter
@EnableAsync
public class ItemUI extends VerticalLayout {

//    private final ClientService clientService;
//
//    private final ClientRepository clientRepository;

    Grid<ClientItem> itemGrid=new Grid<>(ClientItem.class);

    private Client client;
    TextField id=new TextField("id");
    TextField name=new TextField("Name");
    NumberField address=new NumberField("Address");
    NumberField quantity=new NumberField("Quantity");
    Dialog dialog = new Dialog();


    Button refresh=new Button("Refresh");
    Button back;

    Button saveItemButton = new Button("Add item");
    Button saveButton = new Button("Save");
    Button connectButton=new Button();


    private ClientService clientService;


    public ItemUI(ClientService clientService, ClientRepository clientRepository,Client client,Button back) {
        this.back=back;
//        back.addClickListener(e->removeAll());
        this.clientService=clientService;
        itemGrid.setItems(clientService.findAllItemByClient(client));
        configureGrid();
        HorizontalLayout layout = new HorizontalLayout(itemGrid);
        layout.setSizeFull();

        Paragraph paragraph=new Paragraph(client.getName());

        if (client.isActive()){
            connectButton.setText("Yoniq");
            connectButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        }else {
            connectButton.setText("O'chiq");
            connectButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        }

        VerticalLayout dialogLayout = createDialogLayout(dialog,"Item qo'shish");
        dialog.add(dialogLayout);
        saveItemButton.addClickListener(e->{
            dialog.open();
        });

        this.client=client;


        HorizontalLayout backrefresh = new HorizontalLayout();
        HorizontalLayout backrefresh2 = new HorizontalLayout();
        backrefresh.add(back,refresh);

        refresh.addClickListener(e->updateItemList());


        backrefresh2.add(paragraph,saveItemButton,connectButton);
        saveItemButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(new HorizontalLayout(backrefresh,paragraph,saveItemButton,connectButton),layout);

    }


    public VerticalLayout createDialogLayout(Dialog dialog, String title) {
        H2 headline = new H2(title);
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");

        VerticalLayout fieldLayout = new VerticalLayout(name,address,quantity);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> clearAndClose(dialog));
        saveButton.addClickListener(e->{
            addItem();
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

    @Async
    private void addItem() {
        System.out.println();
        System.out.println(name.getValue());
        ClientItem item = ClientItem
                .builder()
                .client(client)
                .name(name.getValue())
                .quantity(quantity.getValue().intValue())
                .address(address.getValue().intValue())
                .build();

        if (id.getValue()!=null&&!id.getValue().isEmpty()) item.setId(Long.valueOf(id.getValue()));

        System.out.println(item);

        Notification notification=new Notification();
        notification.setDuration(1000);
        if (clientService.saveItem(item)){
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setText("Saved");
            notification.open();
            updateItemList();
        }
        else {
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Error");
            notification.open();
        }
    }


//    @Scheduled(fixedDelay = 500)
//    public void update(){
//        System.out.println("Tashqi");
//        if (client!=null&&itemGrid.getPageSize()>0){
//            System.out.println("Ichki");
//
//            itemGrid.setItems(clientService.findAllItemByClient(client));
//        }
//    }

    public void updateItemList(){
        itemGrid.setItems(clientService.findAllItemByClient(client));
    }



    public void clearAndClose(Dialog dialog){
        name.clear();
        address.clear();
        quantity.clear();
        id.clear();
        dialog.close();
    }


    private void configureGrid() {
        itemGrid.addClassName("item-grid");
//        itemGrid.setSizeFull();
//        gridClient.setWidthFull();
//        gridClient.setHeightFull();
//        itemGrid.setColumns("id","name","address","quantity","float high low");

        itemGrid.getColumns().forEach(col->col.setAutoWidth(true));


        NativeButtonRenderer<Object> edit = new NativeButtonRenderer<>("Edit", clickedItem -> {
        });

//        gridClient.addColumn(new NativeButtonRenderer<>("Edit", this::editForm)).setHeader("Edit");
//        gridClient.addColumn(new NativeButtonRenderer<>("Items", this::getItems)).setHeader("Items");
//        gridClient.addColumn(new NativeButtonRenderer<>("Delete", clickedItem -> {})).setHeader("Delete");
//        updateClientList();
    }


}
