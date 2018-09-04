package com.yu.accessTokenAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * Created By Yu On 2018/9/4
 * Description：获取凭证的实现类，以微信accessToken举例
 **/
@Component
public class LocalAccessTokenProvider implements AccessTokenProvider {

    private String accessToken = null;

    @Autowired
    private LocalAccessTokenProvider(TaskScheduler taskScheduler, RestTemplate restTemplate) {
        this.taskScheduler = taskScheduler;
        this.restTemplate = restTemplate;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    // 导入加载此类时先自动获取一遍accessToken
    @Override
    @PostConstruct
    public void init() {
        refreshAccessToken();
    }

    private void refreshAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String wxResponse = restTemplate.getForObject(url, String.class);
        Calendar calendar = Calendar.getInstance();
        try {
            Map map = new ObjectMapper().readValue(wxResponse, Map.class);
            if (map == null || map.containsKey("errcode")) {
                logger.error("Error in obtaining access token.error={}.retry in 5 seconds", map == null ? "no response" : map.get("errmsg"));
                calendar.add(Calendar.SECOND, 5);
                taskScheduler.schedule(this::refreshAccessToken, calendar.getTime());
                return;
            }
            accessToken = (String) map.get("access_token");
            Integer expire = (Integer) map.get("expires_in");
            expire = expire - 100;
            calendar.add(Calendar.SECOND, expire);
            taskScheduler.schedule(this::refreshAccessToken, calendar.getTime());
        } catch (IOException e) {
            logger.error("can not obtain access token", e);
        }
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final
    TaskScheduler taskScheduler;
    private final
    RestTemplate restTemplate;

    @Value("${keys.weixin.appId}")
    String appId;
    @Value("${keys.weixin.secret}")
    String appSecret;
}
