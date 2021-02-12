package com.sg.dp.amazon;

import com.sg.dp.log.Logger;

import java.util.Arrays;

/**
 * Use command patttern to display object oriented skills
 * LeftCommand, RightCommnd,...,nullCommnad
 */
public class RoverControl {
    public static void main(String[] args) {
        RoverControl rc = new RoverControl();
        Logger.stdout(rc.getFinalPosition(4,
                new String[] {"RIGHT", "UP", "DOWN", "LEFT", "DOWN", "DOWN"}));
    }

    public int getFinalPosition(int n, String [] cmds) {
        int [] position = new int[2];
        for (String cmd: cmds) {
            position = getCommandResult(position[0], position[1], cmd, n);
            Logger.stdout(Arrays.toString(position));
        }

        return position[0] * n + position[1];
    }

    private int[] getCommandResult(int i, int j, String cmd, int n) {
        int oldi  = i, oldj = j;
        switch (Command.valueOf(cmd)) {
            case DOWN:  i++;
                        break;
            case UP:    i--;
                        break;
            case LEFT:  j--;
                        break;
            case RIGHT:  j++;
                         break;
        }

        int [] new_position = new int[2];
        if (isValid(i, j, n)) {
            new_position[0] = i; new_position[1] = j;
        } else {
            new_position[0] = oldi; new_position[1] = oldj;
        }

        return new_position;
    }

    public boolean isValid(int i, int j, int n) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}

enum Command {
    RIGHT, DOWN, LEFT, UP;
}
