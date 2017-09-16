package kasuboski.com.imagegallery.ImageDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kasuboski.com.imagegallery.R;

public class ImageDetailActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";

    public static Intent createIntent(Context context, String imageUrl) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        intent.putExtra(EXTRA_IMAGE_URL, imageUrl);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        ImageView imageView = findViewById(R.id.ivImageFull);

        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(this).load(imageUrl)
                    .into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // make up the same as back
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }
}
