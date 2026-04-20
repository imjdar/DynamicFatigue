package com.jdar.dynamicFatigue;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class FatigueManager {

    // Patrón Flyweight: Caché estática de efectos / Flyweight Pattern cache
    private final List<List<PotionEffect>> fatigueStages = new ArrayList<>();
    private final PotionEffect criticalDamageEffect;

    public FatigueManager() {
        buildFlyweightCache();
        // Nivel 4: Efecto crítico letal (Día 13+) / Critical Damage (Wither)
        criticalDamageEffect = new PotionEffect(PotionEffectType.WITHER, 120, 0, false, false, true);
    }

    private void buildFlyweightCache() {
        // Pre-instanciamos la escalabilidad de los 12 días / Initialize 12 stages
        for (int i = 0; i <= 12; i++) {
            List<PotionEffect> stageEffects = new ArrayList<>();

            if (i >= 1) stageEffects.add(new PotionEffect(PotionEffectType.HUNGER, 120, 0, false, false, true));
            if (i >= 3) stageEffects.add(new PotionEffect(PotionEffectType.SLOWNESS, 120, 0, false, false, true));
            if (i >= 5) stageEffects.add(new PotionEffect(PotionEffectType.WEAKNESS, 120, 0, false, false, true));
            if (i >= 7) stageEffects.add(new PotionEffect(PotionEffectType.MINING_FATIGUE, 120, 0, false, false, true));
            if (i >= 9) stageEffects.add(new PotionEffect(PotionEffectType.BLINDNESS, 120, 0, false, false, true));
            if (i >= 11) stageEffects.add(new PotionEffect(PotionEffectType.DARKNESS, 120, 0, false, false, true));

            fatigueStages.add(stageEffects);
        }
    }

    public List<PotionEffect> getEffectsForStage(int stage) {
        int safeStage = Math.min(stage, 12);
        return fatigueStages.get(safeStage);
    }

    public PotionEffect getCriticalDamageEffect() {
        return criticalDamageEffect;
    }
}