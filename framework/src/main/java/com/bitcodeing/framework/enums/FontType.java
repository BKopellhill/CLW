package com.bitcodeing.framework.enums;

/**
 * Custom fonts enumerable
 *
 * @author Luis Hernández
 * @version 1.0
 */
public enum FontType {

    ROBOTO_BLACK("fonts/roboto/Roboto-Black.ttf"),
    ROBOTO_BLACK_ITALIC("fonts/roboto/Roboto-BlackItalic.ttf"),
    ROBOTO_BOLD("fonts/roboto/Roboto-Bold.ttf"),
    ROBOTO_BOLD_ITALIC("fonts/roboto/Roboto-BoldItalic.ttf"),
    ROBOTO_ITALIC("fonts/roboto/Roboto-Italic.ttf"),
    ROBOTO_LIGHT("fonts/roboto/Roboto-Light.ttf"),
    ROBOTO_LIGHT_ITALIC("fonts/roboto/Roboto-LightItalic.ttf"),
    ROBOTO_MEDIUM("fonts/roboto/Roboto-Medium.ttf"),
    ROBOTO_MEDIUM_ITALIC("fonts/roboto/Roboto-MediumItalic.ttf"),
    ROBOTO_REGULAR("fonts/roboto/Roboto-Regular.ttf"),
    ROBOTO_THIN("fonts/roboto/Roboto-Thin.ttf"),
    ROBOTO_THIN_ITALIC("fonts/roboto/Roboto-ThinItalic.ttf"),
    SFUID_BLACK("fonts/sfuidisplay/SFUIDisplay-Black.otf"),
    SFUID_BOLD("fonts/sfuidisplay/SFUIDisplay-Bold.otf"),
    SFUID_HEAVY("fonts/sfuidisplay/SFUIDisplay-Heavy.otf"),
    SFUID_LIGHT("fonts/sfuidisplay/SFUIDisplay-Light.otf"),
    SFUID_MEDIUM("fonts/sfuidisplay/SFUIDisplay-Medium.otf"),
    SFUID_REGULAR("fonts/sfuidisplay/SFUIDisplay-Regular.otf"),
    SFUID_SEMI_BOLD("fonts/sfuidisplay/SFUIDisplay-Semibold.otf"),
    SFUID_THIN("fonts/sfuidisplay/SFUIDisplay-Thin.otf"),
    SFUID_ULTRA_LIGHT("fonts/sfuidisplay/SFUIDisplay-Ultralight.otf"),
    SFUIT_REGULAR("fonts/sfuitext/SFUIText-Regular.otf");

    private String path;

    FontType(String path) {
        this.path = path;
    }

    public String getAssetPath() {
        return path;
    }

    public static FontType getDefault() {
        return ROBOTO_REGULAR;
    }
}
