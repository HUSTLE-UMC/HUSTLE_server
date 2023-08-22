package com.sporthustle.hustle.oauth.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class OAuthConnector {

  public HttpURLConnection getConnectionForKakaoToken(URL url) throws IOException {
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    // POST 요청을 위해 기본값이 false인 setDoOutput을 true로
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    return conn;
  }

  public HttpURLConnection getConnectionForUserInformation(URL url, String token)
      throws IOException {
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("Authorization", "Bearer " + token); // 전송할 header 작성, access_token전송
    return conn;
  }

  public void transportKakaoUrl(
      HttpURLConnection conn, String clientId, String redirectUrl, String code) throws IOException {
    // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
    StringBuilder sb = new StringBuilder();
    sb.append("grant_type=authorization_code");
    sb.append("&client_id=" + clientId); // TODO REST_API_KEY 입력
    sb.append("&redirect_uri=" + redirectUrl); // TODO 인가코드 받은 redirect_uri 입력
    sb.append("&code=" + code);
    bw.write(sb.toString());
    bw.flush();
    bw.close();
  }
}
