package com.example.productcatalog.services;

import com.example.productcatalog.dtos.GenericProductDto;
import com.example.productcatalog.dtos.UserDto;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("SelfProductServiceImpl")
@Primary
public class SelfProductServiceImpl implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id) {
        System.out.println("In Product Service");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> userDtoResponseEntity =restTemplate.getForEntity("http://localhost:9000/users/1", UserDto.class);
        System.out.println(userDtoResponseEntity);
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }


}
