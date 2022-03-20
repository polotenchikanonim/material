package local.kas.material.model.tasks

import android.graphics.Color
import android.view.View
import local.kas.material.view.base_fragments.tasks.TasksAdapter

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(position: Int)

}

interface ItemTouchHelperViewAdapter {

    fun onItemSelected(itemView: View) {
        itemView.setBackgroundColor(Color.LTGRAY)
    }

    fun onItemClear(itemView: View) {
        itemView.setBackgroundColor(0)
    }
}

fun interface OnStartDragListener {
    fun onStartDrag(viewHolder: TasksAdapter.BaseViewHolder)
}