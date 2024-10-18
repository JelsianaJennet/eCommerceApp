package com.example.productcatalog.services;

import com.example.productcatalog.thirdPartyClients.fakeStore.FakeStoreProductDto;
import com.example.productcatalog.dtos.GenericProductDto;
import com.example.productcatalog.exceptions.NotFoundException;
import com.example.productcatalog.thirdPartyClients.fakeStore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService  implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
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

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException{

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductServiceClient.getProductById(id);

        return convertToGenericProductDto(fakeStoreProductDto);

    }


    public GenericProductDto createProduct(GenericProductDto productDto) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.createProduct(productDto));
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.updateProduct(genericProductDto, id));
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getAllProducts()) {
            genericProductDtos.add(convertToGenericProductDto(fakeStoreProductDto));
        }
        return genericProductDtos;

    }
}
