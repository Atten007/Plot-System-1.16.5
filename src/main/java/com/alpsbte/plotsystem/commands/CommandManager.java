/*
 * The MIT License (MIT)
 *
 *  Copyright © 2021, Alps BTE <bte.atchli@gmail.com>
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

package com.alpsbte.plotsystem.commands;

import com.alpsbte.plotsystem.PlotSystem;
import com.alpsbte.plotsystem.commands.admin.CMD_CleanPlot;
import com.alpsbte.plotsystem.commands.admin.CMD_DeletePlot;
import com.alpsbte.plotsystem.commands.admin.CMD_PReload;
import com.alpsbte.plotsystem.commands.admin.CMD_SetHologram;
import com.alpsbte.plotsystem.commands.plot.CMD_Plot;
import com.alpsbte.plotsystem.commands.review.CMD_EditPlot;
import com.alpsbte.plotsystem.commands.review.CMD_Review;
import com.alpsbte.plotsystem.commands.review.CMD_SendFeedback;
import com.alpsbte.plotsystem.commands.review.CMD_UndoReview;

import java.util.Arrays;
import java.util.List;

public class CommandManager {

    public List<BaseCommand> baseCommands = Arrays.asList(
            new CMD_Companion(),
            new CMD_Spawn(),
            new CMD_Tpp(),
            new CMD_Plot(),
            new CMD_Tpll(),
            new CMD_Plots(),
            new CMD_EditPlot(),
            new CMD_Review(),
            new CMD_SendFeedback(),
            new CMD_UndoReview(),
            new CMD_CleanPlot(),
            new CMD_DeletePlot(),
            new CMD_PReload(),
            new CMD_SetHologram()
    );

    public void init() {
        for (BaseCommand baseCmd : baseCommands) {
            for (String baseName : baseCmd.getNames()) {
                PlotSystem.getPlugin().getCommand(baseName).setExecutor(baseCmd);
            }
        }
    }

    public List<BaseCommand> getBaseCommands() {
        return baseCommands;
    }
}
