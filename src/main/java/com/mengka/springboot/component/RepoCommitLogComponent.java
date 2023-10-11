package com.mengka.springboot.component;

import com.alibaba.fastjson.JSON;
import com.mengka.springboot.dao.domain.CommitHistoryDO;
import com.mengka.springboot.dao.persistence.CommitHistoryDOMapper;
import com.mengka.springboot.util.CommonUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Log4j2
@Service
public class RepoCommitLogComponent {

    private static final JgitComponent jgitComponent = JgitComponent.getInitialize();

    @Value("${gogs.repository.path}")
    private String resourcesPath;

    @Autowired
    private CommitHistoryDOMapper commitHistoryDOMapper;

    @Async("threadPoolTaskExecutor")
    public void asyncMethodWithCommitLogExecutor(List<String> urls) {
        log.info("Execute method asynchronously with commitLog executor " + Thread.currentThread().getName());
        try {
            final int pageSize = 10;
            final AtomicInteger counter = new AtomicInteger();
            Collection<List<String>> result = urls.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / pageSize)).values();
            for (List<String> list : result) {
                commit_history_partition(list);
            }
        } catch (final Exception e) {
            log.error("asyncMethodWithCommitLogExecutor error1",e);
        }
    }

    private void commit_history_partition(List<String> list)throws Exception{
        // Start the clock
        long start = System.currentTimeMillis();

        System.out.println(JSON.toJSONString(list));
        CompletableFuture<String>[] cfArray = new CompletableFuture[list.size()];
        for(int i=0;i<list.size();i++){
            // Kick of multiple, asynchronous lookups
            final String uri = list.get(i).trim();
            cfArray[i] = CompletableFuture.supplyAsync(() -> {
                return clone_one(uri);
            });
        }

        // Wait until they are all done
        CompletableFuture.allOf(cfArray).join();

        long end = System.currentTimeMillis();
        log.info("-----commit_history_partition----- clone_partition time: "+(end - start)+"ms");
        log.info("-----commit_history_partition----- ===>>> " + cfArray[0].get());
    }

    private String clone_one(String url) {
        String repositoryName = CommonUtil.getRepositoryByUri(url);
        if(StringUtils.isBlank(repositoryName)){
            return null;
        }
        String repositoryPath = this.resourcesPath + "/" + repositoryName+"/.git";
        List<CommitHistoryDO> commitHistoryDOS = new ArrayList<CommitHistoryDO>();
        try {
            Repository repo = new FileRepository(repositoryPath);
            Git git = new Git(repo);
            RevWalk walk = new RevWalk(repo);

            List<Ref> branches = git.branchList().call();

            for (Ref branch : branches) {
                String branchName = branch.getName();

                log.info("commit_history_partition Commits of ["+repositoryName+"] branch: " + branch.getName());
                log.info("-------------------------------------");

                Iterable<RevCommit> commits = git.log().all().call();

                for (RevCommit commit : commits) {
                    boolean foundInThisBranch = false;

                    RevCommit targetCommit = walk.parseCommit(repo.resolve(commit.getName()));
                    for (Map.Entry<String, Ref> e : repo.getAllRefs().entrySet()) {
                        if (e.getKey().startsWith(Constants.R_HEADS)) {
                            if (walk.isMergedInto(targetCommit, walk.parseCommit(e.getValue().getObjectId()))) {
                                String foundInBranch = e.getValue().getName();
                                if (branchName.equals(foundInBranch)) {
                                    foundInThisBranch = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (foundInThisBranch) {
                        CommitHistoryDO saveDO = new CommitHistoryDO();
                        saveDO.setId(jgitComponent.getNextId());
                        saveDO.setCommitId(commit.getName());
                        saveDO.setAuthorName(commit.getAuthorIdent().getName());
                        saveDO.setEmailAddress(commit.getAuthorIdent().getEmailAddress());
                        saveDO.setCommitTime(new Date(commit.getCommitTime() * 1000L));
                        saveDO.setFullMessage(commit.getFullMessage());
                        saveDO.setBranchName(branchName);
                        saveDO.setReposName(repositoryName);
                        saveDO.setCreateTime(new Date());
                        saveDO.setUpdateTime(new Date());
                        commitHistoryDOS.add(saveDO);
                    }
                }
            }

            //保存数据库
            if(!CollectionUtils.isEmpty(commitHistoryDOS)){
                int value = commitHistoryDOMapper.batchInsertAndUpdate(commitHistoryDOS,new Date());
                log.info("commit_history_partition clone_one ["+repositoryName+"] batchInsertAndUpdate value = "+value);
            }
        }catch (Exception e){
            log.error("commit_history_partition clone_one ["+repositoryName+"] error!",e);
        }
        return repositoryName;
    }
}
