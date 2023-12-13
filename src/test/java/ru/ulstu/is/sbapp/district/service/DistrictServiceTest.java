package ru.ulstu.is.sbapp.district.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ulstu.is.sbapp.district.model.District;
import ru.ulstu.is.sbapp.district.repository.DistrictRepository;
import ru.ulstu.is.sbapp.district.service.DistrictNotFoundException;
import ru.ulstu.is.sbapp.district.service.DistrictService;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    @Autowired
    private DistrictService districtService;

    @MockBean
    private DistrictRepository districtRepository;

    @Test
    public void testAddDistrict() {
        // Создание тестовых данных
        String description = "Test District";

        // Мокирование поведения репозитория
        District savedDistrict = new District(description);
        Mockito.when(districtRepository.save(Mockito.any(District.class))).thenReturn(savedDistrict);

        // Вызов тестируемого метода
        District result = districtService.addDistrict(description);

        // Проверка результатов
        assertEquals(savedDistrict, result);
    }

    @Test
    public void testFindDistrict() {
        // Создание тестовых данных
        Long id = 1L;
        District district = new District("Test Districtвы");

        // Мокирование поведения репозитория
        Mockito.when(districtRepository.findById(id)).thenReturn(Optional.of(district));

        // Вызов тестируемого метода
        District result = districtService.findDistrict(id);

        // Проверка результатов
        assertEquals(district, result);
    }

    @Test(expected = DistrictNotFoundException.class)
    public void testFindDistrictNotFound() {
        // Создание тестовых данных
        Long id = 1L;

        // Мокирование поведения репозитория
        Mockito.when(districtRepository.findById(id)).thenReturn(Optional.empty());

        // Вызов тестируемого метода, ожидаем исключение
        districtService.findDistrict(id);
    }
}
