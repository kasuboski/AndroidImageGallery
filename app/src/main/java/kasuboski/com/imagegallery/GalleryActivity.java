package kasuboski.com.imagegallery;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GalleryActivity extends AppCompatActivity {
    private static final String GALLERY_VM_TAG = "GALLERY_VM_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryFragment galleryFragment = findOrCreateViewFragment();
        GalleryViewModel viewModel = findOrCreateViewModel();

        galleryFragment.setViewModel(viewModel);
    }

    @NonNull
    private GalleryFragment findOrCreateViewFragment() {
        GalleryFragment galleryFragment = (GalleryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (galleryFragment == null) {
            galleryFragment = GalleryFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    galleryFragment, R.id.contentFrame);
        }
        return galleryFragment;
    }

    @NonNull
    private GalleryViewModel findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present. It's retained using the
        // Fragment Manager.
        @SuppressWarnings("unchecked")
        ViewModelHolder<GalleryViewModel> retainedViewModel =
                (ViewModelHolder<GalleryViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(GALLERY_VM_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            GalleryViewModel viewModel = new GalleryViewModel();

            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    GALLERY_VM_TAG);
            return viewModel;
        }
    }
}
