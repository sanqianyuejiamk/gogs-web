package com.mengka.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.mengka.springboot.component.RepoCommitLogComponent;
import com.mengka.springboot.component.RepoRefreshComponent;
import com.mengka.springboot.dao.domain.GogsRepositoryDO;
import com.mengka.springboot.dao.domain.RepositoryDO;
import com.mengka.springboot.dao.persistence.GogsRepositoryDOMapper;
import com.mengka.springboot.dao.persistence.RepositoryDOMapper;
import com.mengka.springboot.model.Out;
import com.mengka.springboot.model.dto.GogsRepositoryDTO;
import com.mengka.springboot.transfor.GogsRepoDtoTransf;
import com.mengka.springboot.transfor.GogsRepoTransf;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mengka
 * @Date 2022-06-27 21:39
 */
@Controller
@RequestMapping("/g1/repo")
public class GogsRepoController {

    static final Logger log = LogManager.getLogger(GogsRepoController.class);
    private final GogsRepoTransf gogsRepoTransf = GogsRepoTransf.INSTANCE;
    private final GogsRepoDtoTransf gogsRepoDtoTransf = GogsRepoDtoTransf.INSTANCE;

    @Value("${gogs.repository.path}")
    private String resourcesPath;

    @Value("${gogs.repository.url}")
    private String resourcesUrl;

    @Autowired
    private GogsRepositoryDOMapper gogsRepositoryDOMapper;

    @Autowired
    private RepoRefreshComponent repoRefreshComponent;

    @Autowired
    private RepoCommitLogComponent repoCommitLogComponent;

    @Autowired
    private RepositoryDOMapper repositoryDOMapper;

    @RequestMapping("/{id}")
    public String demo(@PathVariable("id") String id, Map<String, Object> model){
        log.info("CommonController rate id = {}",id);
        if(StringUtils.isBlank(id)){
            return "child_repos_list";
        }
        GogsRepositoryDO gogsRepositoryDO = gogsRepositoryDOMapper.selectByPrimaryKey(Long.parseLong(id));
        List<RepositoryDO> list = repositoryDOMapper.selectByParentId(Long.parseLong(id));
        List<GogsRepositoryDTO> dtos = gogsRepoDtoTransf.toModel(list);
        model.put("reposList",dtos);
        model.put("parentAppName",gogsRepositoryDO.getName());
        return "child_repos_list";
    }

    /**
     *  更新所有代码仓库
     *
     * @param locale
     * @param model
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "/refesh", method = RequestMethod.POST)
    public void getAccountData(Locale locale, Model model, HttpServletResponse resp) throws IOException {
        System.out.println("Home Page Requested, locale = " + locale);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Out out = refreshAllRepositorys();
        String str = JSON.toJSONString(out);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(str);
        printWriter.flush();
        printWriter.close();
    }

    /**
     *  更新所有代码提交记录数据
     *
     * @param locale
     * @param model
     * @param resp
     * @throws IOException
     */
    @RequestMapping(value = "/refeshCommitLog", method = RequestMethod.POST)
    public void getCommitHistoryData(Locale locale, Model model, HttpServletResponse resp) throws IOException {
        log.info("Home Page getCommitHistoryData Requested, locale = " + locale);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Out out = refreshAllReposCommitHistory();
        String str = JSON.toJSONString(out);

        PrintWriter printWriter = resp.getWriter();
        printWriter.print(str);
        printWriter.flush();
        printWriter.close();
    }

    /**
     *  update all Commit History
     *
     * @return
     */
    public Out refreshAllReposCommitHistory(){
        log.info("refreshAllReposCommitHistory resourcespath: "+ resourcesPath);

        //更新项目信息
        List<GogsRepositoryDO> repositoryDOList = gogsRepositoryDOMapper.selectByBranch("master");
        List<RepositoryDO> list2 = gogsRepoTransf.toDO(repositoryDOList);

        //TODO:
        //List<RepositoryDO> list2 = list1.stream().filter(e -> "github-repository".equals(e.getLowerName())||"spring-learn-2022".equals(e.getLowerName())||"springboot-mkHangdian".equals(e.getName())).collect(Collectors.toList());


        List<String> urls = list2.stream().map(e -> String.format(resourcesUrl,e.getName())).collect(Collectors.toList());
        repoCommitLogComponent.asyncMethodWithCommitLogExecutor(urls);

        return new Out().setReturnCode("0").setReturnMsg("success!");
    }

    /**
     *  update all repository
     *
     *  1.not exist - git clone
     *  2.exist - git pull
     *
     * @return
     */
    public Out refreshAllRepositorys(){
        log.info("refreshAllRepositorys resourcespath: "+ resourcesPath);

        //更新项目信息
        List<GogsRepositoryDO> repositoryDOList = gogsRepositoryDOMapper.selectByBranch("master");
        List<RepositoryDO> list = gogsRepoTransf.toDO(repositoryDOList);

        //TODO:
//        List<RepositoryDO> list2 = list.stream().filter(e -> "github-repository".equals(e.getLowerName())||"spring-learn-2022".equals(e.getLowerName())||"springboot-mkHangdian".equals(e.getName())).collect(Collectors.toList());


        repositoryDOMapper.batchInsertAndUpdate(list,new Date());

        List<String> urls = list.stream().map(e -> String.format(resourcesUrl,e.getName())).collect(Collectors.toList());
        repoRefreshComponent.asyncMethodWithConfiguredExecutor(urls);

        return new Out().setReturnCode("0").setReturnMsg("success!");
    }
}
