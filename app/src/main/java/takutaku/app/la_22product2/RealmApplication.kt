package takutaku.app.la_22product2

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}