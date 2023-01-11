package be.fve.javaspringtutorials.modules.partialupdates;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product map(ProductDTO entity);

    ProductDTO map(Product entity);

    @InheritConfiguration
    void update(ProductDTO update, @MappingTarget Product destination);

}
