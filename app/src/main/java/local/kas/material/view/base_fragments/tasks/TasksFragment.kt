package local.kas.material.view.base_fragments.tasks

//import local.kas.material.databinding.FragmentRecyclerBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import local.kas.material.databinding.FragmentTasksBinding
import local.kas.material.model.tasks.*
import local.kas.material.view.base_fragments.BaseFragment
import local.kas.material.viewmodel.tasks.TasksViewModel


class TasksFragment : BaseFragment<FragmentTasksBinding>(FragmentTasksBinding::inflate) {

    private lateinit var adapter: TasksAdapter
    lateinit var itemTouchHelper: ItemTouchHelper
    val tasks = arrayListOf(
        Task("Earth"),
        Task("Earth"),
        Task("System", type = TYPE_SYSTEM),
        Task("Earth"),
        Task("Mars", type = TYPE_MARS),
        Task("Earth"),
        Task("Earth")
    )

    private val citiesViewModel: TasksViewModel by lazy {
        ViewModelProvider(this)[TasksViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            adapter = TasksAdapter(
                { Toast.makeText(binding.root.context, it.title, Toast.LENGTH_SHORT).show() },
                tasks, {
                    itemTouchHelper.startDrag(it)
                })
            recyclerView.adapter = adapter
            itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
            itemTouchHelper.attachToRecyclerView(recyclerView)
            fab.setOnClickListener {
                addTask()
            }


        }


    }

    private fun addTask() {
        val task = Task("title", "description", TYPE_EARTH)
        citiesViewModel.saveTask(task)
        adapter.addTask(task)
    }

    companion object {
        @JvmStatic
        fun newInstance() = TasksFragment()
    }
}