package someRPG;

import java.awt.Rectangle;

public interface Killable {

    void decHealth(int value);

    void incHealth(int value);

    void meleeAttack(Killable target, int value);

    void specialAttack(Killable target, int value);

    int getHealth();

    String getName();

    void setHealth(int value);

    boolean deadCheck();

    int getBaseDamage();
    
    ID getId();
    
    void attack(Killable target, Skill skill);
    

}
