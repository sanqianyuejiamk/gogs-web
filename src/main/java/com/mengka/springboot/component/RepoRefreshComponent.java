package com.mengka.springboot.component;

import com.alibaba.fastjson.JSON;
import com.mengka.springboot.dao.domain.GogsRepositoryDO;
import com.mengka.springboot.dao.domain.RepositoryDO;
import com.mengka.springboot.dao.persistence.GogsRepositoryDOMapper;
import com.mengka.springboot.dao.persistence.RepositoryDOMapper;
import com.mengka.springboot.transfor.GogsRepoTransf;
import com.mengka.springboot.util.CommonUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.io.File;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author mengka
 * @Date 2022-07-04 20:01
 */
@Log4j2
@Service
public class RepoRefreshComponent {

    private static final JgitComponent jgitComponent = JgitComponent.getInitialize();
    private static final String username = "934734735@qq.com";
    private static final String password = "544027354hyy";

    @Value("${gogs.repository.path}")
    private String resourcesPath;

    @Autowired
    private RepositoryDOMapper repositoryDOMapper;

    @Autowired
    private GogsRepositoryDOMapper gogsRepositoryDOMapper;

    @Autowired
    private RepoCommitLogComponent repoCommitLogComponent;

    @Async("threadPoolTaskExecutor")
    public void asyncMethodWithConfiguredExecutor(List<String> urls) {
        log.info("Execute method asynchronously with configured executor " + Thread.currentThread().getName());
        try {
            final int pageSize = 10;
            final AtomicInteger counter = new AtomicInteger();
            Collection<List<String>> result = urls.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / pageSize)).values();
            for (List<String> list : result) {
                clone_partition(list);
            }
            //更新日志
            repoCommitLogComponent.asyncMethodWithCommitLogExecutor(urls);
        } catch (final Exception e) {
            log.error("asyncMethodWithConfiguredExecutor error1",e);
        }
    }

    private void clone_partition(List<String> list)throws Exception{
        // Start the clock
        long start = System.currentTimeMillis();

        System.out.println(JSON.toJSONString(list));
        CompletableFuture<String>[] cfArray = new CompletableFuture[list.size()];
        CompletableFuture<Integer>[] childFetchArray = new CompletableFuture[list.size()];
        for(int i=0;i<list.size();i++){
            // Kick of multiple, asynchronous lookups
            final String uri = list.get(i).trim();
            cfArray[i] = CompletableFuture.supplyAsync(() -> {
                return clone_one(uri);
            });

            childFetchArray[i] = cfArray[i].thenApplyAsync((repositoryPath) -> {return doRepositoryPath(repositoryPath);});
        }

        // Wait until they are all done
        CompletableFuture.allOf(childFetchArray).join();

        long end = System.currentTimeMillis();
        log.info("-----clone_partition----- clone_partition time: "+(end - start)+"ms");
        log.info("-----clone_partition----- ===>>> " + cfArray[0].get());
    }

    /**
     *  更新仓库下面的子项目
     *
     * @param repositoryPath
     */
    private Integer doRepositoryPath(String repositoryPath){
        File dir = new File(repositoryPath);
        File[] files = dir.listFiles();
        Set<String> childProjects = new HashSet<>();
        for(File file : files){
            if(file.isDirectory()){
                File[] childFiles = file.listFiles();

                Arrays.stream(childFiles).forEach(f -> {
                    if(".gitignore".equals(f.getName()) && f.isFile()){
                        childProjects.add(file.getName());
                    }
                });
            }
        }

        String lowerName = dir.getName().toLowerCase();
        RepositoryDO parentRepositoryDO = repositoryDOMapper.selectByCode(0L,lowerName);
        if(parentRepositoryDO==null){
            return 0;
        }
        List<RepositoryDO> childRepositorys = childProjects.stream().map(e -> getChildRepository(e,parentRepositoryDO)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(childRepositorys)) {
            GogsRepositoryDO recordDO = new GogsRepositoryDO();
            recordDO.setHasChild(true);
            recordDO.setId(parentRepositoryDO.getId());
            gogsRepositoryDOMapper.updateByPrimaryKeySelective(recordDO);
            repositoryDOMapper.batchInsertAndUpdate(childRepositorys, new Date());
        }

        System.out.println("------, childProjects = "+ JSON.toJSONString(childProjects,true));
        return childProjects.size();
    }

    private RepositoryDO getChildRepository(String name,RepositoryDO parentRepositoryDO){
        final BeanCopier copier = BeanCopier.create(RepositoryDO.class, RepositoryDO.class, false);
        RepositoryDO to = new RepositoryDO();
        copier.copy(parentRepositoryDO, to, null);
        to.setId(jgitComponent.getNextId());
        to.setDescription("");
        to.setName(name);
        to.setLowerName(name);
        to.setParentId(parentRepositoryDO.getId());
        return to;
    }

    private String clone_one(String url) {
        String repositoryName = CommonUtil.getRepositoryByUri(url);
        if(StringUtils.isBlank(repositoryName)){
            return null;
        }
        String repositoryPath = this.resourcesPath + "/" + repositoryName;
        jgitComponent.clone(url, repositoryPath, username, password);
        return repositoryPath;
    }
}
