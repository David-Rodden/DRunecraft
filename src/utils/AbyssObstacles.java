package utils;

import org.rspeer.runetek.api.component.tab.Skill;

public enum AbyssObstacles {
    ROCK("Mine", Skill.MINING), TENDRILS("Chop", Skill.WOODCUTTING), EYES("Distract", Skill.THIEVING), GAP("Squeeze-through", Skill.AGILITY), PASSAGE("Go-through", null);
    private final String action;
    private final Skill skill;

    AbyssObstacles(final String action, final Skill skill) {
        this.action = action;
        this.skill = skill;
    }

    public String getAction() {
        return action;
    }

    public static AbyssObstacles[] getMiningLoadout() {
        return new AbyssObstacles[]{ROCK, EYES, GAP, PASSAGE};
    }

    public static AbyssObstacles[] getWoodcuttingLoadout() {
        return new AbyssObstacles[]{TENDRILS, EYES, GAP, PASSAGE};
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
