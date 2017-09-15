package kasuboski.com.imagegallery;


import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kasuboski.com.imagegallery.databinding.FragmentGalleryBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    FragmentGalleryBinding galleryBinding;
    GalleryViewModel viewModel;

    RecyclerView recyclerView;

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
        View root = galleryBinding.getRoot();

        recyclerView = root.findViewById(R.id.rvGallery);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        GalleryAdapter galleryAdapter = new GalleryAdapter(viewModel.text);
        recyclerView.setAdapter(galleryAdapter);

        viewModel.text.add("New");

        super.onViewCreated(view, savedInstanceState);
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

    public GalleryFragment() {
        // Required empty public constructor
    }
}
