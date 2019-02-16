package utils;

public enum AbyssLoadouts {
    MINING_LOADOUT(AbyssObstacles.getMiningLoadout()), WOODCUTTING_LOADOUT(AbyssObstacles.getWoodcuttingLoadout()), PEACEFUL_LOADOUT(AbyssObstacles.getPeacefulLoadout());
    private final AbyssObstacles[] obstacles;

    AbyssLoadouts(final AbyssObstacles[] obstacles) {
        this.obstacles = obstacles;
    }

    public AbyssObstacles[] getObstacles() {
        return obstacles;
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase().replaceAll("_", " ");
    }
}
