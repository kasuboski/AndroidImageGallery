package kasuboski.com.imagegallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

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
}
