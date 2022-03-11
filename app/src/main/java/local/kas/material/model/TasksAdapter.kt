package local.kas.material.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import local.kas.material.databinding.RecyclerItemEarthBinding
import local.kas.material.databinding.RecyclerItemMarsBinding
import local.kas.material.databinding.RecyclerItemSystemBinding

class TasksAdapter(
    private val myClickListener: MyClickListener,
    private var tasks: List<Task>
) : RecyclerView.Adapter<TasksAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return tasks[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            TYPE_EARTH -> {
                val itemBinding = RecyclerItemEarthBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
                return EarthViewHolder(itemBinding.root)
            }
            TYPE_MARS -> {
                val itemBinding = RecyclerItemMarsBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
                return MarsViewHolder(itemBinding.root)
            }
            else -> {
                val itemBinding = RecyclerItemSystemBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
                return SystemViewHolder(itemBinding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    abstract class BaseViewHolder(view: View): RecyclerView.ViewHolder(view){
        abstract fun bind(task: Task)
    }

    override fun getItemCount() = tasks.size

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(task: Task) {
            RecyclerItemEarthBinding.bind(itemView).apply {
                itemView.setOnClickListener {
                    myClickListener.onItemClick(task)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(task: Task) {
            RecyclerItemMarsBinding.bind(itemView).apply {
                itemView.setOnClickListener {
                    myClickListener.onItemClick(task)
                }
            }
        }
    }


    inner class SystemViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(task: Task) {
            RecyclerItemSystemBinding.bind(itemView).apply {
            }
        }
    }
}
