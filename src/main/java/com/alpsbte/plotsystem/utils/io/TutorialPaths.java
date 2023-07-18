/*
 * The MIT License (MIT)
 *
 *  Copyright © 2023, Alps BTE <bte.atchli@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.alpsbte.plotsystem.utils.io;

public abstract class TutorialPaths {
    public static final String TUTORIAL_ID = "tutorial-id";
    public static final String SPAWN_COORDINATES = "spawn-coordinates";
    public static final String SPAWN_COORDINATES_YAW = "spawn-coordinates-yaw";
    public static final String SPAWN_COORDINATES_PITCH = "spawn-coordinates-pitch";
    public static final String PLOT_OUTLINES = "plot-outlines";


    public static final class Beginner {
        private static final String BEGINNER = "beginner.";
        private static final String TELEPORT_COORDINATES = BEGINNER + "teleport-coordinates.";
        public static final String POINT_1 = TELEPORT_COORDINATES + "point-1";
        public static final String POINT_2 = TELEPORT_COORDINATES + "point-2";
        public static final String POINT_3 = TELEPORT_COORDINATES + "point-3";
        public static final String POINT_4 = TELEPORT_COORDINATES + "point-4";
        public static final String HEIGHT = BEGINNER + "height";
        public static final String HEIGHT_OFFSET = BEGINNER + "height-offset";
    }
}
