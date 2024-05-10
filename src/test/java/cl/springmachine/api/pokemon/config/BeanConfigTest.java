package cl.springmachine.api.pokemon.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class BeanConfigTest {

    private final BeanConfig beanConfig = new BeanConfig();

    @Test
    void test() {
        RestTemplate restTemplate = beanConfig.restTemplate();

        Assertions.assertNotNull(restTemplate);
        Assertions.assertInstanceOf(RestTemplate.class, restTemplate);
    }

}