package app.prototype.rockpaperscissors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game_mode.*
import java.lang.Exception

class GameMode : Fragment(), View.OnClickListener {

    private lateinit var vModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vModel = activity?.run {
            ViewModelProvider(this).get(MyViewModel::class.java)
        } ?: throw Exception("Activity Invalid")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_mode, container, false)

        view.findViewById<Button>(R.id.mode_button0).setOnClickListener(this)
        view.findViewById<Button>(R.id.mode_button1).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.back).setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when(v) {
            mode_button0 -> {
                vModel.gameMode.postValue("player")
                findNavController().navigate(R.id.RPSSLFragment, null, TransitionNavigation.slideInOutHorizon)}

            mode_button1 -> {
                vModel.gameMode.postValue("bot")
                findNavController().navigate(R.id.RPSSLFragment, null, TransitionNavigation.slideInOutHorizon)}

            back -> findNavController().navigateUp()
        }
    }
}
