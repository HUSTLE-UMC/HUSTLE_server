package com.sporthustle.hustle.oauth.utils.kakao;

import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.springframework.stereotype.Component;

@Component
public class KakaoInformationParser {

  public String getAccessToken(JsonElement element) {
    return element.getAsJsonObject().get("access_token").getAsString();
  }

  public String getRefreshToken(JsonElement element) {
    return element.getAsJsonObject().get("refresh_token").getAsString();
  }

  public String getSnsIdByParsing(JsonElement element) {
    long id = element.getAsJsonObject().get("id").getAsLong();
    return Long.toString(id);
  }

  public String getEmailByParsing(JsonElement element) {
    boolean hasEmail =
        element
            .getAsJsonObject()
            .get("kakao_account")
            .getAsJsonObject()
            .get("has_email")
            .getAsBoolean();
    String email = "";
    if (hasEmail) {
      email =
          element
              .getAsJsonObject()
              .get("kakao_account")
              .getAsJsonObject()
              .get("email")
              .getAsString();
    }
    return email;
  }

  public String getNameByParsing(JsonElement element) {
    String name =
        element
            .getAsJsonObject()
            .get("kakao_account")
            .getAsJsonObject()
            .get("profile")
            .getAsJsonObject()
            .get("nickname")
            .getAsString();
    return name;
  }

  public String getGenderByParsing(JsonElement element) {
    boolean has_gender =
        element
            .getAsJsonObject()
            .get("kakao_account")
            .getAsJsonObject()
            .get("has_gender")
            .getAsBoolean();
    String gender = "";
    if (has_gender) {
      gender =
          element
              .getAsJsonObject()
              .get("kakao_account")
              .getAsJsonObject()
              .get("gender")
              .getAsString();
    }
    return gender;
  }

  public String getJsonForKakaoUserInformation(HttpURLConnection conn) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line = "";
    String result = "";

    while ((line = br.readLine()) != null) {
      result += line;
    }
    br.close();
    return result;
  }

  public String getJsonForKakaoToken(HttpURLConnection conn) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line = "";
    String result = "";

    while ((line = br.readLine()) != null) {
      result += line;
    }

    br.close();
    return result;
  }
}
