package nl.novi.krijt.service;

import nl.novi.krijt.DonDiabloDemoDropApplication;
import nl.novi.krijt.domain.Demo;
import nl.novi.krijt.domain.User;
import nl.novi.krijt.repository.DemoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ContextConfiguration(classes = DonDiabloDemoDropApplication.class)
public class DemoServiceImplTest {

    @InjectMocks
    private DemoServiceImpl demoService;

    @Mock
    private DemoRepository demoRepository;
    Demo demo;

    @Test
    public void getDemoByIdShouldReturnDemo1() {
        User user = new User();
        Demo d = new Demo();
        d.setId(1L);

        Demo demo1 = new Demo(1L, "name", "email", "message", "demo", "downlaodURL", "feedback");
        Mockito
                .when(demoRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(demoRepository.findById(anyLong()))
                .thenReturn(Optional.of(d));

        Demo demoThroughService = demoService.getDemoById(1);

        assertEquals(1L, demoThroughService.getId());
    }

    @Test
    void updateDemoTest() {
        User user = new User();
        Demo d = new Demo();
        d.setId(1L);

        Demo demo1 = new Demo(1L, "name", "email", "message", "demo", "downlaodURL", "feedback");
        Mockito
                .when(demoRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(demoRepository.findById(anyLong()))
                .thenReturn(Optional.of(d));

        ResponseEntity demoThroughService = demoService.updateDemo( 1 ,"this is the feedback now");

        assertEquals(HttpStatus.OK, demoThroughService.getStatusCode());
    }
}
