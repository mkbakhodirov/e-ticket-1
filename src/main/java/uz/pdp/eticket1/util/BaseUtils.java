package uz.pdp.eticket1.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseUtils {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
