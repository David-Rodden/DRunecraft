package methods;

public enum CraftMethods {
    SIMPLE_AIR(SimpleAir.class), SIMPLE_WATER(SimpleWater.class), SIMPLE_EARTH(SimpleEarth.class), SIMPLE_FIRE(SimpleFire.class), CASTLE_WARS_FIRE(CastleWarsFire.class), SIMPLE_BODY(SimpleBody.class), GENERAL_STORE_NATURE(GeneralStoreNature.class), ABYSS(Abyss.class);

    private final Class method;

    CraftMethods(final Class method) {
        this.method = method;
    }


    public Class getMethod() {
        return method;
    }

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).replaceAll("_", " ").toLowerCase();
    }
}
