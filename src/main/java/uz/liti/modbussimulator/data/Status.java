package uz.liti.modbussimulator.data;

import lombok.Builder;
import uz.liti.modbussimulator.backend.entity.AbstractEntity;

import javax.persistence.Entity;

@Entity

public class Status extends AbstractEntity {

    private String name;

    public Status() { }

    @Builder
    public Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
