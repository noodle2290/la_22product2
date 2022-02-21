package takutaku.app.la_22product2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import takutaku.app.la_22product2.databinding.ActivityPlusBinding
import takutaku.app.la_22product2.databinding.ItemListBinding

class RealmAdapter(
    private val context: Context,
    private var memoData: OrderedRealmCollection<MemoData>?,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RealmAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memoTextView: TextView = view.findViewById(R.id.memo_text)
        val container : ConstraintLayout = view.findViewById(R.id.container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
        LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items: MemoData = memoData?.get(position) ?: return

        holder.memoTextView.text = items.content
        holder.container.setOnClickListener {
            listener.onItemClick(items)
        }
    }
    override fun getItemCount(): Int = memoData?.size ?: 0

    interface OnItemClickListener{
        fun onItemClick(item: MemoData)
    }
}