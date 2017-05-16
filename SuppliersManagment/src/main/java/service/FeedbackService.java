package service;

import java.util.List;

import dao.FeedackDao;
import vo.Feedback;

public class FeedbackService {
	
	FeedackDao feedackDao = new FeedackDao();

	public int createFeedback(Feedback f) {
		return feedackDao.createFeedback(f);
	}

	public void deleteFeedback(long idF1) {
		feedackDao.deleteFeedback(idF1);
		
	}

	public void updateFeedback(Feedback f) {
		feedackDao.updateFeedback(f);
		
	}

	public List<Feedback> readFeedback() {
		// TODO Auto-generated method stub
		return feedackDao.readFeedback();
	}

}
