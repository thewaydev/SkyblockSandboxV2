package xyz.fragbots.sandboxcore.utils.damage;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity;

public class SkyblockMagicDamageEvent extends Event {
    private final Player player;
    private final SkyblockEntity entity;
    private Double baseAbilityDamage;
    private Double multipler;
    private Double enchantMultiplier;
    private boolean isCancelled;
    public SkyblockMagicDamageEvent(Player player, SkyblockEntity entity, Double baseAbilityDamage, Double multiplier, Double enchantMultiplier) {
        this.player = player;
        this.entity = entity;
        this.baseAbilityDamage = baseAbilityDamage;
        this.multipler = multiplier;
        this.enchantMultiplier = enchantMultiplier;
        this.isCancelled = false;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Double getBaseAbilityDamage() {
        return baseAbilityDamage;
    }

    public Double getMultipler() {
        return multipler;
    }

    public Double getEnchantMultiplier() {
        return enchantMultiplier;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setBaseAbilityDamage(Double baseAbilityDamage) {
        this.baseAbilityDamage = baseAbilityDamage;
    }

    public void setMultipler(Double multipler) {
        this.multipler = multipler;
    }

    public void setEnchantMultiplier(Double enchantMultiplier) {
        this.enchantMultiplier = enchantMultiplier;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
