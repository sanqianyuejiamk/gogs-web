package com.mengka.springboot.model;

//import com.baomidou.mybatisplus.annotation.FieldFill;
//import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mengka
 * @version 2021/4/25
 * @since
 */
@Data
//@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"createTime","updateTime"})
@ToString(exclude = {"createTime","updateTime"})
public class BaseDO {

//    @TableId(value = "id",type = IdType.ASSIGN_ID)
//    private Long id;

//    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    Date createTime;

//    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    Date updateTime;

//    @TableField(value = "reqst_no")
//    String reqstNo;
}
