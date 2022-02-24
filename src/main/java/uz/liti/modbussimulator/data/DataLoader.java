//package uz.liti.modbussimulator.data;
////lord
//
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//
//import org.springframework.stereotype.Component;
//import uz.liti.modbussimulator.model.Client;
//import uz.liti.modbussimulator.repository.ClientRepository;
//import uz.liti.modbussimulator.repository.CompanyRepo;
//import uz.liti.modbussimulator.repository.ContactRepo;
//import uz.liti.modbussimulator.repository.StatusRepo;
//
//import java.util.Collections;
//
//@Component
//@NoArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//
//
//    @Autowired
//    private CompanyRepo companyRepo;
//    @Autowired
//    private StatusRepo statusRepo;
//    @Autowired
//    private ContactRepo contactRepo;
//    @Autowired
//    private ClientRepository clientRepository;
//
//
//
//    @Override
//    public void run(String... args) throws Exception {
////        if (mode.equals("always")) {
//        try {
//
//            Status alkash = statusRepo.save(new Status("A"));
//            Status garang = statusRepo.save(new Status("B"));
//
//            Company company = companyRepo.save(Company.builder().name("Company").build());
//            contactRepo.save(Contact.builder().firstName("Mahmud").company(company).lastName("Salomov").email("aaaa@gmail.com").status(alkash).build());
//
//            clientRepository.save(Client.builder().port(502).connectionTimeout(500).name("Test").ip("10.10.24.50").build());
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//    }
//}
//
//
