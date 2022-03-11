package local.kas.material.model

const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_SYSTEM = 3


data class Task(
    val title: String = "title",
    val description: String = "description",
    val type: Int = TYPE_EARTH
)
