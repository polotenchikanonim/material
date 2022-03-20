package local.kas.material.model.tasks

import android.graphics.Color
import android.view.View

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