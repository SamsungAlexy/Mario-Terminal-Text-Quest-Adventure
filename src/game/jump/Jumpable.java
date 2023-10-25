package game.jump;

/**
 * An interface to represent ground that allows actor to perform a jump action.
 */
public interface Jumpable {

    /**
     * set this instance jump success rate
     * @param successRate integer
     */
    void setJumpSuccessRate(int successRate);

    /**
     * set this instance fall damage if jump action failed
     * @param fallDamage integer
     */
    void setFallDamage(int fallDamage);

    /**
     * Returns jump success rate
     * @return integer value of success rate
     */
    int getJumpSuccessRate();

    /**
     * Returns fall damage
     * @return integer value of fall damage
     */
    int getFallDamage();

}
