package local.kas.material.model.tasks

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)

}

interface ItemTouchHelperViewAdapter {
    fun onItemSelected()
    fun onItemClear()
}