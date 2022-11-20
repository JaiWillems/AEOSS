package io.readthedocs.celest.types;

import java.util.Objects;
import java.util.Optional;

/**
 * A tri-state container which can be a success, warning, or failure. The
 * container will contain a result for the success and warning states but not
 * the failure state. The container will contain a reason for the warning and
 * failure states but not the success state.
 *
 * @param <T> the type of result.
 */
public class TriFailable<T> {

    enum TriFailableState {
        SUCCESS,
        WARNING,
        FAILURE
    }

    private final TriFailableState state;
    private final Optional<T> result;
    private final Optional<String> warningReason;
    private final Optional<String> failureReason;

    private TriFailable( TriFailableState state, Optional<T> result,
                         Optional<String> message ) {
        this.state = state;
        this.result = result;
        this.warningReason =
                state == TriFailableState.WARNING ? message : Optional.empty();
        this.failureReason =
                state == TriFailableState.FAILURE ? message : Optional.empty();
    }

    /**
     * Returns a successful {@code TriFailable} describing the given
     * non-{@code null} result.
     *
     * @param result the result to describe, which must be non-{@code null}.
     * @param <T> the type of the result.
     * @return a successful {@code TriFailable} with the result present.
     * @throws NullPointerException if result is {@code null}.
     */
    public static <T> TriFailable<T> success( T result ) {
        return new TriFailable<>(TriFailableState.SUCCESS,
                Optional.of( Objects.requireNonNull( result ) ),
                Optional.empty() );
    }

    /**
     * Returns a warning {@code TriFailable} describing the given
     * non-{@code null} result with a warning message.
     *
     * @param result the result to describe, which must be non-{@code null}.
     * @param <T> the type of the result.
     * @param warningReason a warning description, which must be non-{@code null}.
     * @return a warning {@code TriFailable} with the result present.
     * @throws NullPointerException if result or warningReason is {@code null}.
     */
    public static <T> TriFailable<T> warning( T result, String warningReason ){
        return new TriFailable<>( TriFailableState.WARNING,
                Optional.of( Objects.requireNonNull( result ) ),
                Optional.of( Objects.requireNonNull( warningReason ) ) );
    }

    /**
     * Returns a failing {@code TriFailable} instance. No result is present for
     * this {@code TriFailable}.
     *
     * @param failureReason a failure description, which must be non-{@code null}.
     * @param <T> The type of the non-existent result.
     * @return a failing {@code TriFailable} with no result.
     * @throws NullPointerException if failureReason is {@code null}.
     */
    public static <T> TriFailable<T> failure( String failureReason ) {
        return new TriFailable<>( TriFailableState.FAILURE,
                Optional.empty(), Optional.of( failureReason ));
    }

    /**
     * If is a success, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if is a success, otherwise {@code false}.
     */
    public boolean isSuccess() {
        return state == TriFailableState.SUCCESS;
    }

    /**
     * If is a warning, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if is a warning, otherwise {@code false}.
     */
    public boolean isWarning() {
        return state == TriFailableState.WARNING;
    }

    /**
     * If is a failure, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if is a failure, otherwise {@code false}.
     */
    public boolean isFailure() {
        return state == TriFailableState.FAILURE;
    }

    /**
     * If the result is present, returns the result, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the non-{@code null} result described by this {@code TriFailable}
     * @throws java.util.NoSuchElementException if no result is present.
     */
    public T getResult() {
        return result.orElseThrow();
    }

    /**
     * If a warning reason is present, the reason is returned, otherwise an
     * empty string is returned.
     *
     * @return the reason if present, otherwise an empty string.
     */
    public String getWarningReason() {
        return warningReason.orElse( "" );
    }

    /**
     * If a failure reason is present, the reason is returned, otherwise an
     * empty string is returned.
     *
     * @return the reason if present, otherwise an empty string.
     */
    public String getFailureReason() {
        return failureReason.orElse( "" );
    }
}
