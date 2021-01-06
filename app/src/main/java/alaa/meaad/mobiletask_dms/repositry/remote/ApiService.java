package alaa.meaad.mobiletask_dms.repositry.remote;



import java.util.HashMap;

import alaa.meaad.mobiletask_dms.repositry.model.home.Home;
import alaa.meaad.mobiletask_dms.repositry.model.profile.Profile;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {


    @GET("home")
    Call<Home> getHome();

    @GET("profile")
    Call<Profile> getProfile();
}
