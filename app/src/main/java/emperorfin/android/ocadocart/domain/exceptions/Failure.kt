package emperorfin.android.ocadocart.domain.exceptions


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


/**
 * Base class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()

    /**
     * Extend this class for feature specific failures.
     */
    abstract class FeatureFailure : Failure()
}
