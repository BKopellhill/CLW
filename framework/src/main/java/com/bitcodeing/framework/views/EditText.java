package com.bitcodeing.framework.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.bitcodeing.framework.R;
import com.bitcodeing.framework.enums.FontType;

/**
 * Custom Edit Text
 *
 * @author Luis Hernández
 * @version 1.0
 */
public class EditText extends AppCompatEditText {

    public EditText(Context context) {
        super(context);
    }

    public EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    public EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    /**
     * Parse roboto typeface
     *
     * @param context view context
     * @param attrs   attribute set
     */
    public void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.EditText);
        FontType robotoType = FontType.values()[values.getInt(R.styleable.EditText_fontType, FontType.ROBOTO_REGULAR.ordinal())];
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
                .createFromAsset(context.getAssets(), FontType.ROBOTO_REGULAR.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }

    /**
     * Set custom font to this view
     *
     * @param robotoType FontType to change
     */
    public void setFontType(FontType robotoType) {
        Typeface typeface = Typeface
                .createFromAsset(getContext().getAssets(), robotoType.getAssetPath());
        if (typeface != null) {
            setTypeface(typeface);
        }
    }
}
