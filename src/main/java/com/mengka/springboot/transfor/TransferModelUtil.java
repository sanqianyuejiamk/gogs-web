package com.mengka.springboot.transfor;

import com.mengka.springboot.dao.domain.RepositoryDO;
import com.mengka.springboot.util.TimeUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author mengka
 * @version 1.0
 * @date 2021/5/17 13:47
 */
public class TransferModelUtil {

    public static class MkTransfer{
        public static String getType(String type){
            return type+"[Just for test.."+TimeUtil.toDate(new Date(),
                    TimeUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);

        }

        public static Long getBusinessNo(RepositoryDO repositoryDO){
            if(repositoryDO == null || repositoryDO.getCreateTime() == null){
                return null;
            }
            Long createTime = repositoryDO.getCreateTime().getTime();
            if(createTime == null){
                return null;
            }
            return createTime/1000;
        }

        public static Long getUpdateTime(RepositoryDO repositoryDO){
            if(repositoryDO == null || repositoryDO.getUpdateTime() == null){
                return null;
            }
            Long createTime = repositoryDO.getUpdateTime().getTime();
            if(createTime == null){
                return null;
            }
            return createTime/1000;
        }
    }
}