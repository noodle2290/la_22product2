package takutaku.app.la_22product2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.realm.Realm
import takutaku.app.la_22product2.databinding.ActivityPlusBinding
import java.util.*

class PlusActivity : AppCompatActivity() {

    val realm: Realm = Realm.getDefaultInstance()
    private lateinit var binding: ActivityPlusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlusBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val toMainActivityIntent = Intent(this, MainActivity::class.java)
        val memoName:String? = intent.getStringExtra("MEMO")
        val id = intent.getStringExtra("ID")
        if(memoName == null) {
            binding.deleteButton.isInvisible = true
            binding.submitButton.setOnClickListener {
                val subject: String = binding.textInput.text.toString()
                save(subject)
                startActivity(toMainActivityIntent)
            }
        }else{
            binding.deleteButton.isVisible = true
            binding.textInput.setText(memoName)
            binding.submitButton.setOnClickListener {
                val subject: String = binding.textInput.text.toString()
                update(id,subject)
                startActivity(toMainActivityIntent)
            }
            binding.deleteButton.setOnClickListener {
                delete(id)
                startActivity(toMainActivityIntent)
            }
        }
    }

    fun save(subject: String){
        realm.executeTransaction {
            val data = it.createObject(MemoData::class.java, UUID.randomUUID().toString())
            data.content = subject
        }
    }

    fun update(id: String?,subject: String){
        realm.executeTransaction {
            val data = realm.where(MemoData::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            data.content = subject
        }
    }

    fun delete(id:String?){
        realm.executeTransaction {
            val data = realm.where(MemoData::class.java).equalTo("id", id).findFirst()
                ?: return@executeTransaction
            data.deleteFromRealm()
        }
    }
}