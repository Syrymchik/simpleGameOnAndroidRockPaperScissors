package app.prototype.rockpaperscissors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_rpssl.*
import kotlinx.android.synthetic.main.fragment_rpssl.view.*
import java.lang.Exception
import kotlin.random.Random

class RPSSLFragment : Fragment(), View.OnClickListener {
    private lateinit var vModel: MyViewModel
    private var isPlayingPlayerAndComputer = true

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

        val view: View = inflater.inflate(R.layout.fragment_rpssl, container, false)

        isPlayingPlayerAndComputer = vModel.gameMode.value == "player"

        val computerScore = Observer<Int> {
            score -> view.text_bot.text = getString(R.string.computer_and_score, score)
        }
        val playerScore = Observer<Int> {
            score -> view.text_player.text = getString(R.string.player_and_score, score)
        }

        val computer1Score = Observer<Int> {
            score -> view.text_bot.text = getString(R.string.computer_and_computer_score, 1, score)
        }

        val computer2Score = Observer<Int> {
            score -> view.text_player.text = getString(R.string.computer_and_computer_score, 2, score)
        }

        vModel.computerScore.observe(viewLifecycleOwner, computerScore)
        vModel.playerScore.observe(viewLifecycleOwner, playerScore)

        view.findViewById<Button>(R.id.button1).setOnClickListener(this)
        view.findViewById<Button>(R.id.button2).setOnClickListener(this)
        view.findViewById<Button>(R.id.button3).setOnClickListener(this)
        view.findViewById<Button>(R.id.button4).setOnClickListener(this)
        view.findViewById<Button>(R.id.button5).setOnClickListener(this)
        view.findViewById<Button>(R.id.button6).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.back).setOnClickListener(this)

        if(vModel.countOfWeapon.value == 5) view.findViewById<LinearLayout>(R.id.linear4).visibility = View.VISIBLE

        if (!isPlayingPlayerAndComputer){
            view.findViewById<Button>(R.id.button6).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.linear3).visibility = View.GONE
            view.findViewById<LinearLayout>(R.id.linear4).visibility = View.GONE
            view.findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.bot)
            vModel.computer1Score.observe(viewLifecycleOwner, computer1Score)
            vModel.computer2Score.observe(viewLifecycleOwner, computer2Score)
        }

        return view
    }

    private fun computer (el: Int = Random.nextInt(0, vModel.countOfWeapon.value ?: 3)) {
        val bot = Random.nextInt(0, vModel.countOfWeapon.value ?: 3)
        changeImages(bot, el)

        when(el){
            0 -> {
                when(bot){
                    0 -> showMessage(2)
                    1 -> showMessage(0)
                    2 -> showMessage(1)
                    3 -> showMessage(0)
                    4 -> showMessage(1)
                }
            }
            1 -> {
                when(bot){
                    0 -> showMessage(1)
                    1 -> showMessage(2)
                    2 -> showMessage(0)
                    3 -> showMessage(1)
                    4 -> showMessage(0)
                }
            }
            2 -> {
                when(bot){
                    0 -> showMessage(0)
                    1 -> showMessage(1)
                    2 -> showMessage(2)
                    3 -> showMessage(0)
                    4 -> showMessage(1)
                }
            }
            3 -> {
                when(bot){
                    0 -> showMessage(1)
                    1 -> showMessage(0)
                    2 -> showMessage(1)
                    3 -> showMessage(2)
                    4 -> showMessage(0)
                }
            }
            4 -> {
                when(bot){
                    0 -> showMessage(0)
                    1 -> showMessage(1)
                    2 -> showMessage(0)
                    3 -> showMessage(1)
                    4 -> showMessage(2)
                }
            }
        }
    }

    private fun changeImages (img1: Int, img2: Int) {
        when(img1){
            0 -> imageView1.setImageResource(R.drawable.rock)
            1 -> imageView1.setImageResource(R.drawable.paper)
            2 -> imageView1.setImageResource(R.drawable.scissors)
            3 -> imageView1.setImageResource(R.drawable.spock)
            4 -> imageView1.setImageResource(R.drawable.lizard)
            5 -> imageView1.setImageResource(R.drawable.user)
            6 -> imageView1.setImageResource(R.drawable.bot)
        }
        when(img2){
            0 -> imageView2.setImageResource(R.drawable.rock)
            1 -> imageView2.setImageResource(R.drawable.paper)
            2 -> imageView2.setImageResource(R.drawable.scissors)
            3 -> imageView2.setImageResource(R.drawable.spock)
            4 -> imageView2.setImageResource(R.drawable.lizard)
            5 -> imageView2.setImageResource(R.drawable.user)
            6 -> imageView2.setImageResource(R.drawable.bot)
        }
    }

    private fun showMessage (messId: Int) {
        var message = ""
        when(messId){
            0 -> {
                if(isPlayingPlayerAndComputer) {
                    message = getString(R.string.won_computer)
                    vModel.computerScore.value = vModel.computerScore.value?.plus(1)
                } else {
                    message = getString(R.string.won_computer_number, 1)
                    vModel.computer1Score.value = vModel.computer1Score.value?.plus(1)
                }
            }
            1 -> {
                if(isPlayingPlayerAndComputer) {
                    message = getString(R.string.won_user)
                    vModel.playerScore.value = vModel.playerScore.value?.plus(1)
                } else {
                    message = getString(R.string.won_computer_number, 2)
                    vModel.computer2Score.value = vModel.computer2Score.value?.plus(1)
                }
            }
            2 -> message = getString(R.string.no_one)
        }

        messageview.text = message
    }

    override fun onClick(v: View?) {
        when(v) {
            button1 -> computer(0)
            button2 -> computer(1)
            button3 -> computer(2)
            button4 -> computer(3)
            button5 -> computer(4)
            button6 -> computer()
            back -> findNavController().navigateUp()
        }
    }
}
