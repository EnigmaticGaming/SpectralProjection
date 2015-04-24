package com.eg.SpectralProjection.achievement;

import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.LinkedList;

/**
 * Created by Creysys on 23 Apr 15.
 */
public abstract class AchievementPageBase extends AchievementPage {
    @SuppressWarnings("unchecked")
    public AchievementPageBase(String name) {
        super(StatCollector.translateToLocalFormatted("achievement.page." + name));

        registerAchievements((LinkedList<Achievement>) ReflectionHelper.getPrivateValue(AchievementPage.class, this, "achievements"));

        AchievementPage.registerAchievementPage(this);
    }

    public abstract void registerAchievements(LinkedList<Achievement> achievements);
}
