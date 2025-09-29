package com.mycompany.daily0929.controller;

import com.mycompany.daily0929.domain.QuizSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {

    @Autowired
    private QuizSession quizSession;

    @GetMapping("/quiz")
    public String showQuiz(Model model) {
        if (quizSession.isQuizFinished()) {
            return "redirect:/quiz-result";
        }
        model.addAttribute("question", quizSession.getCurrentQuestion());
        return "quiz";
    }

    @PostMapping("/quiz")
    public String submitAnswer(@RequestParam String answer) {
        quizSession.submitAnswer(answer);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz-result")
    public String showResult(Model model) {
        model.addAttribute("score", quizSession.getScore());
        model.addAttribute("total", quizSession.getTotalQuestions());
        return "quizResult";
    }

    @GetMapping("/restart")
    public String restartQuiz() {
        quizSession.restart();
        return "redirect:/quiz";
    }
}
