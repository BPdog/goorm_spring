package com.mycompany.daily0929.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class QuizSession {
    private List<QuizQuestion> questions = new ArrayList<>();
    private int currentQuestionIndex;
    private int score;

    public QuizSession() {
        // 퀴즈 데이터 init
        questions.add(new QuizQuestion("대한민국의 수도는?", "서울"));
        questions.add(new QuizQuestion("자바에서 클래스를 상속받을 때 사용하는 키워드는?", "extends"));
        questions.add(new QuizQuestion("스프링 프레임워크의 핵심 원칙 중 하나는?", "DI"));
        restart();
    }

    public void restart() {
        currentQuestionIndex = 0;
        score = 0;
    }

    public QuizQuestion getCurrentQuestion() {
        if (isQuizFinished()) {
            return null;
        }
        return questions.get(currentQuestionIndex);
    }

    public boolean isQuizFinished() {
        return currentQuestionIndex >= questions.size();
    }

    public void submitAnswer(String userAnswer) {
        if (isQuizFinished()) {
            return;
        }
        if (getCurrentQuestion().getAnswer().equalsIgnoreCase(userAnswer)) {
            score++;
        }
        currentQuestionIndex++;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}
