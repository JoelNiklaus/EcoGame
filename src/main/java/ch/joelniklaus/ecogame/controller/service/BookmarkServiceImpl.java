package ch.joelniklaus.ecogame.controller.service;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.model.Bookmark;
import ch.joelniklaus.ecogame.model.User;
import ch.joelniklaus.ecogame.model.dao.AdDao;
import ch.joelniklaus.ecogame.model.dao.BookmarkDao;

@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired BookmarkDao bookmarkDao;
	@Autowired LoginService loginService;
	@Autowired AdDao adDao;

	@Transactional
	public Bookmark saveBookmark(Long adId) {
		assert adDao.findOne(adId) != null;
		
		Bookmark bookmark = new Bookmark();

		bookmark.setAdId(adId);
		bookmark.setUserId(loginService.getLoggedInUser().getId());

		bookmarkDao.save(bookmark);

		return bookmark;
	}

	@Transactional
	public Iterable<Bookmark> findBookmarks(User user) {
		Iterable<Bookmark> allBookmarks = bookmarkDao.findAll();
		LinkedList<Bookmark> results = new LinkedList<Bookmark>();

		for(Bookmark b : allBookmarks)
		{
			if(b.getUserId().equals(loginService.getLoggedInUser().getId()))
			{
				b.setAd(adDao.findOne(b.getAdId()));
				results.add(b);
			}
		}

		return (Iterable<Bookmark>)results;
	}

	@Transactional
	public boolean alreadyBookmarked(User user, Long adId) {
		Iterable<Bookmark> bookmarks = findBookmarks(user);
		
		for(Bookmark bookmark : bookmarks) {
			if(bookmark.getAdId().equals(adId))
				return true;
		}
		
		return false;
	}
	
	@Transactional
	public Bookmark removeBookmark(Bookmark bookmark) {
		bookmarkDao.delete(bookmark);
		
		return bookmark;
	}

}
