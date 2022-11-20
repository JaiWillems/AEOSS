package io.readthedocs.celest.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class TriFailableTest {

    private static final String result = "result";
    private static final String reason = "message";
    private static final TriFailable<String> successFailable =
            TriFailable.success( result );
    private static final TriFailable<String> warningFailable =
            TriFailable.warning( result, reason );
    private static final TriFailable<String> failureFailable =
            TriFailable.failure( reason );

    @Test
    public void testSuccessIsSuccess() {
        Assertions.assertTrue( successFailable.isSuccess() );
        Assertions.assertFalse( successFailable.isWarning() );
        Assertions.assertFalse( successFailable.isFailure() );
    }

    @Test
    public void TestSuccessWithNullResultThrowsException() {
        Assertions.assertThrows( NullPointerException.class,
                () -> TriFailable.<String>success( null ) );
    }

    @Test
    public void testSuccessResult() {
        Assertions.assertEquals( successFailable.getResult(), result );
    }
    @Test
    public void testSuccessMessages() {
        Assertions.assertTrue( successFailable.getWarningReason().isEmpty() );
        Assertions.assertTrue( successFailable.getFailureReason().isEmpty() );
    }

    @Test
    public void testWarningIsWarning() {
        Assertions.assertFalse( warningFailable.isSuccess() );
        Assertions.assertTrue( warningFailable.isWarning() );
        Assertions.assertFalse( warningFailable.isFailure() );
    }

    @Test
    public void TestWarningWithNullResultThrowsException() {
        Assertions.assertThrows( NullPointerException.class,
                () -> TriFailable.<String>warning( null, reason ) );
    }

    @Test
    public void TestWarningWithNullReasonThrowsException() {
        Assertions.assertThrows( NullPointerException.class,
                () -> TriFailable.<String>warning( result, null ) );
    }

    @Test
    public void testWarningResultThrowsError() {
        Assertions.assertEquals( warningFailable.getResult(), result );
    }
    @Test
    public void testWarningMessages() {
        Assertions.assertEquals( warningFailable.getWarningReason(), reason );
        Assertions.assertTrue( warningFailable.getFailureReason().isEmpty() );
    }

    @Test
    public void testFailureIsFailure() {
        Assertions.assertFalse( failureFailable.isSuccess() );
        Assertions.assertFalse( failureFailable.isWarning() );
        Assertions.assertTrue( failureFailable.isFailure() );
    }

    @Test
    public void TestFailureWithNullReasonThrowsException() {
        Assertions.assertThrows( NullPointerException.class,
                () -> TriFailable.<String>failure( null ) );
    }

    @Test
    public void testFailureResult() {
        Assertions.assertThrows( NoSuchElementException.class,
                () -> failureFailable.getResult() );
    }
    @Test
    public void testFailureMessages() {
        Assertions.assertTrue( failureFailable.getWarningReason().isEmpty() );
        Assertions.assertEquals( failureFailable.getFailureReason(), reason );
    }
}
