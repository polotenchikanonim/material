package local.kas.material.view.base_fragments.animation.explode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import local.kas.material.R

class ExplodeAdapter(private val onMyClickListener: OnMyClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var onMyClickListener: OnMyClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onMyClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return 32
    }

//    fun setOnMyCLickListener(onMyClickListener: OnMyClickListener) {
//        this.onMyClickListener = onMyClickListener
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

