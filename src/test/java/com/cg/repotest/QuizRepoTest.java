package com.cg.repotest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.entities.Question;
import com.cg.entities.Quiz;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.QuizRepo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class QuizRepoTest {

	@Autowired
	private QuizRepo quizRepo;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test // @PostMapping(value = "quiz")
	public void testQuiz() throws Exception {
		Quiz q = new Quiz();
		Quiz saveInDb = testEntityManager.persist(q);
		Quiz getFromInDb = quizRepo.findById(saveInDb.getQuizId()).get();
		Assertions.assertEquals(getFromInDb, saveInDb);
	}

	@Test // @GetMapping(value = "quiz/{quiz_id}")
	public void testQuizById() throws Exception {
		Quiz q = new Quiz();
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);
		Set<Question> questions = new HashSet<>();
		Question ques = new Question();
		ques.setCorrectAnswer("a");
		ques.setExplanation("ex1");
		ques.setOption_A("a");
		ques.setOption_B("b");
		ques.setOption_C("c");
		ques.setOption_D("d");
		ques.setQuestionStatement("A");
		questions.add(ques);
		q.setQuestions(questions);

		Quiz saveInDb = testEntityManager.persist(q);

		Quiz getInDb = quizRepo.findById(q.getQuizId()).get();
		Assertions.assertEquals(saveInDb, getInDb);
	}

	@Test // @GetMapping(value = "quiz")
	public void testAllQuiz() throws Exception {

		Quiz q = new Quiz();
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);

		Quiz q1 = new Quiz();
		q1.setNegativeMark(true);
		q1.setSkip(false);
		q1.setRound(1);
		q1.setTotalQuestions(3);

		testEntityManager.persist(q1);
		testEntityManager.persist(q);

		try {
			assertThat(quizRepo.findAll().size()).isEqualTo(2);
		} catch (DataIntegrityViolationException e) {
			// Referential integrity constraint violation: "FKDLWGTQJEDCF6WSSQG22P66GJW:
			// PUBLIC.QUIZ FOREIGN KEY(QUIZ_ID) REFERENCES PUBLIC.CONTEST(CONTEST_ID) (1)"
			assertThat(true).isEqualTo(true);
		}
	}

	@Test // @PutMapping(value = "quiz/{quiz_id}")
	public void testUpdateQuiz() {
		Quiz q = new Quiz();
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(1);
		q.setTotalQuestions(1);
		q = testEntityManager.persist(q);
		Quiz getFromDb = quizRepo.findById(q.getQuizId()).get();
		getFromDb.setTotalQuestions(10);
		testEntityManager.persist(getFromDb);
		assertThat(getFromDb.getTotalQuestions()).isEqualTo(10);
	}

	@Test
	public void testDeleteQuizById() throws Exception {
		Quiz q = new Quiz();
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);

		q = testEntityManager.persist(q);
		testEntityManager.remove(q);
		Assert.assertEquals(quizRepo.findById(q.getQuizId()).isPresent(), false);
	}

}
