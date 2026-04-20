package com.jdar.dynamicFatigue;

import org.bukkit.plugin.java.JavaPlugin;

public final class DynamicFatigue extends JavaPlugin {

    private FatigueManager fatigueManager;
    private FatigueTask fatigueTask;

    @Override
    public void onEnable() {
        // 1. Inicializa la Caché de Efectos / Initialize Flyweight Cache
        this.fatigueManager = new FatigueManager();

        // 2. Inicia el motor síncrono cada 100 ticks (5 segundos) / Start Task
        this.fatigueTask = new FatigueTask(fatigueManager);
        this.fatigueTask.runTaskTimer(this, 100L, 100L);

        getLogger().info("DynamicFatigue by JDAR enabled successfully! (Action Bar UI)");
    }

    @Override
    public void onDisable() {
        // Ya no necesitamos lógica de limpieza compleja gracias a la UI Stateless
        getLogger().info("DynamicFatigue disabled.");
    }
}