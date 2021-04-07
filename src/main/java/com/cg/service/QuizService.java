package com.cg.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Question;
import com.cg.entities.Quiz;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.jpautils.QuestionUtil;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.QuizRepo;

@Service
public class QuizService implements QuizServiceInterface {
	private static final Logger logger = LogManager.getLogger(QuizService.class);

	@Autowired
	QuizRepo repo;

	// add quiz
	@Override
	public QuizDTO addQuiz(QuizDTO quizdto) {
		logger.info("Entered in quiz service method addQuiz");
		Quiz quiz = repo.saveAndFlush(QuizUtils.convertQuizDTOToQuiz(quizdto));
		logger.info("Done in quiz service method addQuiz");
		return QuizUtils.convertQuizToQuizDTO(quiz);
	}

	// get all quiz
	@Override
	public List<com.cg.dto.QuizDTO> getAllQuiz() {
		logger.info("Entered in quiz service method getAllQuiz");
		List<Quiz> quizlist = repo.findAll();
		List<QuizDTO> dtolist = QuizUtils.convertQuizListToQuizDTOList(quizlist);
		System.out.println("service list size " + quizlist.size());
		logger.info("Done in quiz service method getAllQuiz");
		return dtolist;

	}

	// get quiz by id
	@Override
	public QuizDTO getQuiz(Integer quiz_id) throws QuizNotFoundException {
		logger.info("Entered in quiz service method getQuiz");
		Optional<Quiz> optional = repo.findById(quiz_id);
		if (optional.isPresent()) {
			Quiz q = optional.get();
			logger.info("Done in quiz service method addQuiz");
			return QuizUtils.convertQuizToQuizDTO(q);
		} else {
			throw new QuizNotFoundException("Quiz not found");
		}
	}

	// update quiz
	@Override
	public QuizDTO updateQuiz(Integer quiz_id, QuizDTO quizdto) throws QuizNotFoundException {
		logger.info("Entered in quiz service method updateQuiz");
		Optional<Quiz> q = repo.findById(quiz_id);
		if (!q.isPresent()) {
			throw new QuizNotFoundException("Quiz not found");
		}
		Quiz quiz = q.get();
		if (quizdto.getTotalQuestions() != null)
			quiz.setTotalQuestions(quizdto.getTotalQuestions());
		if (quizdto.getNegativeMark() != null)
			quiz.setNegativeMark(quizdto.getNegativeMark());
		if (quizdto.getSkip() != null)
			quiz.setSkip(quizdto.getSkip());
		if (quizdto.getRound() != null)
			quiz.setRound(quizdto.getRound());
		quiz = repo.saveAndFlush(quiz);
		logger.info("Done in quiz service method updateQuiz");
		return QuizUtils.convertQuizToQuizDTO(quiz);
	}

	// delete quiz
	@Override
	public boolean deleteQuiz(Integer quiz_id) throws QuizNotFoundException {
		logger.info("Entered in quiz service method deleteQuiz");
		Optional<Quiz> q = repo.findById(quiz_id);
		if (!q.isPresent()) {
			throw new QuizNotFoundException("Quiz not found");
		}
		repo.delete(q.get());
		logger.info("Done in quiz service method addQuiz");
		return true;
	}

	// add questions to quiz by quiz id
	@Override
	public QuizDTO addQuestionToQuiz(Integer quiz_id, Set<QuestionDTO> questions) throws QuizNotFoundException {

		logger.info("Entered in quiz service method addQuestionToQuiz");
		Set<Question> questionEntities = QuestionUtil.convertToQuestionSet(questions);
		Optional<Quiz> q = repo.findById(quiz_id);
		if (!q.isPresent()) {
			throw new QuizNotFoundException("Quiz not found");
		}
		Quiz quiz = q.get();
		quiz.getQuestions().addAll(questionEntities);
		quiz.setTotalQuestions(quiz.getQuestions().size());
		quiz = repo.saveAndFlush(quiz);
		logger.info("Done in quiz service method addQuestionToQuiz");
		return QuizUtils.convertQuizToQuizDTO(quiz);
	}
}