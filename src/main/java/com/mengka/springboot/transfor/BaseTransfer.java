package com.mengka.springboot.transfor;

//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.mkyong.model.BaseDO;

import com.mengka.springboot.model.BaseDO;
import java.util.List;

/**
 * @author mengka
 * @version 2021/4/29
 * @since
 */
public interface BaseTransfer<P,T> {

    /**
     *  DTO转Entity
     *
     * @param dto
     * @return
     */
    P toDO(T dto);

    /**
     *  DTO集合转Entity集合
     *
     * @param dtoList
     * @return
     */
    List<P> toDO(List<T> dtoList);

//    /**
//     *  DTO集合页转Entity集合页
//     *
//     * @param dtoList
//     * @return
//     */
//    Page<P> toDOPage(Page<T> dtoList);

    /**
     *  Entity转DTO
     *
     * @param entity
     * @return
     */
    T toModel(P entity);

    /**
     *  Entity集合转DTO集合
     *
     * @param entityList
     * @return
     */
    List<T> toModel(List<P> entityList);

//    /**
//     *  Entity集合页转DTO集合页
//     *
//     * @param entityList
//     * @return
//     */
//    Page<T> toModelPage(Page<P> entityList);


}
