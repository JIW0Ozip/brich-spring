package com.ktdsuniversity.edu.domain.blog.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface BlogRankService {

	ObjectNode searchBlogAndFindUserRank(String query, String userBlogLink);

}
