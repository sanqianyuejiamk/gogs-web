
CREATE TABLE gogs.g_repository (
  `id` bigint(32) NOT NULL,
  `owner_id` bigint(32) DEFAULT NULL,
  `lower_name` varchar(245) not NULL,
  `name` varchar(245) DEFAULT NULL,
  `description` varchar(345) DEFAULT NULL,
  `default_branch` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` bigint(32) not NULL,
  PRIMARY KEY (lower_name , parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE gogs.g_commit_history (  `id` bigint(32) NOT NULL,  `repos_name` varchar(45) not NULL,  `commit_id` varchar(45) not NULL,  `author_name` varchar(45) DEFAULT NULL,  `commit_time` datetime DEFAULT NULL,  `full_message` varchar(445) DEFAULT NULL,  `branch_name` varchar(45) DEFAULT NULL,  `email_address` varchar(145) DEFAULT NULL,  `create_time` datetime DEFAULT NULL,  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (repos_name ,commit_id)) ENGINE=InnoDB DEFAULT CHARSET=latin1;