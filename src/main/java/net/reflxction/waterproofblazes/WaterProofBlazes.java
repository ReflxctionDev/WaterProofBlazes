/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.waterproofblazes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple plugin which prevents Blazes from taking damage from water or rain
 */
public final class WaterProofBlazes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Save the config
        saveResource("config.yml", false);
        // Register the event
        getServer().getPluginManager().registerEvents(new BlazeListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<World> getDisabledWorlds() {
        List<World> worlds = new ArrayList<>();
        List<String> sList = getConfig().getStringList("DisabledWorlds");
        try {
            sList.forEach(s -> worlds.add(Bukkit.getWorld(s)));
        } catch (NullPointerException ex) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Couldn't find the world specified in config.yml! Please ensure that it's spelled correctly and restart/reload the server after changing.");
        }
        return worlds;
    }

}