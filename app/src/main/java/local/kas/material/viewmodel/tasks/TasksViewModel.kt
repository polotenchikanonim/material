package local.kas.material.viewmodel.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import local.kas.material.model.tasks.RepositoryTasksImpl
import local.kas.material.model.tasks.Task
import local.kas.material.repository.task.RepositoryTasks

class TasksViewModel(
    private val mutableLiveData: MutableLiveData<TasksAppState> = MutableLiveData(),
    private val repositoryTasks: RepositoryTasks = RepositoryTasksImpl()

) : ViewModel() {

    fun getData(): LiveData<TasksAppState> {
        return mutableLiveData
    }

    fun saveTask(task: Task) {
        repositoryTasks.saveTask(task)
    }

    fun getTasks() {
        repositoryTasks.getAllTasks()
    }
}