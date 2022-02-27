package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import uz.liti.modbussimulator.model.Register;
import uz.liti.modbussimulator.model.RegisterType;
import uz.liti.modbussimulator.model.Server;
import uz.liti.modbussimulator.service.ServerService;

//import java.awt.*;

public class RegisterUI extends VerticalLayout {
    private final ServerService service;


//    private Server server;

    Grid<Register> registerGrid=new Grid<>(Register.class);

    Select<RegisterType> select=new Select<>();
    NumberField address=new NumberField("address");
    NumberField value=new NumberField("value");
    Button addButton=new Button("Add register");
    Dialog dialog = new Dialog();
    Server server;

    public RegisterUI(ServerService service) {
        this.service = service;
    }

    public void setServer(Server server) {
        this.server=server;
        removeAll();
        setSizeFull();
        registerGrid.addClassName("server-grid");
//        registerGrid.setSizeFull();
        registerGrid.setItems(service.findAllByServer(server));


        registerGrid.setColumns("id","address","value","floatValue");
        registerGrid.addColumn(new NativeButtonRenderer<>("Edit", this::editForm)).setHeader("Edit");
        registerGrid.getColumns().forEach(col->col.setAutoWidth(true));
        registerGrid.addClassName("register-grid");




//        HorizontalLayout layout = new HorizontalLayout(address,value,addButton);
//        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        add(new VerticalLayout(new HorizontalLayout(new H3("Server: "+server.getName())),getAddRegisterForm("Register qo'shish"),registerGrid));
        updateRegisterList(server);
    }

    private void editForm(Register register) {
        address.setValue(Double.valueOf(register.getAddress()));
        value.setValue(Double.valueOf(register.getValue()));
//        port.setValue((double) server.getPort());
        dialog.open();
    }


    public void updateRegisterList(Server server){
        registerGrid.setItems(service.findAllByServer(server));
//        registerGrid.addColumn(new NativeButtonRenderer<>("Edit", this::editForm)).setHeader("Edit");
    }





    public HorizontalLayout getAddRegisterForm(String title){

        dialog.getElement().setAttribute("aria-label", title);

        VerticalLayout dialogLayout = createDialogLayout(dialog,title);
        dialog.add(dialogLayout);

        addButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        addButton.addClickListener(e -> dialog.open());
        HorizontalLayout layout=new HorizontalLayout(addButton,dialog);
        layout.setDefaultVerticalComponentAlignment(Alignment.END);
        return layout;
    }

    public VerticalLayout createDialogLayout(Dialog dialog, String title) {
        dialog.removeAll();
        H2 headline = new H2(title);
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "1.5em").set("font-weight", "bold");


        select.setLabel("Type");
        select.setItems(RegisterType.INPUT_REGISTER);
        VerticalLayout fieldLayout = new VerticalLayout(address,value,select);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button cancelButton = new Button("Cancel", e -> clearAndClose(dialog));
        Button saveRegisterButton=new Button("Save");
        saveRegisterButton.addClickListener(e->{
//            addClient();
            addRegister();
            clearAndClose(dialog);
        });
        saveRegisterButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton,
                saveRegisterButton);
        buttonLayout
                .setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout,
                buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

    private void addRegister() {
        try {
            System.out.println("AAAAAAAAA");
            Register register = Register
                    .builder()
                    .type(RegisterType.INPUT_REGISTER)
                    .address(address.getValue().intValue())
                    .server(server)
                    .build();
            Register inputRegister = service.saveInputRegister(register, Float.parseFloat(String.valueOf(value.getValue())));
            System.out.println(inputRegister);
            Utils.notificationSuccess();
            updateRegisterList(server);
        }catch (Exception e){
            e.printStackTrace();
            Utils.notificationError();
        }

    }


    public void clearAndClose(Dialog dialog){
        address.clear();
        value.clear();
        dialog.close();
    }








    public static float twoInt16ToFloat(short[] array){
        return Float.intBitsToFloat(twoInt16ToInt32(array));
    }

    public static short[] floatToTwoInt16(float num){
        return int32ToTwoInt16(Float.floatToIntBits(num));
    }

    public static int twoInt16ToInt32(short[] array){
        if (array.length!=2) return 0;
        short high =array[0]; // the high 16 bits
        short low = array[1]; // the low 16 bits
        return  (high << 16) | low;
    }

    public static short[] int32ToTwoInt16(int num){
        short firstHalf = (short) (num >> 16);
        short secondHalf = (short) (num & 0xffff);
        return new short[]{firstHalf,secondHalf};
    }




}
