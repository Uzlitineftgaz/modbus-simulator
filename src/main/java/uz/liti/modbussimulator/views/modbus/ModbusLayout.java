package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
//import uz.liti.modbussimulator.views.list.ListView;

public class ModbusLayout extends AppLayout {


    public ModbusLayout() {
        createHeader();
        createDrawer();
    }

    public void createHeader() {

        H1 logo=new H1("Modbus simulator");
        logo.addClassNames("text-1","m-m");

        HorizontalLayout header=new HorizontalLayout(
                new DrawerToggle(),
                logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames("py-0","px-m");

        addToNavbar(header);

    }


    private void createDrawer(){
        RouterLink listLink=new RouterLink("Servers", ServerUI.class);
        RouterLink listLink2=new RouterLink("Clients", ClientUI.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink,listLink2));
    }
}
