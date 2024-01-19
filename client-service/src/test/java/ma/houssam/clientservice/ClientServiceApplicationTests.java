package ma.houssam.clientservice;


import ma.houssam.clientservice.service.ClientService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest // Specify the main Spring Boot application class

class ClientServiceApplicationTests {
    @Autowired
    private ClientService clientService;
    @Test
    void contextLoads() {
        assertNotNull(clientService);
    }
}
