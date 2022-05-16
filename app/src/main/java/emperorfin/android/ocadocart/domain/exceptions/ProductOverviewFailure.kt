package emperorfin.android.ocadocart.domain.exceptions

import emperorfin.android.ocadocart.domain.exceptions.Failure.FeatureFailure


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


sealed class ProductOverviewFailure {

    class ListNotAvailableMemoryProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class ListNotAvailableLocalProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class ListNotAvailableRemoteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class LocalProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryGetProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class LocalGetProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteGetProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryInsertProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class LocalInsertProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteInsertProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryUpdateProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class LocalUpdateProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteUpdateProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryDeleteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class LocalDeleteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteDeleteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    class NonExistentDataMemoryProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class NonExistentDataLocalProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
    class NonExistentDataRemoteProductOverviewError(val cause: Throwable? = null) : FeatureFailure()

    // For Repositories
    class RepositoryGetProductOverviewError(val cause: Throwable? = null) : FeatureFailure()
}
