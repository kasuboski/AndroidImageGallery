package kasuboski.com.imagegallery.Gallery;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import kasuboski.com.imagegallery.Data.Photo;
import kasuboski.com.imagegallery.Data.PhotosDataProvider;
import kasuboski.com.imagegallery.ImageDetail.ImageNavigator;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryViewModel {
    public static final int PHOTOS_PER_PAGE = 30;

    public final ObservableList<String> imageUrls = new ObservableArrayList<>();

    private WeakReference<ImageNavigator> imageNavigator;
    private PhotosDataProvider photosDataProvider;

    public GalleryViewModel(@NonNull PhotosDataProvider photosDataProvider) {
        this.photosDataProvider = photosDataProvider;
    }

    public void start() {
        if (imageUrls.isEmpty()) {
            // only load photos if haven't before
            photosDataProvider.getPhotos(0, PHOTOS_PER_PAGE, new PhotosDataProvider.GetPhotosCallback() {
                @Override
                public void onPhotosReceived(List<Photo> photos) {
                    handlePhotosReturned(photos);
                }

                @Override
                public void onError() {
                    handleError();
                }
            });
        }
    }

    public void loadMorePhotos(int page) {
        int start = page * PHOTOS_PER_PAGE;
        photosDataProvider.getPhotos(start, PHOTOS_PER_PAGE, new PhotosDataProvider.GetPhotosCallback() {
            @Override
            public void onPhotosReceived(List<Photo> photos) {
                handlePhotosReturned(photos);
            }

            @Override
            public void onError() {
                handleError();
            }
        });
    }

    private void handlePhotosReturned(List<Photo> photos) {
        if (photos != null) {
            for (Photo photo : photos) {
                String url = photo.url;
                if (!TextUtils.isEmpty(url)) {
                    imageUrls.add(url);
                }
            }
        }
    }

    private void handleError() {
        //normally you'd want to show some sort of error
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
