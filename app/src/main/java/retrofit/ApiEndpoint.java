package retrofit;

import Model.Data;
import Model.DetailModel;
import Model.KeluarKendaraanModel;
import Model.LoginModel;

import Model.LogoutModel;
import Model.SimpanKendaraanModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @POST("login")
    Call<LoginModel> sendLogin(@Body LoginModel loginModel);

    @POST("transactions")
    Call<Data> SendKendaraan(@Header("Authorization") String authToken, @Body Data data);

    @GET("transactions/parker/{parker_id}/vehicle/{vehicle_id}")
    Call<KeluarKendaraanModel> Kendaraan(@Header("Authorization") String authToken, @Path("parker_id") int parker_id, @Path("vehicle_id") int vehicle_id);

    @PUT("transactions/{id}")
    Call<SimpanKendaraanModel> SimpanKendaraan(@Header("Authorization") String authToken, @Path("id") int id, @Body SimpanKendaraanModel simpanKendaraanModel);

    @GET("transactions/{id}")
    Call<DetailModel> GetKendaraan(@Header("Authorization") String authToken, @Path("id") int transaction_id);

    @POST("logout/{parker}")
    Call<LogoutModel> SendLogout(@Header("Authorization") String authToken, @Path("parker") int parker_id);
}
