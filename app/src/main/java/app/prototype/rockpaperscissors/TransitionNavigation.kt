package app.prototype.rockpaperscissors

import androidx.navigation.navOptions

class TransitionNavigation {

    companion object {

        val slideInOutHorizon = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    }
}