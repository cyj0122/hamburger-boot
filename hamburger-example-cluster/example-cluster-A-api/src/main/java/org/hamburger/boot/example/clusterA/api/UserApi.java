package org.hamburger.boot.example.clusterA.api;

import org.hamburger.boot.core.dto.BaseResponse;
import org.hamburger.boot.example.clusterA.dto.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/user/{id}")
    Call<BaseResponse<User>> findUser(@Path("id") String id);
}
