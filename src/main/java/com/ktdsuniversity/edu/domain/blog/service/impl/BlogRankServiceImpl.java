package com.ktdsuniversity.edu.domain.blog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ktdsuniversity.edu.domain.blog.service.BlogRankService;
import com.ktdsuniversity.edu.global.config.NaverApiKeyProperties;


@Service
public class BlogRankServiceImpl implements BlogRankService{
	
	private static final Logger log = LoggerFactory.getLogger(BlogRankServiceImpl.class);
	
	
    @Autowired
    NaverApiKeyProperties naverApi;

    // naver API Base 로직
    private WebClient webClient = WebClient.builder()
            .baseUrl("https://openapi.naver.com/v1/search/blog")
            .build();


    /**
     * 블로그 검색 API 요청 메서드
     * @param query 검색어
     * @param userBlogLink 사용자가 입력한 블로그 링크
     * @return 응답 결과와 블로그 순위 정보
     * @author 동은
     */
    @Override
    public ObjectNode searchBlogAndFindUserRank(String query, String userBlogLink) {
        final int maxStart = 1000;
        int rank = -1;
        ObjectNode response = null;

        
       
        
        // start 값을 100씩 증가시키며 요청을 반복 (최댓값은 1000)
        for (int currentStart =  1; currentStart < maxStart; currentStart += 100) {
            int finalCurrentStart = currentStart;
            log.info("몇 번째 페이지?!?!?! {}", finalCurrentStart);
            
            response = webClient.get()
                                .uri(uriBuilder -> uriBuilder
                                .queryParam("query", query)
                                .queryParam("display", 100)
                                .queryParam("start", finalCurrentStart)
                                .queryParam("sort", "sim")
                                .build())
                                .headers(headers -> {
                                		              headers.add("X-Naver-Client-Id", naverApi.getClientId());
                                		              headers.add("X-Naver-Client-Secret", naverApi.getClientSecret());
                                					})
                                .retrieve()
                                .bodyToMono(ObjectNode.class)
                                .block();

            log.info("\n[블로그 검색 API 응답] \n{}", response.toPrettyString());

            // 검색 결과에서 사용자 블로그 링크의 순위를 확인
            // TODO: link의 블로그 아이디만 비교하는 로직 추가 필요
            
            if (response != null && response.has("items")) {
                for (int i = 0; i < response.get("items").size(); i++) {
                    String resultLink = response.get("items").get(i).get("link").asText();
                    log.info("몇 번째? {}, 지금 몇? {}",currentStart, rank);
                    if (resultLink.equals(userBlogLink)) {
                        rank = finalCurrentStart + i;  // 배열은 0부터 시작하므로 i 더함
                        
                        break;
                    }
                }
            }
            
            // 기본 rank 값은 -1이므로 0 이상이면 무조건 이미 찾은 블로그라는 의미
            // 몇 번째 페이지인지 확인을 위한 log
            if (rank > 0) {
                log.info("사용자의 블로그 순위: {}", rank);
                break;
            } else {
                log.info("사용자의 블로그가 현재 페이지에서 검색되지 않음, 다음 페이지를 검색합니다. (start={})", finalCurrentStart);
            }
        }

    // 순위를 응답에 추가
        if (response != null) {
        response.put("userBlogRank", rank > 0 ? rank : -1);  // -1일 경우 순위 없음
    }

        return response;  // 응답 결과 전체와 순위 포함
    }

}
