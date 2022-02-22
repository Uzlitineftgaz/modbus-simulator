package uz.liti.modbussimulator.data;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Formula;
import uz.liti.modbussimulator.backend.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Company extends AbstractEntity {

    @Builder
    public Company(String name, List<Contact> employees, int employeeCount) {
        this.name = name;
        this.employees = employees;
        this.employeeCount = employeeCount;
    }

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Contact> employees = new LinkedList<>();

    @Formula("(select count(c.id) from Contact c where c.company_id = id)")
    private int employeeCount;

    public Company() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Contact> employees) {
        this.employees = employees;
    }

    public int getEmployeeCount(){
        return employeeCount;
    }
}
