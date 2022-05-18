package emperorfin.android.ocadocart.ui.utils

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import com.google.android.material.snackbar.Snackbar
import emperorfin.android.ocadocart.R


/**
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Tuesday 17th May, 2022.
 */


object ErrorUtil {

    /**
     * Show error message in a snack bar.  If an action is specified then the click listener
     * will be applied to the retry button.
     */
    fun showError(
        view: View,
        @StringRes message: Int,
        @StringRes buttonText: Int,
        action: View.OnClickListener?
    ) : Snackbar {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)

        val margin = snackBar.context.resources.getDimension(R.dimen._8dp).toInt()

        (snackBar.view.layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin)

        snackBar.view.background =
            ContextCompat.getDrawable(snackBar.context, R.drawable.snackbar_background)
        snackBar.setActionTextColor(ContextCompat.getColor(snackBar.context, R.color.teal_200))
        snackBar.behavior

        action?.let {
            snackBar.setAction(buttonText, action)
        }

        snackBar.show()

        return snackBar
    }

}