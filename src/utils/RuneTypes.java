package utils;


import org.rspeer.runetek.api.movement.position.Position;

public enum RuneTypes {
    AIR(556, 2841, 4830), MIND(558, 2788, 4837), WATER(555, 2721, 4834), EARTH(557, 2657, 4835), FIRE(554, 2583, 4841), BODY(559, 2521, 4844), COSMIC(564, 2142, 4836), CHAOS(562, 2270, 4840), NATURE(561, 2400, 4837), BLOOD(565, 1718, 3828);
    private final int id, x, y;

    RuneTypes(final int id, final int x, final int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public Position getAltarPosition() {
        return new Position(x, y);
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
