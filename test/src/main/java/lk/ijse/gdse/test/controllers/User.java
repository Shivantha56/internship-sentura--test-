package lk.ijse.gdse.test.controllers;

import lk.ijse.gdse.test.dto.UserDTO;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RequestMapping("/api/v1/user")
public class User {

    public static final MediaType JSON = MediaType.get("application/json");

    OkHttpClient client = new OkHttpClient();
    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public String save(UserDTO userDTO) throws IOException {
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

}