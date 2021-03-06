package kasuboski.com.imagegallery.Gallery;

import android.content.Context;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import kasuboski.com.imagegallery.R;
import kasuboski.com.imagegallery.Utils.DisplayUtils;

/**
 * Created by Josh Kasuboski on 9/14/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    public interface ClickListener {
        void onItemClicked(int position);
    }

    private static final int NUM_COLUMNS = 3;

    private int imageSize;

    private ObservableList<String> imageUrls;
    private Context context;

    private ClickListener listener;

    public GalleryAdapter(Context context, ObservableList<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;

        WeakReferenceOnListChangedCallback onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        this.imageUrls.addOnListChangedCallback(onListChangedCallback);

        imageSize = DisplayUtils.calculateColumnWidthPixels(context, NUM_COLUMNS);
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_image_row_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {
        final int pos = position;
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClicked(pos);
                }
            }
        });
        String item = imageUrls.get(position);
        Picasso.with(context).load(item)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .fit()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback {

        private final WeakReference<GalleryAdapter> adapterReference;

        public WeakReferenceOnListChangedCallback(GalleryAdapter bindingRecyclerViewAdapter) {
            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            RecyclerView.Adapter adapter = adapterReference.get();
            if (adapter != null) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.ivImage);
            // set image size to avoid out of memory exception from Picasso
            // when loading a ton of images at once
            imageView.getLayoutParams().width = imageSize;
            imageView.getLayoutParams().height = imageSize;
        }
    }
}
