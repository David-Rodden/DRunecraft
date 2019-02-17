package utils;

public enum FoodTypes {
    TUNA, MONKFISH, SHARK;

    @Override
    public String toString() {
        final String name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
