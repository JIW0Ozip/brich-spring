package com.ktdsuniversity.edu.domain.pay.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.domain.pay.service.PayService;
import com.ktdsuniversity.edu.domain.pay.vo.request.RequestPaymentVO;
import com.ktdsuniversity.edu.domain.user.vo.UserVO;
import com.ktdsuniversity.edu.global.common.CommonCodeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class WidgetController {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.api.key}")
    String widgetSecretKey;
    
    private String PKkey;
    
	@Autowired
    private PayService payService;
    
    @GetMapping("/blgr/pay/{cdId}")
    public String PayBlgr(@PathVariable String cdId, Model model,
			 @SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	
    	// 결제 cdId 로 클라이언트에서 전달할 정보 조회하기
    	CommonCodeVO commonCodeVO = this.payService.payInfoService(cdId);
    	
    	String amount = commonCodeVO.getValue1();   // 가격
    	String cdNm= commonCodeVO.getCdNm();		// 상품명
    	model.addAttribute("amount", amount);
    	model.addAttribute("cdNm",cdNm);
    	model.addAttribute("cdId",cdId);
    	model.addAttribute("usrId", loginUser.getUsrId());
    	return "pay/checkout";
    	
    }
    
    @GetMapping("/adv/pay/{cmpnId}")
    public String PayAdv(@PathVariable String cmpnId, Model model,
    		@SessionAttribute(value = "__LOGIN_USER__", required = false) UserVO loginUser ) {
    	// cmpnId 로 결재내역 검색
    	String amount = this.payService.payInfoServiceCampaignAmount(cmpnId);
    	
    	
    	model.addAttribute("amount", amount );
    	model.addAttribute("usrId", loginUser.getUsrId());
    	model.addAttribute("cmpnId", cmpnId);
    	return "pay/checkoutcmpn";
    	
    }
    
    @PostMapping("/orders/prepay")
    public ResponseEntity<Void> prepay(@RequestBody String jsonBody, 
    		@SessionAttribute(value = "__LOGIN_USER__") UserVO loginUser) {
        // orderId, usrId, orderName, amount 를 DB나 세션에 저장
    	JSONParser parser = new JSONParser();
    	JSONObject requestData;
		try {
			requestData = (JSONObject) parser.parse(jsonBody);
			String clientOrderName = (String) requestData.get("orderName");
			String clientCdId = (String) requestData.get("cdId");
			
			String clientUsrId = (String) requestData.get("usrId");
			String clientCmpnId = (String) requestData.get("cmpnId");
			
			String clientOrderId = (String) requestData.get("orderId");
			String clientPrice = String.valueOf(requestData.get("price"));
			
            logger.info("-----클라이언트가 보내준 값 ----- : " + 
            			" clientOrderName : " + clientOrderName +
            			" clientUsrId : " + clientUsrId +
            			" clientOrderId : " + clientOrderId +
            			" clientCdId : " + clientCdId +
            			" clientCmpnid: " + clientCmpnId +
            			" clientPrice : " + clientPrice);
            
            // 결제 전 정보 db에 선 저장
            // 결제 후 응답값과 비교하여 유효성 체크 할 예정
            RequestPaymentVO requestPaymentVO = new RequestPaymentVO();
            requestPaymentVO.setClientOrderId(clientOrderId);
            requestPaymentVO.setClientOrderName(clientOrderName);
            
            if(clientUsrId != null && clientUsrId != "") {
            	requestPaymentVO.setClientId(clientUsrId);            	
            } else if(clientCmpnId != null && clientCmpnId != "") {
            	requestPaymentVO.setClientId(clientCmpnId);
            }
            requestPaymentVO.setClientPrice(clientPrice);
            requestPaymentVO.setClientCdId(clientCdId);
            PKkey = this.payService.beforePaymentInfoSave(requestPaymentVO);
            if(loginUser.getAutr().equals("1004")) {
            	logger.info("ClientId : " + requestPaymentVO.getClientId());
            	PKkey = this.payService.beforeCampaigninfo(requestPaymentVO.getClientId());
            	logger.info("PKkey : " + PKkey);
            }
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	
        // 이후 /confirm에서 orderId로 조회해서 검증/사용
        return ResponseEntity.ok().build();
    }
    
    
    @RequestMapping(value = "/confirm")
    public ResponseEntity<JSONObject> confirmPayment(@RequestBody String jsonBody, HttpSession httpSession) throws Exception {
        logger.info("============= 결제 시작 =============" ); 

        JSONParser parser = new JSONParser();
        String orderId;
        String amount;
        String paymentKey;
        JSONObject easyPay;
        String easyAmount;
        String orderName;  
        

        try {
            // 클라이언트에서 받은 JSON 요청 바디입니다.
            JSONObject requestData = (JSONObject) parser.parse(jsonBody);
            paymentKey = (String) requestData.get("paymentKey");
            orderId = (String) requestData.get("orderId");
            amount = (String) requestData.get("amount");
            

            logger.info("결제 요청값 : " + requestData.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        };
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);
        obj.put("paymentKey", paymentKey);

        // TODO: 개발자센터에 로그인해서 내 결제위젯 연동 키 > 시크릿 키를 입력하세요. 시크릿 키는 외부에 공개되면 안돼요.
        // @docs https://docs.tosspayments.com/reference/using-api/api-keys

        // 토스페이먼츠 API는 시크릿 키를 사용자 ID로 사용하고, 비밀번호는 사용하지 않습니다.
        // 비밀번호가 없다는 것을 알리기 위해 시크릿 키 뒤에 콜론을 추가합니다.
        // @docs https://docs.tosspayments.com/reference/using-api/authorization#%EC%9D%B8%EC%A6%9D
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes(StandardCharsets.UTF_8));
        String authorizations = "Basic " + new String(encodedBytes);

        // 결제 승인 API를 호출하세요.
        // 결제를 승인하면 결제수단에서 금액이 차감돼요.
        // @docs https://docs.tosspayments.com/guides/v2/payment-widget/integration#3-결제-승인하기
        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);


        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();
        boolean isSuccess = code == 200;
        
        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

        // TODO: 결제 성공 및 실패 비즈니스 로직을 구현하세요.
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        
        if (code == 200) {
        	orderId      = (String) jsonObject.get("orderId");
            orderName    = (String) jsonObject.get("orderName");
        	paymentKey   = (String) jsonObject.get("paymentKey");
        	easyPay = (JSONObject) jsonObject.get("easyPay");
            easyAmount    = String.valueOf((easyPay != null && easyPay.get("amount") != null)
                               ? ((Number) easyPay.get("amount")).longValue() : null);
            logger.info("응답값 파라미터 : " +
            		"orderId : " + orderId + 
            		"orderName : " + orderName + 
            		"paymentKey : " + paymentKey + 
            		"easyAmount : " + easyAmount);
            
            RequestPaymentVO requestPaymentVO = new RequestPaymentVO();
            requestPaymentVO.setOrderId(orderId);
            requestPaymentVO.setOrderName(orderName);
            requestPaymentVO.setPaymentKey(paymentKey);
            requestPaymentVO.setEasyAmount(easyAmount);
            requestPaymentVO.setPKkey(PKkey);
            boolean paymentValidationCheck = this.payService.paymentValidationCheck(requestPaymentVO);
            if(paymentValidationCheck) {
            	int paymentSuccessUpdateCount = this.payService.paymentSuccessUpdate(requestPaymentVO);
            }else {
            	jsonObject.put("status", "ABORTED");
                return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST).body(jsonObject);
            }
            
            
        }

        
        responseStream.close();
        
        logger.info("응답값  [tosspay] raw response: {}", jsonObject.toJSONString());
        return ResponseEntity.status(code).body(jsonObject);
    }

    /**
     * 인증성공처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String paymentRequest(HttpServletRequest request, Model model) throws Exception {
        return "/pay/success";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) throws Exception {
        return "/pay/checkout";
    }

    /**
     * 인증실패처리
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String failPayment(HttpServletRequest request, Model model) throws Exception {
        String failCode = request.getParameter("code");
        String failMessage = request.getParameter("message");

        model.addAttribute("code", failCode);
        model.addAttribute("message", failMessage);

        return "/pay/fail";
    }
}

