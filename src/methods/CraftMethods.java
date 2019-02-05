package methods;

public enum CraftMethods {
    SIMPLE_AIR(SimpleAir.class, 556), SIMPLE_WATER(SimpleWater.class, 555), SIMPLE_EARTH(SimpleEarth.class, 557), SIMPLE_FIRE(SimpleFire.class, 554), CASTLE_WARS_FIRE(CastleWarsFire.class, 554), SIMPLE_BODY(SimpleBody.class, 559), GENERAL_STORE_NATURE(GeneralStoreNature.class, 561);

    private final Class method;
    private final int id;

    CraftMethods(final Class method, final int id) {
        this.method = method;
        this.id = id;
    }

    public int getId() {
        return id;
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
