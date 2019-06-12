package com.jwsharpe.kanyequest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jwsharpe.kanyequest.data.AnswerListAsyncResponse
import com.jwsharpe.kanyequest.data.QuestionBank
import com.jwsharpe.kanyequest.model.Question
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var questionTextView: TextView
    lateinit var questionCounterTextView: TextView
    lateinit var kanyeButton: Button
    lateinit var trumpButton: Button
    lateinit var swansonButton: Button
    var currentQuestionIndex = 0
    lateinit var question: List<Question>
    lateinit var shuffledQuestions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionText)
        questionCounterTextView = findViewById(R.id.counterText)

        kanyeButton = findViewById(R.id.kanyeButton)
        trumpButton = findViewById(R.id.trumpButton)
        swansonButton = findViewById(R.id.swansonButton)

        kanyeButton.setOnClickListener(this)
        trumpButton.setOnClickListener(this)
        swansonButton.setOnClickListener(this)

        question = QuestionBank().getQuestions(object: AnswerListAsyncResponse {
            override fun processFinished(questionArrayList: ArrayList<Question>) {
                shuffledQuestions = question.shuffled()
                updateQuestion()

            }
        })



    }

    override fun onClick(view: View) {

        val answer = shuffledQuestions.get(currentQuestionIndex).isPerson

        when (view.id) {
            R.id.kanyeButton -> {
                if(answer == "Kanye") {
                    Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Wrong!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.trumpButton -> {
                if(answer == "Trump") {
                    Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Wrong!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.swansonButton -> {
                if(answer == "Swanson") {
                    Toast.makeText(applicationContext, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Wrong!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        currentQuestionIndex += 1 % shuffledQuestions.size
        updateQuestion()

    }

    private fun updateQuestion(){
        questionTextView.text = shuffledQuestions.get(currentQuestionIndex).quote
        counterText.text = currentQuestionIndex.toString() + " out of " + shuffledQuestions.size
    }
}

