package xyz.fragbots.sandboxcore.utils.damage;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import xyz.fragbots.sandboxcore.entitites.SkyblockEntity;

/*
 * Event Called before all PVE damage (For damage calculation and cancelling)
 * Coded in java for better hooking into bukkit
 */
public class SkyblockMeleeDamageEvent extends Event {
    private final Player player;
    private final SkyblockEntity entity;
    private boolean isCrit;
    private Double inititalDamage;
    private Double damageMultiplier;
    private Double armorMultiplier;
    private boolean isCancelled;
    public SkyblockMeleeDamageEvent(Player player, SkyblockEntity entity, Boolean isCrit, Double initialDamage, Double damageMultiplier, Double armorMultiplier) {
        this.player = player;
        this.entity = entity;
        this.isCrit = isCrit;
        this.inititalDamage = initialDamage;
        this.damageMultiplier = damageMultiplier;
        this.armorMultiplier = armorMultiplier;
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

    public boolean isCrit() {
        return isCrit;
    }

    public Double getInititalDamage() {
        return inititalDamage;
    }

    public Double getDamageMultiplier() {
        return damageMultiplier;
    }

    public Double getArmorMultiplier() {
        return armorMultiplier;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCrit(boolean crit) {
        isCrit = crit;
    }

    public void setInititalDamage(Double inititalDamage) {
        this.inititalDamage = inititalDamage;
    }

    public void setDamageMultiplier(Double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public void setArmorMultiplier(Double armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
