package be.ehb.cinema.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class FavoriteDatabaseTest : TestCase(){

    private lateinit var favoriteDao: FavoriteDAO
    private lateinit var db: FavoriteDatabase

    @Before
    override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,FavoriteDatabase::class.java).build()
        favoriteDao = db.favoriteDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun check(){

        val checkOnMultipleData = favoriteDao.check("634649")
        assertEquals(checkOnMultipleData,true)
    }
}