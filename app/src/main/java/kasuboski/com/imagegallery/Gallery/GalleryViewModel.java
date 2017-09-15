package kasuboski.com.imagegallery.Gallery;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import kasuboski.com.imagegallery.Data.Photo;
import kasuboski.com.imagegallery.Data.PhotosDataProvider;
import kasuboski.com.imagegallery.ImageDetail.ImageNavigator;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryViewModel {
    public final ObservableList<String> imageUrls = new ObservableArrayList<>();

    private WeakReference<ImageNavigator> imageNavigator;
    private PhotosDataProvider photosDataProvider;

    public GalleryViewModel(@NonNull PhotosDataProvider photosDataProvider) {
        this.photosDataProvider = photosDataProvider;
    }

    public void start() {
        imageUrls.clear();
        photosDataProvider.getPhotos(0, 4, new PhotosDataProvider.GetPhotosCallback() {
            @Override
            public void onPhotosReceived(List<Photo> photos) {
                for (Photo photo : photos) {
                    String url = photo.url;
                    if (!TextUtils.isEmpty(url)) {
                        imageUrls.add(url);
                    }
                }
            }

            @Override
            public void onError() {
                // TODO
            }
        });

//        imageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg");
    }

    public void imageClicked(int position) {
        ImageNavigator navigator = imageNavigator.get();
        if (navigator != null) {
            navigator.navigateToFullImage(imageUrls.get(position));
        }
    }

    public void setNavigator(@NonNull ImageNavigator navigator) {
        this.imageNavigator = new WeakReference<>(navigator);
    }
}
