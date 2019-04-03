package com.online.store.service.brand;

import com.online.store.model.Brand;
import com.online.store.reporsitory.BrandRepository;
import com.online.store.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Page<BrandDto> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable).map(brandMapper::toDto);
    }

    @Override
    public BrandDto createEntity(BrandDto brandDto) {
        Brand brand = brandRepository.save(brandMapper.toModel(brandDto));
        return brandMapper.toDto(brand);
    }

    @Override
    public BrandDto updateEntity(BrandDto brandDto) {
        Optional<Brand> brandOptional = brandRepository.findById(brandDto.getId());
        if (!brandOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Brand with %d not exist.", brandDto.getId()));
        }
        Brand brand = brandOptional.get();
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
        return brandDto;
    }

    @Override
    public BrandDto deleteEntity(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (!brandOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Brand with %d not exist.", id));
        }
        brandRepository.delete(brandOptional.get());
        return brandMapper.toDto(brandOptional.get());
    }
}
