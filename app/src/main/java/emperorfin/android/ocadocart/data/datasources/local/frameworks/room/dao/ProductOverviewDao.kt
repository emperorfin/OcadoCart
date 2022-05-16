package emperorfin.android.ocadocart.data.datasources.local.frameworks.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.TABLE_NAME
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_TITLE


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Monday 16th May, 2022.
 */


@Dao
interface ProductOverviewDao {

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    suspend fun countProductsOverviews(): Int

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_INFO_TITLE ASC")
    suspend fun getProductsOverviews(): List<ProductOverviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductOverview(productOverview: ProductOverviewEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsOverviews(productsOverviews: List<ProductOverviewEntity>): List<Long>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteProductsOverviews(): Int

}