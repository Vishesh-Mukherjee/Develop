package com.gdgu.mvc.panel;

import javax.swing.JPanel;

public abstract class AbstractPanel extends JPanel {
    protected boolean air = false;

    public abstract void setAir(boolean air);
    public abstract boolean getAir();
}
