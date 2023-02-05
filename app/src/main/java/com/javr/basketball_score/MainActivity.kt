package com.javr.basketball_score

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.javr.basketball_score.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.localScoreLiveData.observe(this, Observer {
            localScoreValue ->
            binding.textScoreLocal.text = localScoreValue.toString()
        })

        viewModel.visitorScore.observe(this, Observer {
            visitorScoreValue ->
            binding.textScoreVisitante.text = visitorScoreValue.toString()
        })

        configuracionesBotones()
    }

    private fun configuracionesBotones() {
        binding.botonReset.setOnClickListener {
            viewModel.resetScores()
        }

        binding.botonResult.setOnClickListener {
            startScoreActivity()
        }

        binding.botonLocalMinusOne.setOnClickListener {
            viewModel.minusPoints(true, 1)
        }

        binding.botonLocalPlusOne.setOnClickListener {
            viewModel.addPoints(true, 1)
        }

        binding.botonLocalPlusTwo.setOnClickListener {
            viewModel.addPoints(true, 2)
        }

        binding.botonVisitanteMinusOne.setOnClickListener {
            viewModel.minusPoints(false, 1)
        }

        binding.botonVisitantePlusOne.setOnClickListener {
            viewModel.addPoints(false, 1)
        }

        binding.botonVisitantePlusTwo.setOnClickListener {
            viewModel.addPoints(false, 2)
        }
    }
    private fun startScoreActivity() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.localScoreLiveData.value)
        intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.visitorScore.value)
        startActivity(intent)
    }
}