package mrthomas20121.tfc_tinker.api.types;

import javax.annotation.Nonnull;

public enum Type
{
    KNAPPING_GROUT,
    NULL; // This is special, it is a non-null null.

    private static final Type[] values = values();

    @Nonnull
    public static Type valueOf(int id)
    {
        while (id >= values.length) id -= values.length;
        while (id < 0) id += values.length;
        return values[id];
    }
}