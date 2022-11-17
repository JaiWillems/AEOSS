package io.readthedocs.celest.types;

import java.util.Objects;

import static java.lang.Math.PI;

/**
 * Angle is a measure coupled with a unit.
 */
public class Angle {

    private final double value;
    private final AngleUnit unit;

    /**
     * Define an angular measure coupled with a unit.
     *
     * @param value Angular value.
     * @param unit Unit of the angular value.
     */
    public Angle( double value, AngleUnit unit ) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Factory method to generate an Angle using a degree measure.
     *
     * @param value An angular measure in degrees.
     * @return A measure coupled with a unit.
     */
    public static Angle fromDegree( double value ) {
        return new Angle( value, AngleUnit.DEGREE );
    }

    /**
     * Factory method to generate an Angle using a radian measure.
     *
     * @param value An angular measure in radians.
     * @return A measure coupled with a unit.
     */
    public static Angle fromRadian( double value ) {
        return new Angle( value, AngleUnit.RADIAN );
    }

    /**
     * Get the degree value of the angular measure.
     *
     * @return Degree value.
     */
    public double toDegree() {
        return unit == AngleUnit.DEGREE ? value : value * 180 / PI;
    }

    /**
     * Get the radian value of the angular measure.
     *
     * @return Radian value.
     */
    public double toRadian() {
        return unit == AngleUnit.RADIAN ? value : value * PI / 180;
    }

    @Override
    public String toString() {
        return "Angle{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Angle angle = (Angle) o;
        return Double.compare( angle.value, value ) == 0 && unit == angle.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash( value, unit );
    }
}
