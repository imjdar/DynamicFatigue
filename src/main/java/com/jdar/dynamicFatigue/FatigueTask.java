package com.jdar.dynamicFatigue;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

public class FatigueTask extends BukkitRunnable {

    private final FatigueManager manager;
    private static final int TICKS_PER_DAY = 24000;

    public FatigueTask(FatigueManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        // Bucle de evaluación / Evaluation loop
        for (Player player : Bukkit.getOnlinePlayers()) {
            evaluatePlayer(player);
        }
    }

    private void evaluatePlayer(Player player) {
        // Lee la estadística nativa del jugador / Check native statistic
        int ticksSinceRest = player.getStatistic(Statistic.TIME_SINCE_REST);
        int daysWithoutSleep = ticksSinceRest / TICKS_PER_DAY;

        // 1. Actualiza la Interfaz en la Action Bar / Action Bar UI Update
        sendActionBarUI(player, daysWithoutSleep);

        // 2. Aplica los castigos / Apply Punishments
        if (daysWithoutSleep > 0) {
            applyFatigueEffects(player, daysWithoutSleep);
        }
    }

    private void sendActionBarUI(Player player, int days) {
        Component message;

        // Mapeo dinámico de texto y colores / Dynamic Text mapping
        if (days == 0) {
            message = Component.text("Estado: Bien Descansado", NamedTextColor.GREEN);
        } else if (days < 6) {
            message = Component.text("Fatiga Acumulada: Día " + days, NamedTextColor.YELLOW);
        } else if (days < 12) {
            message = Component.text("Exhaustión Severa: Día " + days, NamedTextColor.RED);
        } else {
            message = Component.text("PELIGRO: DAÑO CRÍTICO POR EXHAUSTIÓN", NamedTextColor.DARK_PURPLE)
                    .decorate(TextDecoration.BOLD);
        }

        // Envía el paquete visual encima de la barra de hambre / Send to Action Bar
        player.sendActionBar(message);
    }

    private void applyFatigueEffects(Player player, int days) {
        for (PotionEffect effect : manager.getEffectsForStage(days)) {
            player.addPotionEffect(effect);
        }

        if (days >= 13) {
            player.addPotionEffect(manager.getCriticalDamageEffect());
        }
    }
}