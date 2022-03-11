package local.kas.material.view.base_fragments.tasks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import local.kas.material.databinding.FragmentRecyclerBinding
import local.kas.material.model.TYPE_MARS
import local.kas.material.model.TYPE_SYSTEM
import local.kas.material.model.Task
import local.kas.material.model.TasksAdapter
import local.kas.material.view.base_fragments.BaseFragment


class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>(FragmentRecyclerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = TasksAdapter(
            {
                Toast.makeText(binding.root.context, it.title, Toast.LENGTH_SHORT).show()
            },
            arrayListOf(
                Task("Earth"),
                Task("Earth"),
                Task("System", type = TYPE_SYSTEM),
                Task("Earth"),
                Task("Mars", type = TYPE_MARS),
                Task("Earth"),
                Task("Earth"),
            )
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }
}