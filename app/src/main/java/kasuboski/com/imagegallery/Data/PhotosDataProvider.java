package kasuboski.com.imagegallery.Data;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class PhotosDataProvider {
    public interface GetPhotosCallback {
        void onPhotosReceived(List<Photo> photos);
        void onError();
    }

    private static final String TAG = PhotosDataProvider.class.getSimpleName();

    private List<Photo> photos = new ArrayList<>();

    public void getPhotos(final int start, final int limit, @NonNull final GetPhotosCallback callback) {
        if (photos.isEmpty()) {
            API.service.getPhotos().enqueue(new Callback<List<Photo>>() {
                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    if (response.body() != null) {
                        photos = response.body();
                        callback.onPhotosReceived(getSubsetOfPhotos(start, limit));
                    } else {
                        Log.e(TAG, "Response body was null");
                        callback.onError();
                    }

                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {
                    Log.e(TAG, "Error getting photos", t);
                    callback.onError();
                }
            });
        } else {
            callback.onPhotosReceived(getSubsetOfPhotos(start, limit));
        }
    }

    private List<Photo> getSubsetOfPhotos(int start, int limit) {
        if (photos != null) {
            return photos.subList(start, start + limit);
        } else {
            return null;
        }
    }
}
