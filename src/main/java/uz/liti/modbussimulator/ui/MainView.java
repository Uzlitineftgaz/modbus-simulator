//package uz.liti.modbussimulator.ui;
//
//import com.vaadin.flow.component.Component;
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.html.H1;
//import com.vaadin.flow.component.html.Paragraph;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.component.tabs.Tab;
//import com.vaadin.flow.router.Route;
//
//@Route("")
//public class MainView extends VerticalLayout {
//
//
//    public MainView(){
//
//        Button button=new Button("Click me");
//        DatePicker datePicker = new DatePicker("Picker");
//
//
//        HorizontalLayout layout = new HorizontalLayout(button, datePicker);
//        layout.setDefaultVerticalComponentAlignment(Alignment.END);
//        add(layout);
//
//        button.addClickListener(click->add(new Paragraph("Clicked: "+datePicker.getValue())));
//
//    }
//
//
//
//}
