package ma.houssam.clientservice.service;

import ma.houssam.clientservice.dto.ClientResponse;
import ma.houssam.clientservice.entities.Car;
import ma.houssam.clientservice.entities.Client;
import ma.houssam.clientservice.external.CarRestClient;
import ma.houssam.clientservice.model.CarModel;
import ma.houssam.clientservice.repository.CarRepository;
import ma.houssam.clientservice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ClientServiceTest {


    @Mock
    private ClientRepository clientRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarRestClient carRestClient;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void save() {
        // Créer un client fictif
        Client mockClient = new Client(); // Assurez-vous de créer un objet Client valide

        // Appeler la méthode à tester
        clientService.save(mockClient);

        // Vérifier que la méthode du repository mock a été appelée
        verify(clientRepository, times(1)).save(mockClient);
    }

    @Test
    void power() {
        // Créer un client fictif avec une liste de voitures fictives
        Client mockClient = new Client();
        List<Car> mockCars = Arrays.asList(new Car(), new Car()); // Assurez-vous de créer des objets Car valides
        mockClient.setCar_ids(mockCars);

        // Définir le comportement du repository mock
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(mockClient));

        // Définir le comportement du carRestClient mock
        when(carRestClient.car(anyLong())).thenReturn(new CarModel()); // Assurez-vous de créer un objet CarModel valide

        // Appeler la méthode à tester
        clientService.power(1L,4L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(clientRepository, times(1)).save(mockClient);
    }

    @Test
    void affect() {
        // Créer un client fictif sans voiture
        Client mockClient = new Client();

        // Définir le comportement du repository mock
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(mockClient));

        // Définir le comportement du carRestClient mock
        when(carRestClient.car(anyLong())).thenReturn(new CarModel()); // Assurez-vous de créer un objet CarModel valide

        // Appeler la méthode à tester
        clientService.affect(1L,4L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(clientRepository, times(1)).save(mockClient);
    }

    @Test
    void findAll() {
        // Créer une liste fictive de clients
        List<Client> mockClients = new ArrayList<>();

        // Définir le comportement du repository mock
        when(clientRepository.findAll()).thenReturn(mockClients);

        // Appeler la méthode à tester
        List<Client> result = clientService.findAll();

        // Vérifier que la méthode du repository mock a été appelée
        verify(clientRepository, times(1)).findAll();

        // Assertions pour vérifier le résultat
        assertEquals(mockClients, result);
    }

    // Ajouter des tests pour les autres méthodes du service (findById, save, power, trash, affect, fuel)...
    // Les tests suivront une structure similaire à celui ci-dessus.

    // Exemple pour findById
    @Test
    void findById() {
        // Créer un client fictif
        Client mockClient = new Client(); // Assurez-vous de créer un objet Client valide

        // Définir le comportement du repository mock
        when(clientRepository.findById(1L)).thenReturn(Optional.of(mockClient));

        // Définir le comportement du clientRestClient mock
        when(carRestClient.car(anyLong())).thenReturn(new CarModel()); // Assurez-vous de créer un objet CarModel valide

        // Appeler la méthode à tester
        ClientResponse result = clientService.findById(1L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(clientRepository, times(1)).findById(1L);

        // Assertions pour vérifier le résultat
        // Ajoutez des assertions pour vérifier les propriétés de ClientResponse en fonction de votre implémentation
    }
}