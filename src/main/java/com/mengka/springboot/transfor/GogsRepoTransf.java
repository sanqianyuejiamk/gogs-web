package com.mengka.springboot.transfor;

import com.mengka.springboot.dao.domain.GogsRepositoryDO;
import com.mengka.springboot.dao.domain.RepositoryDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Mapper(componentModel = "spring")
public interface GogsRepoTransf extends BaseTransfer<RepositoryDO,GogsRepositoryDO>{

    GogsRepoTransf INSTANCE = Mappers.getMapper(GogsRepoTransf.class);

    @Mappings({
       @Mapping(source = "id",target = "id")
    })
    RepositoryDO toDO(GogsRepositoryDO user);
}
