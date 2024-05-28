package dev.ta2khu75.java5assignment.services.impls;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.ta2khu75.java5assignment.enviroments.ContainerEnviroment;
import dev.ta2khu75.java5assignment.exceptions.NotFoundException;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.models.ProductImage;
import dev.ta2khu75.java5assignment.repositories.ProductImageRepository;
import dev.ta2khu75.java5assignment.repositories.ProductRepository;
import dev.ta2khu75.java5assignment.services.AzureStorageService;
import dev.ta2khu75.java5assignment.services.ProductImageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository repository;
    private final ProductRepository productRepository;
    private final AzureStorageService azureStorageService;

    @Override
    public void deleteProductImage(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ProductImage createProductImage(Long productId, MultipartFile image) throws IOException {
        Product product=productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        String imageName=azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT_IMAGES, image);
        imageName="https://java5assignment.blob.core.windows.net/"+ContainerEnviroment.PRODUCT_IMAGES+"/"+imageName;
        ProductImage productImage=new ProductImage();
        productImage.setProduct(product);
        productImage.setImageUrl(imageName);
        return repository.save(productImage);
    }

    @Override
    public ProductImage updateProductImage(Long id, MultipartFile image) throws IOException {
        ProductImage productImage=repository.findById(id).orElseThrow(() -> new NotFoundException("Product image not found"));
        String imageName=azureStorageService.writeBlobFile(ContainerEnviroment.PRODUCT_IMAGES, image);
        imageName="https://java5assignment.blob.core.windows.net/"+ContainerEnviroment.PRODUCT_IMAGES+"/"+imageName;
        productImage.setImageUrl(imageName);
        return repository.save(productImage);
    }

    @Override
    public List<ProductImage> getAllProductImagesByProductId(Long productId) {
        return repository.findAllByProductId(productId);
    }

}
