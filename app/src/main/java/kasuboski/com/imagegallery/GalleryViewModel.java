package kasuboski.com.imagegallery;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryViewModel {
    public final ObservableList<String> imageUrls = new ObservableArrayList<>();

    private WeakReference<ImageNavigator> imageNavigator;

    public void start() {
        imageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg");
        imageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg");
        imageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg");
        imageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg");
    }

    public void imageClicked(int position) {
        if (imageNavigator != null) {
            imageNavigator.get().navigateToFullImage(imageUrls.get(position));
        }
    }

    public void setNavigator(@NonNull ImageNavigator navigator) {
        this.imageNavigator = new WeakReference<>(navigator);
    }
}
