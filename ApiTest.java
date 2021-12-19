import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit.api.MiniMarketApi;
import retrofit.dto.Category;
import retrofit.dto.ProductDto;
import retrofit.utils.RetrofitGetter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    private final MiniMarketApi api;

    public ApiTest() throws IOException {
        Retrofit retrofit = new RetrofitGetter().getInstance();
        api = retrofit.create(MiniMarketApi.class);
    }

    @Test
    @DisplayName("Создание продукта")
    void testCreateProduct() throws IOException {

        ProductDto dto = ProductDto.builder()
                .title("Water")
                .categoryTitle(Category.FOOD.getTitle())
                .price(15L)
                .build();

        Response<ProductDto> response = api.createProduct(dto).execute();
        Long id = response.body().getId();

        ProductDto actually = api.getProduct(id).execute().body();

        assertEquals(dto.getTitle(), actually.getTitle());
        assertEquals(dto.getPrice(), actually.getPrice());
        assertEquals(dto.getCategoryTitle(), actually.getCategoryTitle());

        api.deleteProduct(id).execute();
    }

    @Test
    @DisplayName("Получение продукта с несуществующим id")
    void testGetProductWithWrongId() {
        ProductDto dto = ProductDto.builder()
                .id(10L)
                .title("Milk")
                .categoryTitle(Category.FOOD.getTitle())
                .price(95L)
                .build();

        try {
            Response<ProductDto> response = api.getProduct(dto.getId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new AssertionError("Unable to find product with id: " + dto.getId());
    }

    @Test
    @DisplayName("Удаление продукта")
    void testDeleteProduct() {

        ProductDto dto = ProductDto.builder()
                .title("Potato")
                .categoryTitle(Category.FOOD.getTitle())
                .price(25L)
                .build();

        Response<ProductDto> response = null;
        try {
            response = api.createProduct(dto).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Long id = response.body().getId();

        try {
            api.deleteProduct(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            api.getProduct(id).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new AssertionError("Unable to find product with id: " + dto.getId());
    }
}
