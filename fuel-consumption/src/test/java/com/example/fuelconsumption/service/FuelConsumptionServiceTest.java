package com.example.fuelconsumption.service;

import com.example.fuelconsumption.entities.FuelConsumption;
import com.example.fuelconsumption.external.CarRestClient;
import com.example.fuelconsumption.external.ClientRestClient;
import com.example.fuelconsumption.model.Car;
import com.example.fuelconsumption.model.Client;
import com.example.fuelconsumption.repository.FuelConsumptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FuelConsumptionServiceTest {
    @Mock
    private FuelConsumptionRepository fuelConsumptionRepository;

    @Mock
    private ClientRestClient clientRestClient;

    @Mock
    private CarRestClient carRestClient;

    @InjectMocks
    private FuelConsumptionService fuelConsumptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void fuelConsumptionsByClientId() {
        // Créer une liste fictive de consommations de carburant
        List<FuelConsumption> mockFuelConsumptions = Collections.singletonList(new FuelConsumption());

        // Définir le comportement du repository mock
        when(fuelConsumptionRepository.getFuelConsumptionsByClient_id(anyLong())).thenReturn(mockFuelConsumptions);

        // Appeler la méthode à tester
        List<FuelConsumption> result = fuelConsumptionService.fuelConsumptionsByClientId(1L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(fuelConsumptionRepository, times(1)).getFuelConsumptionsByClient_id(1L);

        // Assertions pour vérifier le résultat
        assertEquals(mockFuelConsumptions, result);
    }

    @Test
    void fuelConsumptions() {
        // Créer une liste fictive de consommations de carburant
        List<FuelConsumption> mockFuelConsumptions = Collections.singletonList(new FuelConsumption());

        // Définir le comportement du repository mock
        when(fuelConsumptionRepository.findAll()).thenReturn(mockFuelConsumptions);

        // Définir le comportement du clientRestClient mock et carRestClient mock
        when(clientRestClient.client(anyLong())).thenReturn(new Client()); // Assurez-vous de créer un objet Client valide
        when(carRestClient.car(anyLong())).thenReturn(new Car()); // Assurez-vous de créer un objet CarModel valide

        // Appeler la méthode à tester
        List<FuelConsumption> result = fuelConsumptionService.fuelConsumptions();

        // Vérifier que la méthode du repository mock a été appelée
        verify(fuelConsumptionRepository, times(1)).findAll();

        // Assertions pour vérifier le résultat
        assertEquals(mockFuelConsumptions, result);
    }

    @Test
    void fuelConsumption() {
        // Créer une consommation de carburant fictive
        FuelConsumption mockFuelConsumption = new FuelConsumption(); // Assurez-vous de créer un objet FuelConsumption valide

        // Définir le comportement du repository mock
        when(fuelConsumptionRepository.findById(anyLong())).thenReturn(Optional.of(mockFuelConsumption));

        // Définir le comportement du clientRestClient mock et carRestClient mock
        when(clientRestClient.client(anyLong())).thenReturn(new Client()); // Assurez-vous de créer un objet Client valide
        when(carRestClient.car(anyLong())).thenReturn(new Car()); // Assurez-vous de créer un objet CarModel valide

        // Appeler la méthode à tester
        FuelConsumption result = fuelConsumptionService.fuelConsumption(1L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(fuelConsumptionRepository, times(1)).findById(1L);

        // Assertions pour vérifier le résultat
        assertEquals(mockFuelConsumption, result);
    }

    @Test
    void save() {
        // Créer une consommation de carburant fictive
        FuelConsumption mockFuelConsumption = new FuelConsumption(); // Assurez-vous de créer un objet FuelConsumption valide

        // Appeler la méthode à tester
        fuelConsumptionService.save(mockFuelConsumption);

        // Assertions pour vérifier le résultat
        assertNotNull(mockFuelConsumption.getCreatedAt());
        // Vous pouvez également ajouter des assertions supplémentaires selon la logique de votre implémentation
    }
}