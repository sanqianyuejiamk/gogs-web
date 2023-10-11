package com.mengka.springboot.component;

import com.mengka.springboot.util.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;
import java.util.Collection;
import java.util.Date;

/**
 * @author huangyy
 * @version qiushan2.0, 2018/7/30
 * @since qiushan2.0
 */
@Log4j2
public class JgitComponent {

    private static final Logger logger = LogManager.getLogger(JgitComponent.class);

    private static final LocalCacheComponent localCacheComponent = LocalCacheComponent.getInstance();

    private static final IdWorker idWorker = new IdWorker(31, 31);
    private static final IdWorker commitIdWorker = new IdWorker(31, 24);

    public static final String SOURCE_CODE_CLONE_KEY = "jgit-clone-%s";

    public Long getNextId(){
        return idWorker.nextId();
    }

    public Long getNextCommitId(){
        return commitIdWorker.nextId();
    }

    public void clone(String uri, String path, String username, String password) {
        String virtualServiceId = CommonUtil.getRepositoryByUri(uri);
        clone(uri, path, username, password,null,virtualServiceId);
    }

    public void clone(String uri, String path, String username, String password,String branch, String virtualServiceId) {
        logger.info("调用Jgit模块代码clone命令 ["+ virtualServiceId+"] start-{}", TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        try {
            File file = new File(path);
            if (file.exists()) {
                FileDirUtil.deleteDir(file);
            }
            file.mkdirs();

            CloneCommand.Callback callback = new CloneCommand.Callback() {

                @Override
                public void initializedSubmodules(Collection<String> submodules) {
                    // Nothing to do
                }

                @Override
                public void cloningSubmodule(String path) {
                    log.info("---------, cloningSubmodule path = {}",path);

                }

                @Override
                public void checkingOut(AnyObjectId commit, String path) {
                    // Nothing to do
                    log.info("---------, checkingOut path = {}",path);
                }
            };


            CloneCommand cloneCommand = Git.cloneRepository();
            cloneCommand.setURI(uri);
            cloneCommand.setDirectory(file);
            if(StringUtils.isNotBlank(branch)){
                cloneCommand.setBranch(branch);
            }
            cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
            cloneCommand.setCallback(callback);
            cloneCommand.call();
        } catch (Exception e) {
            log.error("jgit clone error!", e);
        }
        logger.info("调用Jgit模块代码clone命令 ["+ virtualServiceId+"] end-{}",TimeUtil.toDate(new Date(),TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
        /**保存代码clone进度到缓存**/
        String key = String.format(SOURCE_CODE_CLONE_KEY, virtualServiceId);
        localCacheComponent.put(key,true);
        log.info("保存代码clone进度到缓存 {}",key);
    }

    public static JgitComponent getInitialize() {
        return JgitFactoryHolder.jgitFactory_holder;
    }

    private static class JgitFactoryHolder {
        private static JgitComponent jgitFactory_holder = new JgitComponent();
    }
}
