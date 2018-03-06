package someRPG;

public class Skill {

    public enum Type {
        SPELL,
        BUFF
    };

    private String mName, mDescription;
    private int mDamage, mMinDamage, mManaCost, mSkillCost, mStaminaCost;
    private boolean mKnown;
    private Type mType;

    public Skill (Type type, String name, String description, int damage, int manacost, int skillcost) {
        mType = type;
        mName = name;
        mDescription = description;
        mDamage = damage;
        mManaCost = manacost;
        mSkillCost = skillcost;
    }

    public String getName() {
        return this.mName;
    }

    public void setKnown(boolean value) {
        this.mKnown = value;
    }

    public boolean getKnown() {
        return this.mKnown;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getDamage() {
        return this.mDamage;
    }

    public int getMinDamage() {
        return this.mMinDamage;
    }

    public void setMinDamage(int value) {
        this.mMinDamage = value;
    }

    public int getManaCost() {
        return this.mManaCost;
    }

    public int getSkillCost() {
        return this.mSkillCost;
    }

    public Type getType() {
        return this.mType;
    }

    public void setType(Type value) {
        this.mType = value;
    }

	public int getmStaminaCost() {
		return mStaminaCost;
	}

	public void setmStaminaCost(int mStaminaCost) {
		this.mStaminaCost = mStaminaCost;
	}



}
