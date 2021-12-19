package retrofit.api;

import okhttp3.ResponseBody;
import retrofit.dto.ProductDto;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MiniMarketApi {

    @GET("market/api/v1/products")
    Call<List<ProductDto>> getProduct();

    @GET("market/api/v1/products/{id}")
    Call<ProductDto> getProduct(@Path("id") long id);

    @POST("market/api/v1/products")
    Call<ProductDto> createProduct(@Body ProductDto product);

    @PUT("market/api/v1/products")
    Call<ProductDto> updateProduct(@Body ProductDto product);

    @DELETE("market/api/v1/products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") long id);
}
