package by.paveldzunovich.diary.model.comparators;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.services.ifaces.LikeService;

public class ThemesPopularityComparator implements Comparator<Theme> {

	@Autowired
	private LikeService likeService;

	public int compare(Theme t1, Theme t2) {
		try {
			return likeService.getThemeLikeNumber(t2)
					- likeService.getThemeLikeNumber(t1);
		} catch (DaoException e) {
			return 0;
		}
	}

}
