package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private   var questions = arrayOf("What is built-in database in Android Studio?","What is the full from of APK in Android Development?","In which year, first android was released by Google?")
    private var options = arrayOf(arrayOf("MySQL","SQLite","Firebase"),arrayOf("Application Programming Interface","Android Programming Interface","Android Package Information"),arrayOf("2010","2006","2008"))
    private var correctAnswers= arrayOf(1,0,2)
    private var currentQuestionIndex =0
    private var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         displayQuestions()
        binding.option1.setOnClickListener {
            checkAnswer(0)
        }
        binding.option2.setOnClickListener {
            checkAnswer(1)
        }
        binding.option3.setOnClickListener {
            checkAnswer(2)
        }
        binding.restart.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex : Int){
        when(buttonIndex){
            0 -> binding.option1.setBackgroundColor(Color.GREEN)
            1 -> binding.option2.setBackgroundColor(Color.GREEN)
            2 -> binding.option3.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex:Int){

        when(buttonIndex){
            0 -> binding.option1.setBackgroundColor(Color.RED)
            1 -> binding.option2.setBackgroundColor(Color.RED)
            2 -> binding.option3.setBackgroundColor(Color.RED)
        }
    }
    private fun resetButtonColors(){

        binding.option1.setBackgroundColor(Color. rgb(55,58,33) )
        binding.option2.setBackgroundColor(Color. rgb(55,58,33) )
        binding.option3.setBackgroundColor(Color. rgb(55,58,33) )


    }
    private fun showResults(){
        Toast.makeText(this,"Your Score: $score out of ${questions.size}",Toast.LENGTH_LONG).show()
        binding.restart.isEnabled=true

    }
    private fun displayQuestions(){

        if(currentQuestionIndex < questions.size ){
            binding.textQuestion.text = questions[currentQuestionIndex]

            // Access option array using questions.size
            binding.option1.text = options[currentQuestionIndex][0]
            binding.option2.text = options[currentQuestionIndex][1]
            binding.option3.text = options[currentQuestionIndex][2]

           resetButtonColors()}else{
               showResults()
        }

    }
    private fun checkAnswer(selectedAnswerIndex:Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]
        if(selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }

        if(currentQuestionIndex < questions.size ){
            currentQuestionIndex ++
            binding.textQuestion.postDelayed({displayQuestions()},700)
        }

    }
    private fun restartQuiz(){
        currentQuestionIndex=0
        score=0
        displayQuestions()
        binding.restart.isEnabled=false
    }

}