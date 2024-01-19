package ma.houssam.carservice.service;

import ma.houssam.carservice.entities.Car;
import ma.houssam.carservice.entities.FuelType;
import ma.houssam.carservice.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void cars() {
        // Créer une liste fictive de voitures
        List<Car> mockCars = Arrays.asList(
                Car.builder()
                        .brand("BMW")
                        .consumptionPerKm(9F)
                        .description("BMW Serie 2")
                        .fuelType(FuelType.ESSENCE)
                        .picture("https://www.bmw.ma/content/dam/bmw/common/all-models/2-series/series-overview/bmw-2-series-overview-page-ms-coupe.jpg/jcr:content/renditions/cq5dam.resized.img.585.low.time1627455118737.jpg")
                        .tankSize(50F)
                        .model("Serie 2")
                        .build(),
                Car.builder()
                        .brand("BMW")
                        .consumptionPerKm(9F)
                        .description("BMW Serie 2")
                        .fuelType(FuelType.ESSENCE)
                        .picture("https://www.bmw.ma/content/dam/bmw/common/all-models/2-series/series-overview/bmw-2-series-overview-page-ms-coupe.jpg/jcr:content/renditions/cq5dam.resized.img.585.low.time1627455118737.jpg")
                        .tankSize(50F)
                        .model("Serie 2")
                        .build()

        );

        // Définir le comportement du repository mock
        Mockito.when(carRepository.findAll()).thenReturn(mockCars);

        // Appeler la méthode à tester
        List<Car> result = carService.cars();

        // Vérifier que la méthode du repository mock a été appelée
        verify(carRepository, times(1)).findAll();

        // Assertions pour vérifier le résultat
        assertEquals(2, result.size());
        assertEquals("Serie 2", result.get(0).getModel());
        assertEquals("Serie 2", result.get(1).getModel());

    }

    @Test
    void car() {
        // Créer une voiture fictive
        Car mockCar = Car.builder()
                .brand("BMW")
                .consumptionPerKm(9F)
                .description("BMW Serie 2")
                .fuelType(FuelType.ESSENCE)
                .picture("https://www.bmw.ma/content/dam/bmw/common/all-models/2-series/series-overview/bmw-2-series-overview-page-ms-coupe.jpg/jcr:content/renditions/cq5dam.resized.img.585.low.time1627455118737.jpg")
                .tankSize(50F)
                .model("Serie 2")
                .build();

        // Définir le comportement du repository mock
        when(carRepository.findById(1L)).thenReturn(Optional.of(mockCar));

        // Appeler la méthode à tester
        Car result = carService.car(1L);

        // Vérifier que la méthode du repository mock a été appelée
        verify(carRepository, times(1)).findById(1L);

        // Assertions pour vérifier le résultat
        assertEquals("Serie 2", result.getModel());

    }

    @Test
    void save() {
        // Créer une voiture fictive à sauvegarder
        Car carToSave = Car.builder()
                .brand("BMW")
                .consumptionPerKm(9F)
                .description("BMW Serie 2")
                .fuelType(FuelType.ESSENCE)
                .picture("https://www.bmw.ma/content/dam/bmw/common/all-models/2-series/series-overview/bmw-2-series-overview-page-ms-coupe.jpg/jcr:content/renditions/cq5dam.resized.img.585.low.time1627455118737.jpg")
                .tankSize(50F)
                .model("Serie 2")
                .build();

        // Appeler la méthode à tester
        carService.save(carToSave);

        // Vérifier que la méthode du repository mock a été appelée avec la voiture à sauvegarder
        verify(carRepository, times(1)).save(carToSave);
    }
}