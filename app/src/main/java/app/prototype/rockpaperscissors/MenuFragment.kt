package app.prototype.rockpaperscissors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*
import java.lang.Exception

class MenuFragment : Fragment(), View.OnClickListener {

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
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        view.findViewById<Button>(R.id.menu_button0).setOnClickListener(this)
        view.findViewById<Button>(R.id.menu_button1).setOnClickListener(this)

        return view
    }

    override fun onClick(v: View?) {
        when(v) {
            menu_button0 -> {
                vModel.countOfWeapon.postValue(3)
                findNavController().navigate(R.id.gameMode, null, TransitionNavigation.slideInOutHorizon)}
            menu_button1 -> {
                vModel.countOfWeapon.postValue(5)
                findNavController().navigate(R.id.gameMode, null, TransitionNavigation.slideInOutHorizon)}
        }
    }
}
