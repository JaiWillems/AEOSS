package io.readthedocs.celest.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Math.PI;

public class AngleTest {

    @Test
    public void testToDegreeFromDegreeIsValid() {
        Angle angle = Angle.fromDegree( 180 );
        Assertions.assertEquals( 180.0, angle.toDegree() );
    }

    @Test
    public void testToDegreeFromRadianIsValid() {
        Angle angle = Angle.fromRadian( PI );
        Assertions.assertEquals( 180.0, angle.toDegree() );
    }

    @Test
    public void testToRadianFromDegreeIsValid() {
        Angle angle = Angle.fromDegree( 180 );
        Assertions.assertEquals( PI, angle.toRadian() );
    }

    @Test
    public void testToRadianFromRadianIsValid() {
        Angle angle = Angle.fromRadian( PI );
        Assertions.assertEquals( PI, angle.toRadian() );
    }
}
