package service;

import java.util.List;

import org.junit.Test;

import vo.Feedback;

public class FeedbackTest {

	@Test
	public void createFeedbackTest() {

		System.out.println("=================CREATE=================");

		Feedback f1 = new Feedback("OK");
		Feedback f2 = new Feedback("OK");
		Feedback f3 = new Feedback("KO");

		FeedbackService feedbackService = new FeedbackService();

		long idF1 = feedbackService.createFeedback(f1);
		long idF2 = feedbackService.createFeedback(f2);
		long idF3 = feedbackService.createFeedback(f3);

		feedbackService.deleteFeedback(idF1);
		feedbackService.deleteFeedback(idF2);
		feedbackService.deleteFeedback(idF3);
	}

	@Test
	public void updateFeedbackTest() {
		FeedbackService feedbackService = new FeedbackService();
		System.out.println("=============UPDATE=============");
		
		Feedback f = new Feedback("OK");
		feedbackService.createFeedback(f);
		f.setEsito("KO");
		feedbackService.updateFeedback(f);
		System.out.println("=============READ=============");
		
		List<Feedback> fornitoriList = feedbackService.readFeedback();
		for (Feedback fdb : fornitoriList) {
			System.out.println(fdb);
		}
	}
}