package takutaku.app.la_22product2

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class MemoData(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var content: String = "",
    open var createdAt: Date = Date(System.currentTimeMillis())
) : RealmObject()
