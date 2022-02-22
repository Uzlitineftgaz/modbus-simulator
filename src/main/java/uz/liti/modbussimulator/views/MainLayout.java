package uz.liti.modbussimulator.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import uz.liti.modbussimulator.views.list.ListView;

//@Theme(themeFolder = "flowcrmtutorial")
public class MainLayout extends AppLayout {


    public MainLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader(){
        H1 logo=new H1("Dash");
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
        RouterLink listLink=new RouterLink("List", ListView.class);
        RouterLink listLink2=new RouterLink("List", ListView.class);

        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink,listLink2));
    }

}
