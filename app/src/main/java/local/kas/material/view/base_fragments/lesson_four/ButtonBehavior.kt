package local.kas.material.view.base_fragments.lesson_four

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class ButtonBehavior(context: Context?, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout

    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        val bar = dependency as AppBarLayout
        val barY = abs(bar.y)
        val barHeight = bar.height / 2

        if (barY > barHeight) {
            child.visibility = View.GONE
        } else {
            child.visibility = View.VISIBLE
            val alpha = (barHeight - barY) / barHeight
            child.alpha = alpha
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }
}