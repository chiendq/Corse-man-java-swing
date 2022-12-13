package a2_1901040037.utils;

import java.awt.*;

public class DimensionFactory {

    private static Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();


    public static Dimension getScreenSizeScale(int scale){
        return new Dimension(screenResolution.width/scale, screenResolution.height/scale);
    }

    public static Dimension baseScreenScale(){
        return getScreenSizeScale(2);
    }
}
