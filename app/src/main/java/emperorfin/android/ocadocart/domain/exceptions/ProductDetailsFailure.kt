package emperorfin.android.ocadocart.domain.exceptions

import emperorfin.android.ocadocart.domain.exceptions.Failure.FeatureFailure


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Wednesday 18th May, 2022.
 */


sealed class ProductDetailsFailure {

    class ListNotAvailableMemoryProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class ListNotAvailableLocalProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class ListNotAvailableRemoteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class LocalProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryGetProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class LocalGetProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteGetProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryInsertProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class LocalInsertProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteInsertProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryUpdateProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class LocalUpdateProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteUpdateProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class MemoryDeleteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class LocalDeleteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class RemoteDeleteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    class NonExistentDataMemoryProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class NonExistentDataLocalProductDetailsError(val cause: Throwable? = null) : FeatureFailure()
    class NonExistentDataRemoteProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

    // For Repositories
    class RepositoryGetProductDetailsError(val cause: Throwable? = null) : FeatureFailure()

}
