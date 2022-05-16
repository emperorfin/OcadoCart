package emperorfin.android.ocadocart.data.datasources.local.frameworks.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.dao.ProductOverviewDao
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.TABLE_NAME
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_TITLE
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_SIZE
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_PRICE
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_IMAGE_URL
import emperorfin.android.ocadocart.data.datasources.local.frameworks.room.entities.ProductOverviewEntity.Companion.COLUMN_INFO_TAG
import emperorfin.android.ocadocart.data.utils.ProductsOverviewsSampleDataGeneratorUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 13th May, 2022.
 */


@Database(entities = [ProductOverviewEntity::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract val mProductOverviewDao: ProductOverviewDao

    companion object {

        private const val DATABASE_NAME = "database_app"

        private var isDatabaseAlreadyPopulated: Boolean = false

        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        private val TAG: String = AppRoomDatabase::class.java.simpleName

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase{

            synchronized(this){
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppRoomDatabase::class.java,
                        DATABASE_NAME
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                coroutineScope.launch {
                                    // This is now commented out since real productOverview data are being cached
                                    // to the database.
                                    populateInitialProductsOverviewsSampleDataUsingSqliteDatabaseWithCoroutineThread(db, ProductsOverviewsSampleDataGeneratorUtil.getProductOverviewEntityList())
                                }
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                            }

                            override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                                super.onDestructiveMigration(db)
                            }
                        })
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }

        }

        private suspend fun populateInitialProductsOverviewsSampleDataUsingSqliteDatabaseWithCoroutineThread(db: SupportSQLiteDatabase, productsOverviews: List<ProductOverviewEntity>) {
            // Unused at the moment.
            if (isDatabaseAlreadyPopulated)
                return

            db.beginTransaction()

            try {
                val initialProductOverviewValues: ContentValues = ContentValues()
                for (productOverview in productsOverviews){
                    initialProductOverviewValues.put(COLUMN_INFO_ID, productOverview.id)
                    initialProductOverviewValues.put(COLUMN_INFO_TITLE, "${productOverview.title} (room sample data)")
                    initialProductOverviewValues.put(COLUMN_INFO_SIZE, productOverview.size)
                    initialProductOverviewValues.put(COLUMN_INFO_PRICE, productOverview.price)
                    initialProductOverviewValues.put(COLUMN_INFO_IMAGE_URL, productOverview.imageUrl)
                    initialProductOverviewValues.put(COLUMN_INFO_TAG, productOverview.tag)

                    db.insert(TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE, initialProductOverviewValues)
                }

                db.setTransactionSuccessful()
            }finally {
                db.endTransaction()
            }
        }

    }

}
