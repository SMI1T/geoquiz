// MainActivity.kt
package com.example.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Создаем массив вопросов
    private val questions = arrayOf(
        Question(R.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // Создаем переменную для индекса текущего вопроса
    private var currentIndex = 0

    // Создаем переменные для элементов пользовательского интерфейса
    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Связываем переменные с элементами по их идентификаторам
        questionTextView = findViewById(R.id.question_text_view)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        // Устанавливаем текст вопроса в TextView
        updateQuestion()

        // Добавляем слушатели нажатий на кнопки
        trueButton.setOnClickListener {
            checkAnswer(true) // Проверяем ответ пользователя
            nextQuestion() // Переходим к следующему вопросу
        }

        falseButton.setOnClickListener {
            checkAnswer(false) // Проверяем ответ пользователя
            nextQuestion() // Переходим к следующему вопросу
        }
    }

    // Функция для обновления текста вопроса в TextView
    private fun updateQuestion() {
        // Получаем идентификатор ресурса для текущего вопроса
        val questionResId = questions[currentIndex].textResId
        // Устанавливаем текст в TextView из ресурса
        questionTextView.setText(questionResId)
    }

    // Функция для проверки ответа пользователя
    private fun checkAnswer(userAnswer: Boolean) {
        // Получаем правильный ответ для текущего вопроса
        val correctAnswer = questions[currentIndex].answer
        // Создаем переменную для текста сообщения
        val messageResId: Int
        // Сравниваем ответ пользователя с правильным ответом
        if (userAnswer == correctAnswer) {
            // Если ответ верный, то показываем сообщение "Правильно!"
            messageResId = R.string.correct_toast
        } else {
            // Если ответ неверный, то показываем сообщение "Неправильно!"
            messageResId = R.string.incorrect_toast
        }
        // Показываем сообщение в виде Toast
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    // Функция для перехода к следующему вопросу
    private fun nextQuestion() {
        // Увеличиваем индекс текущего вопроса на единицу
        currentIndex = (currentIndex + 1) % questions.size
        // Обновляем текст вопроса в TextView
        updateQuestion()
    }
}

// Question.kt
package com.example.geoquiz

// Класс для представления вопроса
data class Question(
    val textResId: Int, // Идентификатор ресурса для текста вопроса
    val answer: Boolean // Правильный ответ на вопрос
)
