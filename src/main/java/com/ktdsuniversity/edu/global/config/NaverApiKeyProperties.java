 package com.ktdsuniversity.edu.global.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/************************************************
 * 네이버 API 클라이언트 ID, Secret 관리 클래스 
 * @author 동은
 ************************************************/
@Configuration
@ConfigurationProperties("naver-api")
public class NaverApiKeyProperties {

	// Naver API Client ID
    private String clientId;
    
    // Naver API Client Secret
    private String clientSecret;
    
    
    // GETTER & SETTER
	public String getClientId() {
		return this.clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return this.clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
    
    
    

}