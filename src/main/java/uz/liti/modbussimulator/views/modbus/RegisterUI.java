package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import uz.liti.modbussimulator.model.Server;
import uz.liti.modbussimulator.service.ServerService;

public class RegisterUI extends VerticalLayout {
    private final ServerService service;



    public RegisterUI(ServerService service) {
        this.service = service;
        add(new H1("aada"));
    }

    public void setServer(Server server) {
    }
}
