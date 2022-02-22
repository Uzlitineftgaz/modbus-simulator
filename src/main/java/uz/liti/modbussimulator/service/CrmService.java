package uz.liti.modbussimulator.service;

import org.springframework.stereotype.Service;
import uz.liti.modbussimulator.data.Company;
import uz.liti.modbussimulator.data.Contact;
import uz.liti.modbussimulator.data.Status;
import uz.liti.modbussimulator.repository.CompanyRepo;
import uz.liti.modbussimulator.repository.ContactRepo;
import uz.liti.modbussimulator.repository.StatusRepo;

import java.util.List;

@Service
public class CrmService {
    private final CompanyRepo companyRepo;
    private final ContactRepo contactRepo;
    private final StatusRepo statusRepo;

    public CrmService(CompanyRepo companyRepo, ContactRepo contactRepo, StatusRepo statusRepo) {
        this.companyRepo = companyRepo;
        this.contactRepo = contactRepo;
        this.statusRepo = statusRepo;
    }

    public List<Contact> findAllContact(String filterText){
        if (filterText==null||filterText.isEmpty()){
            return contactRepo.findAll();
        }else {
            return contactRepo.search(filterText);
        }
    }

    public long countContacts(){
        return contactRepo.count();
    }
    public void deleteContact(Contact contact){
        contactRepo.delete(contact);
    }

    public void saveContact(Contact contact){
        if (contact==null){
            System.err.println("Contact is null");
            return;
        }
        contactRepo.save(contact);
    }

    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    public List<Status> getAllStatuses(){
        return statusRepo.findAll();
    }
}
