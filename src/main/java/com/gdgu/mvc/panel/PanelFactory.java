package com.gdgu.mvc.panel;

public class PanelFactory {
    
    private static NotebookPanel notebookPanel;
    private static ClockPanel clockPanel;
    private static StopwatchPanel watchPanel;
    private static TrackerPannel trackerPanel;
    private static TipPanel tipPanel;
    private static RequestPanel requestPanel;

    public static RequestPanel getRequestPanel() {
        if (requestPanel == null) {
            requestPanel = new RequestPanel();
        }
        return requestPanel;
    }

    public static TipPanel getTipPanel() {
        if (tipPanel == null) {
            tipPanel = new TipPanel();
        }
        return tipPanel;
    }

    public static NotebookPanel getNotebookPanel() {
        if (notebookPanel == null) {
            notebookPanel = new NotebookPanel();
        }
        return notebookPanel;
    }

    public static ClockPanel getClockPanel() {
        if (clockPanel == null) {
            clockPanel = new ClockPanel();
        }
        return clockPanel;
    }

    public static StopwatchPanel getStopWatchPanel() {
        if (watchPanel == null) {
            watchPanel = new StopwatchPanel();
        }
        return watchPanel;
    }

    public static TrackerPannel getTrackerPanel() {
        if (trackerPanel == null) {
            trackerPanel = new TrackerPannel(); 
        }
        return trackerPanel;
    }
}
