package kasuboski.com.imagegallery;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasuboski.com.imagegallery.databinding.FragmentGalleryBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance() {
        GalleryFragment fragment = new GalleryFragment();
        return fragment;
    }

    FragmentGalleryBinding galleryBinding;
    GalleryViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        galleryBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_gallery, container, false);

        return galleryBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        galleryBinding.setViewModel(viewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.start();
    }

    public void setViewModel(GalleryViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
