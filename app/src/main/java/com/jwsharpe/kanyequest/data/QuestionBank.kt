package com.jwsharpe.kanyequest.data


import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.jwsharpe.kanyequest.controller.VolleyController
import com.jwsharpe.kanyequest.model.Question

import java.util.ArrayList

class QuestionBank {
    internal var questionArrayList = ArrayList<Question>()

    public fun getQuestions(callBack: AnswerListAsyncResponse?): List<Question> {

        val kanyeRequest = JsonArrayRequest(
        Request.Method.GET,
        "https://raw.githubusercontent.com/ajzbc/kanye.rest/master/quotes.json",
        null,
        Response.Listener {
            for (i in 0..it.length() - 1) {
                var quote = it.getString(i)
                questionArrayList.add(Question(quote, "Kanye"))
            }
            callBack?.processFinished(questionArrayList)
        },
        Response.ErrorListener {
        })

    val trumpRequest = JsonArrayRequest(
        Request.Method.GET,
        "https://raw.githubusercontent.com/jwsharpe/jwsharpe.github.io/master/awfultrumpquotes.json",
        null,
        Response.Listener {
            for (i in 0..it.length() - 1) {
                var quote = it.getString(i)
                questionArrayList.add(Question(quote, "Trump"))
            }
            callBack?.processFinished(questionArrayList)
        },
        Response.ErrorListener {
        })


    val swansonRequest = JsonArrayRequest(
        Request.Method.GET,
        "https://ron-swanson-quotes.herokuapp.com/v2/quotes/50",
        null,
        Response.Listener {
            for (i in 0..it.length() - 1) {
                var quote = it.getString(i)
                questionArrayList.add(Question(quote, "Swanson"))

            }
            callBack?.processFinished(questionArrayList)
        },
        Response.ErrorListener {
        })

        VolleyController.instance!!.addToRequestQueue(kanyeRequest)
        VolleyController.instance!!.addToRequestQueue(trumpRequest)
        VolleyController.instance!!.addToRequestQueue(swansonRequest)




        return questionArrayList

    }
}
