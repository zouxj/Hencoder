package com.zxj.hencoder06;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * function:
 *
 * <p>
 * Created by Zxj on 2019/9/12.
 */
public class Utils {
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
