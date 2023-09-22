package com.example.hw3exercise3

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.hw3exercise3.databinding.ActivityMainBinding

import com.google.android.material.snackbar.Snackbar


//Property that the log function takes, called a TAG
//filters down verbose logging
//Keeps function call cleaner
private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {


    //creating a variable for the binding
    private lateinit var binding: ActivityMainBinding


    private val questionBank = listOf(
        question(R.string.question_australia, true),
        question(R.string.question_oceans, true),
        question(R.string.question_mideast, false),
        question(R.string.question_africa, false),
        question(R.string.question_americas, true),
        question(R.string.question_asia, true),
    )

    private var currentIndex = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //First Log for on create action
        Log.d(TAG, "onCreate (Bundle?) called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //My on onclick listener for the true button
        // and coding it with 'Correct!'
        binding.trueButton.setOnClickListener {

            checkAnswer(true)

//Disabling buttons after true button is clicked
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)


        }



        //My on onclick listener for the false button
        // and coding it with 'False!'
        binding.falseButton.setOnClickListener {

            checkAnswer(false)


//Disabling buttons after false button is clicked
            binding.falseButton.isEnabled = !(binding.falseButton.isEnabled)
            binding.trueButton.isEnabled = !(binding.trueButton.isEnabled)

        }



        //Finding questionTextView through binding
        //Moving to next and previous questions on click
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size

            updateQuestion()
        }


        binding.nextButton.setOnClickListener {

            currentIndex = (currentIndex + 1) % questionBank.size

//Re-enabling buttons when next button is clicked
            binding.falseButton.isEnabled = true
            binding.trueButton.isEnabled = true

            updateQuestion()

        }




        updateQuestion()
    }





    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }


//Re-configuring checkAnswer function like in the video
    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast

        }

        else {
            R.string.incorrect_toast

        }
        

        Toast.makeText(this, messageResId, Toast.LENGTH_LONG)
            .show()


    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }



    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }



    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

}
