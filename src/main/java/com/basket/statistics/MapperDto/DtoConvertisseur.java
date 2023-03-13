package com.basket.statistics.MapperDto;

import org.modelmapper.ModelMapper;

public class DtoConvertisseur {
    private static ModelMapper myMapper = new ModelMapper();


    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {
        return obj == null ? null : myMapper.map(obj, clazz);
    }

}
