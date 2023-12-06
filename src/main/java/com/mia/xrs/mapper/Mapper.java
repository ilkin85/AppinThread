package com.mia.xrs.mapper;

@org.mapstruct.Mapper
public interface Mapper<P, D>{
    D toDto(P p);
    P toPackage(D d);
}
