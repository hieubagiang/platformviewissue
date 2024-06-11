package com.example.android_platform_view_on_click_issue

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.gridlayout.widget.GridLayout
import io.flutter.plugin.platform.PlatformView

internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) : PlatformView {
    private val view: View

    override fun getView(): View {
        return view
    }

    override fun dispose() {}

    init {
        val gridView = GridLayout(context)
        gridView.rowCount = 3
        gridView.columnCount = 7
        for (i in 0..20) {
            val button = Button(context)
            button.text = "Button $i"
            button.layoutParams = GridLayout.LayoutParams().apply {
                height = 150 // Adjust this value to change the height of the button
                width = GridLayout.LayoutParams.WRAP_CONTENT
            }

            // Create a StateListDrawable for the button background
            val states = StateListDrawable()
            states.addState(intArrayOf(android.R.attr.state_selected), ContextCompat.getDrawable(context, R.drawable.selected_background))
            states.addState(intArrayOf(), ContextCompat.getDrawable(context, R.drawable.default_background))
            button.background = states

            // Set the button to be outlined when not clicked and filled when clicked
            button.setOnClickListener {
                it.isSelected = !it.isSelected
            }

            gridView.addView(button)
        }
        view = gridView
    }
}