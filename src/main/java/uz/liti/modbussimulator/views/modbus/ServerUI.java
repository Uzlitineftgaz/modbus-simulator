package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@Route(value="/server",layout = ModbusLayout.class)
@PageTitle("Modbus servers")
@PermitAll
public class ServerUI extends VerticalLayout {

    public ServerUI() {
        add(new H1("SERVER"));

    }
}
