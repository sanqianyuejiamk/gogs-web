package com.mengka.springboot.dao.domain;

import java.io.Serializable;

public class CommitHistoryDOKey implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_commit_history.repos_name
     *
     * @mbg.generated
     */
    private String reposName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column g_commit_history.commit_id
     *
     * @mbg.generated
     */
    private String commitId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table g_commit_history
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_commit_history.repos_name
     *
     * @return the value of g_commit_history.repos_name
     *
     * @mbg.generated
     */
    public String getReposName() {
        return reposName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_commit_history.repos_name
     *
     * @param reposName the value for g_commit_history.repos_name
     *
     * @mbg.generated
     */
    public void setReposName(String reposName) {
        this.reposName = reposName == null ? null : reposName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column g_commit_history.commit_id
     *
     * @return the value of g_commit_history.commit_id
     *
     * @mbg.generated
     */
    public String getCommitId() {
        return commitId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column g_commit_history.commit_id
     *
     * @param commitId the value for g_commit_history.commit_id
     *
     * @mbg.generated
     */
    public void setCommitId(String commitId) {
        this.commitId = commitId == null ? null : commitId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_commit_history
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
        CommitHistoryDOKey other = (CommitHistoryDOKey) that;
        return (this.getReposName() == null ? other.getReposName() == null : this.getReposName().equals(other.getReposName()))
            && (this.getCommitId() == null ? other.getCommitId() == null : this.getCommitId().equals(other.getCommitId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_commit_history
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReposName() == null) ? 0 : getReposName().hashCode());
        result = prime * result + ((getCommitId() == null) ? 0 : getCommitId().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table g_commit_history
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reposName=").append(reposName);
        sb.append(", commitId=").append(commitId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}