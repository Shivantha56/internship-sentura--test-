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

    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE , headers = {})
    public String save(@org.springframework.web.bind.annotation.RequestBody UserDTO userDTO) throws IOException {
//        System.out.println("dd");
//        String userImage = Base64.getEncoder().encodeToString(userDTO.getPicture())

        RequestBody body = RequestBody.create(String.valueOf(userDTO), JSON);
        Request request = new Request.Builder()
                .url(URL+"/api/users")
                .post(body)
                .header("Authorization","Bearer wys_mBxRK5cG5EsmbqzDdmvKYC3ixdyyj623DZdr")
                .header("Content-Type","application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // get all user details
    @GetMapping("/all")
    public String getAll() throws IOException {
        Request request = new Request.Builder()
                .url(URL+"/api/users")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @ResponseBody
    @GetMapping("{userId}")
    public String getUser(@PathVariable String userId) throws IOException {
        ///api/users/{id}
        Request request = new Request.Builder()
                .url(URL+"/api/users/"+userId)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    //

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

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable String userId){

        Request request = new Request.Builder()
                .url(URL+"api/users/"+userId+"/trash")
                .delete()
                .build();
        client.newCall(request);
    }

    // get access token

    @GetMapping("/token")
    public String getAccessToken(){
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users/dilshan/tokens")
                .get()
                .header("Content-Type","application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* generating access token */
    @GetMapping("/reg")
    public String createUser(@org.springframework.web.bind.annotation.RequestBody String user){
        RequestBody body = RequestBody.create(user,JSON);
        Request request = new Request.Builder()
                .url("https://a0f7ba0c80b543d48b04006017fee747.weavy.io/api/users/dilshan/tokens")
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


}