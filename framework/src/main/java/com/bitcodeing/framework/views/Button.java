package com.bitcodeing.framework.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.bitcodeing.framework.R;
import com.bitcodeing.framework.enums.FontType;

/**
 *
 * @author Luis Hernandez
 * @version 0.0.1
 *
 */
public class Button extends AppCompatButton {
    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode())
            parseAttributes(context, attrs);
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
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
        try {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.Button);
            FontType robotoType = FontType.values()
                    [values.getInt(R.styleable.Button_fontType, FontType.ROBOTO_REGULAR.ordinal())];
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), robotoType.getAssetPath());
            if (typeface != null) {
                setTypeface(typeface);
            }
            values.recycle();
        } catch (Exception ex) {
            ex.printStackTrace();
            setDefaultTypeface(context);
        }
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
