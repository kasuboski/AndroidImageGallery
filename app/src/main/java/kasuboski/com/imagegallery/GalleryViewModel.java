package kasuboski.com.imagegallery;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryViewModel {
    public final ObservableList<String> text = new ObservableArrayList<>();

    public void start() {
        text.add("Hello");
        text.add("World");
        text.add("hey");
    }
}
