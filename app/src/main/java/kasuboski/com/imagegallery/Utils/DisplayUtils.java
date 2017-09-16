package kasuboski.com.imagegallery.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Josh Kasuboski on 9/16/17.
 */

public class DisplayUtils {
    public static int calculateColumnWidthPixels(Context context, int numColumns) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        float px = dpWidth * ((float)displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        return (int) (px / numColumns);
    }
}
