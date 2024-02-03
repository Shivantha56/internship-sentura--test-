package lk.ijse.gdse.test.controllers;

import lk.ijse.gdse.test.dto.UserDTO;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RequestMapping("/api/v1/user")
public class User {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client = new OkHttpClient();
    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public String save(@org.springframework.web.bind.annotation.RequestBody UserDTO userDTO) throws IOException {
        RequestBody body = RequestBody.create(String.valueOf(userDTO), JSON);
        Request request = new Request.Builder()
                .url("/api/users")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }

    }

    public void getAll(){

    }

    @ResponseBody
    @GetMapping("{userId}")
    public String getUser(@PathVariable String userId) throws IOException {
        ///api/users/{id}
        Request request = new Request.Builder()
                .url("/api/users/"+userId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @PutMapping("{userId}")
    public String updateUser(@PathVariable String userId , @org.springframework.web.bind.annotation.RequestBody UserDTO userDTO){
        RequestBody body = RequestBody.create(String.valueOf(userDTO), JSON);
        Request request = new Request.Builder()
                .url("/api/users")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}