package uz.liti.modbussimulator.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.liti.modbussimulator.service.ClientService;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final ClientService clientService;

    public RestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public HttpEntity<?> test(){
        System.out.println();
        clientService.test();
        return ResponseEntity.ok("ok");
    }


}
