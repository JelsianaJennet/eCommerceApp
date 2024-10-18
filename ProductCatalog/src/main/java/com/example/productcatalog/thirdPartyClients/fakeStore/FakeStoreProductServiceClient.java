package com.example.productcatalog.thirdPartyClients.fakeStore;

import com.example.productcatalog.dtos.GenericProductDto;
import com.example.productcatalog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/***
 * Wrapper over Fake Store API, NO logic
 * Since it is fake store client , should return fakeStore object
 */

@Service("FakeStoreProductServiceClient")
public class FakeStoreProductServiceClient{

    private RestTemplateBuilder restTemplateBuilder;

    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";

    private String getProductRequestUrl;
    private String createProductRequestUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiBase,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreApiProduct){
        this.restTemplateBuilder = restTemplateBuilder;
        this.getProductRequestUrl = fakeStoreApiBase + fakeStoreApiProduct+ "{id}";
        this.createProductRequestUrl = fakeStoreApiBase + fakeStoreApiProduct;
    }

    private GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto product = new GenericProductDto();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setId(fakeStoreProductDto.getId());

        return product;

    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        // get Request - getForEntity
        // post Request - postForEntity
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found");
        }
        return fakeStoreProductDto;

//        if(fakeStoreProductDto == null) {
//            throw  new NotFoundException("Product with id " + id + " doesn't exist");
//        }
//        return convertToGenericProductDto(fakeStoreProductDto);

    }

    public FakeStoreProductDto createProduct(GenericProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(createProductRequestUrl, productDto, FakeStoreProductDto.class);

        return response.getBody();
    }


    public FakeStoreProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(updateProductRequestUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }


    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(updateProductRequestUrl, id);

        return null;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =restTemplate.getForEntity(createProductRequestUrl, FakeStoreProductDto[].class);

        List<FakeStoreProductDto> ans = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: response.getBody()) {
            ans.add(fakeStoreProductDto);
        }
        return ans;

    }
}
