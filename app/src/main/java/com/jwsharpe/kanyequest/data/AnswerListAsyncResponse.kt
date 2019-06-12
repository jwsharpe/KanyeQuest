package com.jwsharpe.kanyequest.data
import com.jwsharpe.kanyequest.model.Question

import java.util.ArrayList

interface AnswerListAsyncResponse {
    fun processFinished(questionArrayList: ArrayList<Question>)
}
