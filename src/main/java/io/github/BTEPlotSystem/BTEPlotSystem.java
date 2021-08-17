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

package github.BTEPlotSystem;

import com.onarandombox.MultiverseCore.MultiverseCore;
import github.BTEPlotSystem.commands.*;
import github.BTEPlotSystem.commands.admin.CMD_DeletePlot;
import github.BTEPlotSystem.commands.admin.CMD_PReload;
import github.BTEPlotSystem.commands.admin.CMD_SetHologram;
import github.BTEPlotSystem.commands.admin.setup.*;
import github.BTEPlotSystem.commands.plot.*;
import github.BTEPlotSystem.commands.review.*;
import github.BTEPlotSystem.core.database.DatabaseConnection;
import github.BTEPlotSystem.core.EventListener;
import github.BTEPlotSystem.core.config.ConfigManager;
import github.BTEPlotSystem.core.config.ConfigNotImplementedException;
import github.BTEPlotSystem.core.holograms.*;
import github.BTEPlotSystem.core.system.plot.PlotManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class BTEPlotSystem extends JavaPlugin {
    // Plugins
    private static BTEPlotSystem plugin;
    private static MultiverseCore multiverseCore;

    // Config
    private ConfigManager configManager;

    // Holograms
    private static final List<HolographicDisplay> holograms = Arrays.asList(
      new ScoreLeaderboard(),
      new PlotsLeaderboard()
    );

    @Override
    public void onEnable() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog"); // Disable Logging

        plugin = this;
        multiverseCore = (MultiverseCore) getServer().getPluginManager().getPlugin("Multiverse-Core");

        try {
            configManager = new ConfigManager();
        } catch (ConfigNotImplementedException ex) {
            return;
        }

        reloadConfig();

        // Initialize database
        DatabaseConnection.InitializeDatabase();

        // Add listeners
        this.getServer().getPluginManager().registerEvents(new EventListener(), plugin);
        this.getServer().getPluginManager().registerEvents(new MenuFunctionListener(), plugin);

        // Add default commands [No Permissions]
        this.getCommand("spawn").setExecutor(new CMD_Spawn());
        this.getCommand("hub").setExecutor(new CMD_Spawn());
        this.getCommand("tpp").setExecutor(new CMD_Tpp());
        this.getCommand("tpll").setExecutor(new CMD_Tpll());

        // Add plot commands [alpsbte.plot Permission]
        this.getCommand("companion").setExecutor(new CMD_Companion());
        this.getCommand("link").setExecutor(new CMD_Links());
        this.getCommand("submit").setExecutor(new CMD_Submit());
        this.getCommand("abandon").setExecutor(new CMD_Abandon());
        this.getCommand("undosubmit").setExecutor(new CMD_UndoSubmit());
        this.getCommand("feedback").setExecutor(new CMD_Feedback());
        this.getCommand("plots").setExecutor(new CMD_Plots());
        this.getCommand("tpll").setExecutor(new CMD_Tpll());
        this.getCommand("invite").setExecutor(new CMD_Invite());

        // Add reviewer commands [alpsbte.review Permission]
        this.getCommand("plot").setExecutor(new CMD_Plot());
        this.getCommand("review").setExecutor(new CMD_Review());
        this.getCommand("undoreview").setExecutor(new CMD_UndoReview());
        this.getCommand("sendfeedback").setExecutor(new CMD_SendFeedback());
        this.getCommand("editplot").setExecutor(new CMD_EditPlot());

        // Add admin commands [alpsbte.admin Permission]
        this.getCommand("deleteplot").setExecutor(new CMD_DeletePlot());
        this.getCommand("generateplot").setExecutor(new CMD_GeneratePlot());
        this.getCommand("sethologram").setExecutor(new CMD_SetHologram());
        this.getCommand("preload").setExecutor(new CMD_PReload());

        // Setup Commands
        CMD_Setup setupCommand = new CMD_Setup();
        setupCommand.registerCommand("server", new CMD_Setup_Server());
        setupCommand.registerCommand("ftp", new CMD_Setup_FTP());
        setupCommand.registerCommand("country", new CMD_Setup_Country());
        setupCommand.registerCommand("city", new CMD_Setup_City());
        this.getCommand("pss").setExecutor(setupCommand);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        reloadHolograms();

        PlotManager.checkPlotsForLastActivity();

        getLogger().log(Level.INFO, "Successfully enabled AlpsBTE-PlotSystem plugin.");
    }

    @Override
    public FileConfiguration getConfig() {
        return this.configManager.getConfig();
    }

    @Override
    public void reloadConfig() {
        this.configManager.reloadConfig();
    }

    @Override
    public void saveConfig() {
        this.configManager.saveConfig();
    }

    public static void reloadHolograms() {
        for (HolographicDisplay hologram : holograms) {
            if(getPlugin().getConfig().getBoolean(hologram.getDefaultPath() + "enabled")) {
                hologram.show();
            } else {
                hologram.hide();
            }
        }
    }

    public static BTEPlotSystem getPlugin() {
        return plugin;
    }

    public static MultiverseCore getMultiverseCore() { return multiverseCore; }

    public static List<HolographicDisplay> getHolograms() { return holograms; }
}
