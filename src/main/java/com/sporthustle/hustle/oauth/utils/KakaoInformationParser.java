package com.sporthustle.hustle.oauth.utils;

import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoInformationParser {

    private final JsonElement element;

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

}
