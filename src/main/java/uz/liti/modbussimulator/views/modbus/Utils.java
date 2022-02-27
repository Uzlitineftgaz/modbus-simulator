package uz.liti.modbussimulator.views.modbus;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class Utils {










    public static void notificationSuccess(){
        notificationSuccess(1000,"Success");
    }

    public static void notificationSuccess(String text){
        notificationSuccess(1000,text);
    }

    public static void notificationSuccess(int duration){
        notificationSuccess(duration,"Success");
    }

    public static void notificationSuccess(String text, Notification.Position position){
        notificationSuccess(1000,text,position);
    }

    public static void notificationSuccess(int duration, String text){
        notification(duration,text,NotificationVariant.LUMO_SUCCESS);
    }

    public static void notificationSuccess(int duration, String text, Notification.Position position){
        notification(duration,text,position,NotificationVariant.LUMO_SUCCESS);
    }








    public static void notificationError(){
        notificationError(1000,"Error");
    }

    public static void notificationError(String text){
        notificationError(1000,text);
    }

    public static void notificationError(int duration){
        notificationError(duration,"Error");
    }

    public static void notificationError(String text, Notification.Position position){
        notificationError(1000,text,position);
    }

    public static void notificationError(int duration, String text){
        notification(duration,text,NotificationVariant.LUMO_ERROR);
    }

    public static void notificationError(int duration, String text, Notification.Position position){
        notification(duration,text,position,NotificationVariant.LUMO_ERROR);
    }








    public static void notification(int duration,
                                    String text,
                                    Notification.Position position,
                                    NotificationVariant variant){
        Notification notification=new Notification();
        notification.setDuration(duration);
        notification.setText(text);
        notification.setPosition(position);
        notification.addThemeVariants(variant);
        notification.open();
    }

    public static void notification(String text,
                                    Notification.Position position,
                                    NotificationVariant variant){
        Notification notification=new Notification();
        notification.setText(text);
        notification.setPosition(position);
        notification.addThemeVariants(variant);
        notification.open();
    }

    public static void notification(int duration,
                                    String text,
                                    NotificationVariant variant){
        Notification notification=new Notification();
        notification.setDuration(duration);
        notification.setText(text);
        notification.addThemeVariants(variant);
        notification.open();
    }



}
