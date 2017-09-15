package kasuboski.com.imagegallery;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kasuboski.com.imagegallery.databinding.LayoutRowItemBinding;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private ObservableList<String> strings;

    public GalleryAdapter(ObservableList<String> strings) {
        this.strings = strings;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row_item, parent, false);

        LayoutRowItemBinding binding = DataBindingUtil.bind(v);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {
        String item = strings.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LayoutRowItemBinding binding;

        public ViewHolder(LayoutRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String text) {
            binding.setText(text);
            binding.executePendingBindings();
        }
    }
}
