package kasuboski.com.imagegallery;

import android.databinding.ObservableField;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryViewModel {
    public final ObservableField<String> text = new ObservableField<>();

    public void start() {
        text.set("Hello World");
    }
}
