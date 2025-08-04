package com.gdgu.mvc.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum State {

    COMPLETED('3', new Color(128, 245, 110)), IN_PROGRESS('2', new Color(252, 209, 106)),
    NOT_STARTED('1', new Color(255, 255, 255)), IRRELEVANT('0', new Color(43, 43, 43));

    private char charRepresentation;
    private Color associateColor;

    private static final List<State> states = Arrays.asList(COMPLETED, IN_PROGRESS, NOT_STARTED, IRRELEVANT);

    State(char charRepresentation, Color associateColor) {
        this.charRepresentation = charRepresentation;
        this.associateColor = associateColor;
    }

    public static Color getColor(char charRepresentation) {
        return getState(charRepresentation).getAssociateColor();
    }

    public static State getState(char charRepresentation) {
        return states.stream().filter(state -> state.getCharRepresentation() == charRepresentation).findAny()
                .orElse(State.IRRELEVANT);
    }
}
