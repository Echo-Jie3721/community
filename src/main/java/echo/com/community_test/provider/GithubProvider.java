package echo.com.community_test.provider;

import com.alibaba.fastjson.JSON;
import echo.com.community_test.dto.AccessTokenDto;
import echo.com.community_test.dto.GithubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = SSLSocketClient.getUnsafeOkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String token = string.split("&")[0].split("=")[1];
                return token;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    public GithubUserDto getUser(String accessToken){
        OkHttpClient client = SSLSocketClient.getUnsafeOkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUserDto githubUser = JSON.parseObject(string, GithubUserDto.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
