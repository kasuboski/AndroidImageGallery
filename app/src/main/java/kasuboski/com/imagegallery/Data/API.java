package kasuboski.com.imagegallery.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class API {
    public interface PhotosService {
        @GET("/photos")
        Call<List<Photo>> getPhotos();
    }

    public static final PhotosService service = new Retrofit.Builder()
                                        .baseUrl("https://jsonplaceholder.typicode.com/")
                                        .addConverterFactory(MoshiConverterFactory.create())
                                        .build().create(PhotosService.class);
}
