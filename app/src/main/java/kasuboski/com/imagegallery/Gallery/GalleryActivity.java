package kasuboski.com.imagegallery;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GalleryActivity extends AppCompatActivity implements ImageNavigator {
    private static final String GALLERY_VM_TAG = "GALLERY_VM_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GalleryFragment galleryFragment = findOrCreateViewFragment();
        GalleryViewModel viewModel = findOrCreateViewModel();
        viewModel.setNavigator(this);

        galleryFragment.setViewModel(viewModel);
    }

    @Override
    public void navigateToFullImage(@Nullable String imageUrl) {
        startActivity(ImageDetailActivity.createIntent(this, imageUrl));
    }

    @NonNull
    private GalleryFragment findOrCreateViewFragment() {
        GalleryFragment galleryFragment = (GalleryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.flContent);
        if (galleryFragment == null) {
            galleryFragment = GalleryFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    galleryFragment, R.id.flContent);
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
