package com.mengka.springboot.transfor;

import com.mengka.springboot.dao.domain.GogsRepositoryDO;
import com.mengka.springboot.dao.domain.RepositoryDO;
import com.mengka.springboot.model.dto.GogsRepositoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GogsRepoDtoTransf extends BaseTransfer<RepositoryDO, GogsRepositoryDTO>{

    GogsRepoDtoTransf INSTANCE = Mappers.getMapper(GogsRepoDtoTransf.class);

    @Mappings({
        @Mapping(source = "id",target = "id")
    })
    GogsRepositoryDTO convertDTO(GogsRepositoryDO user);

    @Mappings({
        @Mapping(source = "id",target = "id"),
        @Mapping(target = "updatedUnix",expression = "java(TransferModelUtil.MkTransfer.getUpdateTime(repositoryDO))"),
        @Mapping(target = "createdUnix",expression = "java(TransferModelUtil.MkTransfer.getBusinessNo(repositoryDO))")
    })
    GogsRepositoryDTO toModel(RepositoryDO repositoryDO);
}
