package com.bitcodeing.framework.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import com.bitcodeing.framework.R;
import com.bitcodeing.framework.enums.FontType;

/**
 * Keylimetie custom TextView view class
 *
 * @author Luis Hernández
 * @version 0.0.1
 */
public class TextView extends android.widget.TextView {

    public TextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            setDefaultTypeface(context);
        }
    }

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    public TextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (!isInEditMode()) {
            parseAttributes(context, attrs);
        }
    }

    /**
     * Parse asset font type
     *
     * @param context view context
     * @param attrs   attribute set
     */
    public void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.Text);
        FontType robotoType = FontType.values()
                [values.getInt(R.styleable.Text_fontType, FontType.getDefault().ordinal())];
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), robotoType.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
        values.recycle();
    }


    /**
     * Set default typeface
     *
     * @param context view context
     */
    public void setDefaultTypeface(Context context) {
        Typeface typeface = Typeface
                .createFromAsset(context.getAssets(), FontType.getDefault().getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }

    public void setAssetFontType(FontType assetFontType) {
        Typeface typeface = Typeface
                .createFromAsset(getContext().getAssets(), assetFontType.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }
}
