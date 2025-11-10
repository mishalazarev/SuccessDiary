package white.ball.data.local_storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import white.ball.data.local_storage.room.entity.CoffeeCoinDTO

@Dao
interface CoffeeCoinDao {

    @Query("SELECT * FROM CoffeeCoinDTO LIMIT 1")
    fun getCoffeeCoins(): Flow<CoffeeCoinDTO?>

    @Query("UPDATE CoffeeCoinDTO SET balance = :balance")
    suspend fun updateBalance(balance: Int)

    @Insert
    suspend fun createCoffeeCoin(coffeeCoinDTO: CoffeeCoinDTO)
}