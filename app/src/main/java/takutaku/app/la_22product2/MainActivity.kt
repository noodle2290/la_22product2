package takutaku.app.la_22product2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.RealmResults
import takutaku.app.la_22product2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val dataList: RealmResults<MemoData>? = readAll()
        var adapter = RealmAdapter(this, dataList, object : RealmAdapter.OnItemClickListener {
            override fun onItemClick(item: MemoData) {
                // itemクリック時の処理
                val toPlusIntent = Intent(this@MainActivity, PlusActivity::class.java)
                toPlusIntent.putExtra("MEMO",item.content)
                toPlusIntent.putExtra("ID",item.id)
                startActivity(toPlusIntent)
            }
        })
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        binding.plusButton.setOnClickListener {
            val toPlusIntent = Intent(this, PlusActivity::class.java)
            startActivity(toPlusIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun readAll(): RealmResults<MemoData> {
        return realm.where(MemoData::class.java).findAll()
    }
}