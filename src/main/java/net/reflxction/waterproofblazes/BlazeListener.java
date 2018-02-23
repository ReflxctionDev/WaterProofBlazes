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

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * The listener which detects blaze's damage and cancels it
 */

public class BlazeListener implements Listener {

    private WaterProofBlazes m;

    public BlazeListener(WaterProofBlazes m) {
        this.m = m;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        // Make sure blazes aren't being hit by another entity, which possibly makes them invincible against other entities when being in water lol
        if (!(event instanceof EntityDamageByEntityEvent)) {
            // An instance of the damaged entity
            final Entity entity = event.getEntity();
            // Checking if the damaged entity is a blaze
            if (entity instanceof Blaze) {
                // An instance of the damaged blaze
                Blaze b = (Blaze) event.getEntity();
                // Check if the damage cause is drowning, which is apparently fired when a blaze gets damaged by water/rain
                if (event.getCause() == EntityDamageEvent.DamageCause.DROWNING)
                    // Check if the blaze's world isn't in the disabled worlds
                    if (!m.getDisabledWorlds().contains(b.getWorld())) {
                        // Cancel the damage event
                        event.setCancelled(true);
                    }
            }
        }
    }
}
