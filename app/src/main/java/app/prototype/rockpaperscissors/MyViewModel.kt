package app.prototype.rockpaperscissors

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    val countOfWeapon = MutableLiveData<Int>()
    val gameMode = MutableLiveData<String>()
    val playerScore = MutableLiveData(0)
    val computerScore = MutableLiveData(0)
    val computer1Score = MutableLiveData(0)
    val computer2Score = MutableLiveData(0)

}