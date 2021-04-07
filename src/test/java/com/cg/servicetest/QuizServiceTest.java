package com.cg.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Quiz;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.QuizRepo;
import com.cg.service.QuizServiceInterface;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class QuizServiceTest {

	@MockBean
	private QuizRepo quizRepo;

	@Autowired
	private QuizServiceInterface quizServiceInterface;

	@Test // addquiz -- @PostMapping(value = "quiz")
	public void addQuiz() {
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);
		Set<QuestionDTO> questions = new HashSet<>();
		QuestionDTO ques = new QuestionDTO();
		ques.setCorrectAnswer("a");
		ques.setExplanation("ex1");
		ques.setOption_A("a");
		ques.setOption_B("b");
		ques.setOption_C("c");
		ques.setOption_D("d");
		ques.setQuestion_ID(1);
		ques.setQuestionStatement("A");
		questions.add(ques);
		q.setQuestions(questions);

		Quiz quiz = QuizUtils.convertQuizDTOToQuiz(q);

		Mockito.when(quizRepo.saveAndFlush(Mockito.any(Quiz.class))).thenReturn(quiz);
		Assertions.assertEquals(quizServiceInterface.addQuiz(q), q);
	}

	@Test // getQuizById -- @GetMapping(value = "quiz/{quiz_id}")
	public void getQuizById() throws QuizNotFoundException {
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);
		Set<QuestionDTO> questions = new HashSet<>();
		QuestionDTO ques = new QuestionDTO();
		ques.setCorrectAnswer("a");
		ques.setExplanation("ex1");
		ques.setOption_A("a");
		ques.setOption_B("b");
		ques.setOption_C("c");
		ques.setOption_D("d");
		ques.setQuestion_ID(1);
		ques.setQuestionStatement("A");
		questions.add(ques);
		q.setQuestions(questions);
		Quiz quiz = QuizUtils.convertQuizDTOToQuiz(q);
		Optional<Quiz> optional = Optional.of(quiz);

		Mockito.when(quizRepo.findById(Mockito.any(Integer.class))).thenReturn(optional);
		Assertions.assertEquals(quizServiceInterface.getQuiz(2), q);
	}

	@Test // getAllQuiz -- @GetMapping(value = "quiz")
	public void getAllQuiz() throws QuizNotFoundException {
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);

		QuizDTO q1 = new QuizDTO();
		q1.setQuizId(3);
		q1.setNegativeMark(true);
		q1.setSkip(false);
		q1.setRound(2);
		q1.setTotalQuestions(3);

		List<QuizDTO> quizDtoList = new ArrayList<>();
		quizDtoList.add(q);
		quizDtoList.add(q1);

		List<Quiz> quizList = QuizUtils.convertQuizDTOListToQuizList(quizDtoList);

		Mockito.when(quizRepo.findAll()).thenReturn(quizList);
		assertThat(quizServiceInterface.getAllQuiz()).isEqualTo(quizDtoList);
	}

	@Test // updateQuiz -- @PutMapping(value = "quiz/{quiz_id}")
	public void testUpdateQuiz() throws Exception {
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);

		Quiz quiz = QuizUtils.convertQuizDTOToQuiz(q);
		Optional<Quiz> optional = Optional.of(quiz);

		Mockito.when(quizRepo.findById(Mockito.anyInt())).thenReturn(optional);
		Mockito.when(quizRepo.saveAndFlush(Mockito.any(Quiz.class))).thenReturn(quiz);
		Assertions.assertEquals(quizServiceInterface.updateQuiz(2, q), q);
	}

	@Test // @DeleteMapping(value = "quiz/{quiz_id}")
	public void testDeleteQuiz() throws Exception {
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);

		Quiz quiz = QuizUtils.convertQuizDTOToQuiz(q);
		Optional<Quiz> optional = Optional.of(quiz);

		Mockito.when(quizRepo.save(quiz)).thenReturn(quiz);
		Mockito.when(quizRepo.findById(Mockito.anyInt())).thenReturn(optional);
		quizRepo.deleteById(quiz.getQuizId());
		Assertions.assertEquals(quizServiceInterface.deleteQuiz(1), true);

	}

}
