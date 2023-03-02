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

package com.alpsbte.plotsystem.core.system.tutorial;

import com.alpsbte.plotsystem.core.system.Builder;
import com.alpsbte.plotsystem.utils.io.language.LangPaths;
import com.alpsbte.plotsystem.utils.io.language.LangUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeginnerTutorial extends AbstractTutorial {

    @Override
    protected List<Class<? extends AbstractStage>> setStages() {
        return Collections.singletonList(
                Stage1.class
        );
    }

    public BeginnerTutorial(Builder builder) {
        super(builder);
    }

    private static class Stage1 extends AbstractStage {
        public Stage1(Builder builder) {
            super(builder);
        }

        @Override
        protected boolean performStage() {
            builder.getPlayer().sendMessage("Send /companion command!");
            return true;
        }

        @Override
        List<String> getMessages() {
            return Arrays.asList(
                    LangUtil.get(builder.getPlayer(), LangPaths.Tutorials.TUTORIALS_BEGINNER_STAGE1_1),
                    LangUtil.get(builder.getPlayer(), LangPaths.Tutorials.TUTORIALS_BEGINNER_STAGE1_2),
                    LangUtil.get(builder.getPlayer(), LangPaths.Tutorials.TUTORIALS_BEGINNER_STAGE1_3),
                    LangUtil.get(builder.getPlayer(), LangPaths.Tutorials.TUTORIALS_BEGINNER_STAGE1_4),
                    LangUtil.get(builder.getPlayer(), LangPaths.Tutorials.TUTORIALS_BEGINNER_STAGE1_5)
            );
        }

        @Override
        public void onPlayerCommandInputEvent(String command) {
            if (command.startsWith("companion")) {
                builder.getPlayer().sendMessage("Received /companion command!");
                isDone = true;
            }
        }
    }
}
