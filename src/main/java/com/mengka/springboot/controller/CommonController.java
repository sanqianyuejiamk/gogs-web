package com.mengka.springboot.controller;

import com.mengka.springboot.dao.domain.BookDO;
import com.mengka.springboot.dao.domain.GogsRepositoryDO;
import com.mengka.springboot.dao.domain.RepositoryDO;
import com.mengka.springboot.dao.persistence.BookDOMapper;
import com.mengka.springboot.dao.persistence.GogsRepositoryDOMapper;
import com.mengka.springboot.dao.persistence.RepositoryDOMapper;
import com.mengka.springboot.model.dto.GogsRepositoryDTO;
import com.mengka.springboot.transfor.GogsRepoDtoTransf;
import com.mengka.springboot.transfor.GogsRepoTransf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangyy
 * @description
 * @data 2017/02/19.
 */
@Controller
@RequestMapping("/v1")
public class CommonController {

    static final Logger logger = LogManager.getLogger(CommonController.class);
    private final GogsRepoDtoTransf gogsRepoDtoTransf = GogsRepoDtoTransf.INSTANCE;

    @Autowired
    private BookDOMapper bookDOMapper;

    @Autowired
    private GogsRepositoryDOMapper gogsRepositoryDOMapper;

    @Autowired
    private RepositoryDOMapper repositoryDOMapper;

    /**
     *  http://127.0.0.1:8073/v1/rate
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/rate")
    public String product(Map<String, Object> model, Long id){
        logger.info("CommonController rate id = {}",id);
        model.put("list",null);

        //add
//        BookDO bookDO = new BookDO();
//        bookDO.setName("数学");
//        bookDO.setPrice(100);
//        bookDO.setTenantId("20022");
//        bookDOMapper.insert(bookDO);
//        logger.info("CommonController add a new book! id = {}",bookDO.getId());

        return "product_rate";
    }

    @RequestMapping("/demo")
    public String demo(Map<String, Object> model, Long id){
        logger.info("CommonController rate id = {}",id);
        List<GogsRepositoryDO> repositoryDOList = gogsRepositoryDOMapper.selectByBranch("master");
        model.put("reposList",convertDTO(repositoryDOList));
        return "repos_list";
    }

    private List<GogsRepositoryDTO> convertDTO(List<GogsRepositoryDO> repositoryDOList){
        List<GogsRepositoryDTO> list = new ArrayList<>();
        for(GogsRepositoryDO repositoryDO : repositoryDOList){
            GogsRepositoryDTO repositoryDTO = gogsRepoDtoTransf.convertDTO(repositoryDO);

//            List<RepositoryDO> childRepos = repositoryDOMapper.selectByParentId(repositoryDTO.getId());
//            if(!CollectionUtils.isEmpty(childRepos)){
//                repositoryDTO.setHasChild(true);
//            }
            list.add(repositoryDTO);
        }
        return list;
    }
}
