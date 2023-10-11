package com.mengka.springboot.dao.domain;

import com.mengka.springboot.model.BaseDO;

import java.io.Serializable;
import java.util.Date;

public class RepositoryDO extends BaseDO implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.owner_id
     *
     * @mbg.generated
     */
    private Long ownerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.lower_name
     *
     * @mbg.generated
     */
    private String lowerName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.default_branch
     *
     * @mbg.generated
     */
    private String defaultBranch;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.create_time
     *
     * @mbg.generated
     */
    private Date createTime = new Date();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.update_time
     *
     * @mbg.generated
     */
    private Date updateTime = new Date();

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_repository.parent_id
     *
     * @mbg.generated
     */
    private Long parentId = 0L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table g_repository
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.id
     *
     * @return the value of g_repository.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.id
     *
     * @param id the value for g_repository.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.owner_id
     *
     * @return the value of g_repository.owner_id
     *
     * @mbg.generated
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.owner_id
     *
     * @param ownerId the value for g_repository.owner_id
     *
     * @mbg.generated
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.lower_name
     *
     * @return the value of g_repository.lower_name
     *
     * @mbg.generated
     */
    public String getLowerName() {
        return lowerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.lower_name
     *
     * @param lowerName the value for g_repository.lower_name
     *
     * @mbg.generated
     */
    public void setLowerName(String lowerName) {
        this.lowerName = lowerName == null ? null : lowerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.name
     *
     * @return the value of g_repository.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.name
     *
     * @param name the value for g_repository.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.description
     *
     * @return the value of g_repository.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.description
     *
     * @param description the value for g_repository.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.default_branch
     *
     * @return the value of g_repository.default_branch
     *
     * @mbg.generated
     */
    public String getDefaultBranch() {
        return defaultBranch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.default_branch
     *
     * @param defaultBranch the value for g_repository.default_branch
     *
     * @mbg.generated
     */
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch == null ? null : defaultBranch.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.create_time
     *
     * @return the value of g_repository.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.create_time
     *
     * @param createTime the value for g_repository.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.update_time
     *
     * @return the value of g_repository.update_time
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.update_time
     *
     * @param updateTime the value for g_repository.update_time
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_repository.parent_id
     *
     * @return the value of g_repository.parent_id
     *
     * @mbg.generated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_repository.parent_id
     *
     * @param parentId the value for g_repository.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_repository
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RepositoryDO other = (RepositoryDO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getLowerName() == null ? other.getLowerName() == null : this.getLowerName().equals(other.getLowerName()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getDefaultBranch() == null ? other.getDefaultBranch() == null : this.getDefaultBranch().equals(other.getDefaultBranch()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_repository
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getLowerName() == null) ? 0 : getLowerName().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getDefaultBranch() == null) ? 0 : getDefaultBranch().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_repository
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", lowerName=").append(lowerName);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", defaultBranch=").append(defaultBranch);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}