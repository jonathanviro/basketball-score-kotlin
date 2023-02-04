package com.javr.basketball_score

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.javr.basketball_score.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var localScore = 0
    private var visitorScore = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configuracionesBotones()
    }

    private fun configuracionesBotones() {
        binding.botonReset.setOnClickListener {
            resetScores()
        }

        binding.botonResult.setOnClickListener {
            startScoreActivity()
        }

        binding.botonLocalMinusOne.setOnClickListener {
            minusPoints(true, 1)
        }

        binding.botonLocalPlusOne.setOnClickListener {
            addPoints(true, 1)
        }

        binding.botonLocalPlusTwo.setOnClickListener {
            addPoints(true, 2)
        }

        binding.botonVisitanteMinusOne.setOnClickListener {
            minusPoints(false, 1)
        }

        binding.botonVisitantePlusOne.setOnClickListener {
            addPoints(false, 1)
        }

        binding.botonVisitantePlusTwo.setOnClickListener {
            addPoints(false, 2)
        }
    }

    private fun addPoints(booIsLocal: Boolean, intPuntos: Int) {
        if(booIsLocal) {
            localScore += intPuntos
            binding.textScoreLocal.text = localScore.toString()
        }else{
            visitorScore += intPuntos
            binding.textScoreVisitante.text = visitorScore.toString()
        }
    }

    private fun minusPoints(booIsLocal: Boolean, intPuntos: Int) {
        if(booIsLocal) {
            if (localScore > 0){
                localScore -= intPuntos
                binding.textScoreLocal.text = localScore.toString()
            }
        }else{
            if (visitorScore > 0) {
                visitorScore -= intPuntos
                binding.textScoreVisitante.text = visitorScore.toString()
            }
        }
    }

    private fun resetScores() {
        localScore = 0
        visitorScore = 0
        binding.textScoreLocal.text = localScore.toString()
        binding.textScoreVisitante.text = visitorScore.toString()
    }

    private fun startScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, localScore)
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, visitorScore)
        startActivity(intent)
    }
}