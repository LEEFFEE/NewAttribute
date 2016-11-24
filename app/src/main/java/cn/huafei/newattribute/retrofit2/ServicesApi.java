package cn.huafei.newattribute.retrofit2;

import cn.huafei.newattribute.bean.Classify;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface ServicesApi {
    @GET("api/info/{list}/")
    Call<RequestBody> getHealthList(@Path("list") String list, @Query("page") int page, @Query("rows") int rows);

    @GET("api/info/classify")
    Call<Classify> getClassify();

    @GET("api/info/classify")
    Observable<Classify> getRxClassify();


}
