package com.eg.SpectralProjection.gui.client.screen.element;

import com.eg.SpectralProjection.gui.client.screen.ScreenBase;

/**
 * Created by Creysys on 24 May 15.
 */
public abstract class ElementBase {

    ScreenBase screen;

    public void add(ScreenBase screen){
        this.screen = screen;
    }

    public void drawForeground(int mx, int my){}
    public void drawBackground(int mx , int my){}
    public void init() {}
}
