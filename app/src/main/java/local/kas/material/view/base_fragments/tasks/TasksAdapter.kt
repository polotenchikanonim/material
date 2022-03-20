package local.kas.material.view.base_fragments.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import local.kas.material.databinding.RecyclerItemEarthBinding
import local.kas.material.databinding.RecyclerItemMarsBinding
import local.kas.material.databinding.RecyclerItemSystemBinding
import local.kas.material.model.tasks.*
import local.kas.material.viewmodel.tasks.MyClickListener

class TasksAdapter(
    private val myClickListener: MyClickListener,
    var tasks: MutableList<Task>
) : RecyclerView.Adapter<TasksAdapter.BaseViewHolder>(),
    ItemTouchHelperAdapter {

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

    abstract inner class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view),

        ItemTouchHelperViewAdapter {
        abstract fun bind(task: Task)

        fun removeItem() {
            tasks.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    override fun getItemCount() = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(itemCount - 1)
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(task: Task) {
            RecyclerItemEarthBinding.bind(itemView).apply {
                title.text = task.title
                itemView.setOnClickListener {
                    myClickListener.onItemClick(task)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {

        override fun bind(task: Task) {
            RecyclerItemMarsBinding.bind(itemView).apply {
                title.text = task.title
                itemView.setOnClickListener {
                    myClickListener.onItemClick(task)
                }
                moveItemUp.setOnClickListener {
                    moveUp(layoutPosition)
                }
                moveItemDown.setOnClickListener {
                    moveDown(layoutPosition)
                }
            }
        }

    }


    inner class SystemViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(task: Task) {
            RecyclerItemSystemBinding.bind(itemView).apply {
                itemView.setOnClickListener {
                    myClickListener.onItemClick(task)
                }
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        tasks.removeAt(fromPosition).apply {
            tasks.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun moveUp(layoutPosition: Int) {
        if (layoutPosition > 0) {
            tasks.removeAt(layoutPosition).apply {
                tasks.add(layoutPosition - 1, this)
            }
            notifyItemMoved(layoutPosition, layoutPosition - 1)
        }

    }

    private fun moveDown(layoutPosition: Int) {
        val newLayoutPosition = layoutPosition + 1
        if (newLayoutPosition < tasks.size) {
            tasks.removeAt(layoutPosition).apply {
                tasks.add(newLayoutPosition, this)
            }
            notifyItemMoved(layoutPosition, newLayoutPosition)
        }
    }
}
