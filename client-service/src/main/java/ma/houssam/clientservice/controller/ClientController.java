package ma.houssam.clientservice.controller;


import ma.houssam.clientservice.dto.ClientResponse;
import ma.houssam.clientservice.entities.Client;
import ma.houssam.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> clients(){return clientService.findAll();}
    @GetMapping("/{id}")
    public ClientResponse client(@PathVariable("id") Long id){return clientService.findById(id);}
    @PostMapping
    public void save(@RequestBody Client client){clientService.save(client);}
    @GetMapping("/power/{id}/{userid}")
    public void power(@PathVariable("id") Long id, @PathVariable("userid") Long userid){clientService.power(id,userid);}
    @DeleteMapping("/trash/{id}/{userid}")
    public void trash(@PathVariable("id") Long id,@PathVariable("userid") Long userid){clientService.trash(id,userid);}
    @GetMapping("/affect/{id}/{userid}")
    public void affect(@PathVariable("id") Long id, @PathVariable("userid") Long userid){clientService.affect(id,userid);}
    @PutMapping("/fuel/{id}/{liters}")
    public void fuel(@PathVariable("id") Long id,@PathVariable("liters") Float liters){clientService.fuel(id,liters);}
}
