package alaa.meaad.mobiletask_dms.repositry.remote;



import alaa.meaad.mobiletask_dms.repositry.model.home.Home;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManagerImpl implements DataManager {

    ApiService apiService;

    public DataManagerImpl() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }




    @Override
    public void getHome(RetrofitCallback callback) {
        Call<Home> call = apiService.getHome();
        call.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


}
