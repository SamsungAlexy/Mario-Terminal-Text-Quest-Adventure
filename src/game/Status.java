package game;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    FERTILE,          // this status indicate whether a ground is fertile
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    JUMP_CAPABLE, // use this to label that an actor can jump to high ground
    ACCESS_TO_FLOOR, // use this to restrict actor who can enter floor
    HOSTILE_TO_PLAYER, // use this status to be considered hostile toward player
    SUPER_MUSHROOM_EFFECT,  // use this effect to indicate actor on Super Mushroom Buff
    CONSUMED_POWER_STAR,  // use this to indicate recently consumed power star
    POWER_STAR_EFFECT, // use this effect to indicate actor on Power Star Buff
    POWER_STAR_DESTROY_GROUND, // use this effect to indicate actor can destroy ground
    HIDE_IN_SHELL, // this is used by koopa to show that it cannot dies before destroying its shell after koopa is unconscious
    RESET_NOW, // use this to show that the class attribute can be reset
    BREAK_SHELL, // use this to show that the item can break koopa's shell
    ACCESS_TO_LAVA, // use this to indicate actor can step on lava
    CONSUMED_POWER_WATER, // use this to indicate actor recently drank Power Water
    FIRE_ATTACK_EFFECT,// use this effect to indicate player can use fire attack
    CONSUMED_FIRE_FLOWER,// use this effect to indicate player recently consumed fire flower
    CANNOT_FOLLOW_PLAYER, // use this effect to indicate the actor is not capable to follow player
    FLY_CAPABLE, // use this to indicate an actor can enter to high ground without jump action
    FREE_CAPTIVE, // use by key to free princess
}
