package a2_1901040037.utils;

import javax.swing.*;

import static a2_1901040037.utils.DimensionFactory.baseScreenScale;

public class FrameFactory{

    public static JFrame createBaseFrame(String frameName) {
        JFrame baseFrame = new JFrame(frameName);
        baseFrame.setSize(DimensionFactory.baseScreenScale());
        return baseFrame;
    }
}
