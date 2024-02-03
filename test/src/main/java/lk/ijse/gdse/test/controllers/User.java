package lk.ijse.gdse.test.controllers;

import lk.ijse.gdse.test.dto.UserDTO;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Base64;
import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RequestMapping("/api/v1/user")
@RestController
public class User {

    public static final MediaType JSON = MediaType.get("application/json");
    //we can add this as the spring manage bean
    OkHttpClient client = new OkHttpClient();
    final String URL = "https://a0f7ba0c80b543d48b04006017fee747.weavy.io";


    //delete user details
    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable String userId) throws IOException {
        RequestBody body = RequestBody.create("",JSON);
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users/"+userId+"/trash")
                .header("Authorization","Bearer wys_mBxRK5cG5EsmbqzDdmvKYC3ixdyyj623DZdr")
                .post(body)
                .build();
        client.newCall(request);
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    // get all user details
    @GetMapping("/all")
    public String getAll() throws IOException {
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users")
                .header("Authorization","Bearer wys_mBxRK5cG5EsmbqzDdmvKYC3ixdyyj623DZdr")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /* get access token */
    @GetMapping("/get/{userId}/token")
    public String getAccessToken(@PathVariable String userId) throws IOException {
        RequestBody body = RequestBody.create("",JSON);
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users/"+userId+"/tokens")
                .header("Authorization","Bearer wys_mBxRK5cG5EsmbqzDdmvKYC3ixdyyj623DZdr")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /* register the user */
    @PostMapping("/reg")
    public String createUser(@org.springframework.web.bind.annotation.RequestBody String user){
        RequestBody body = RequestBody.create(user,JSON);
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users")
                .post(body)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer wys_mBxRK5cG5EsmbqzDdmvKYC3ixdyyj623DZdr")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{userId}")
    public String updateUser(@PathVariable String userId , @org.springframework.web.bind.annotation.RequestBody UserDTO userDTO){
        RequestBody body = RequestBody.create(String.valueOf(userDTO), JSON);
        Request request = new Request.Builder()
                .url(URL+"/api/users/"+userId)
                .put(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}