package by.paveldzunovich.diary.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.NoteService;
import by.paveldzunovich.diary.services.ifaces.PriorityService;
import by.paveldzunovich.diary.services.ifaces.ThemeService;
import by.paveldzunovich.diary.web.Attributes;
import by.paveldzunovich.diary.web.Pages;
import by.paveldzunovich.diary.web.controllers.viewbeans.Credentials;

@Controller
public class MainPage {

	@Autowired
	private NoteService noteService;
	@Autowired
	private PriorityService priorityService;
	@Autowired
	private ThemeService themeService;

	@RequestMapping(value = "/")
	public ModelAndView get(HttpServletRequest request) {
		return get(request, null);
	}

	public ModelAndView get(HttpServletRequest request, Theme theme) {
		try {
			ModelAndView view = new ModelAndView(Pages.MAIN);

			HttpSession session = request.getSession(true);
			User user = (User) session
					.getAttribute(Attributes.APPLICATION_USER);
			if (user != null) {
				Theme newTheme = new Theme();
				newTheme.setUser(user);
				view.addObject(Attributes.NEW_THEME, newTheme);

				List<Theme> userThemes = themeService.getUserThemes(user);
				view.addObject(Attributes.THEMES, userThemes);

				List<Note> notes = getNotes(user, theme);
				view.addObject(Attributes.NOTES, notes);

				Note note = new Note();
				note.setUser(user);
				note.setTheme(theme);
				view.addObject(Attributes.NOTE, note);

				List<Priority> priorities = priorityService.list();
				view.addObject(Attributes.PRIORITIES, priorities);
			} else {
				view.addObject(Attributes.CREDENTIALS, new Credentials());
			}

			return view;
		} catch (DaoException e) {
			ModelAndView view = new ModelAndView(Pages.MAIN);
			return view;
		}
	}

	private List<Note> getNotes(User user, Theme theme) throws DaoException {
		if (theme == null) {
			return noteService.getNotes(user);
		} else {
			return noteService.getThemeNotes(theme);
		}
	}
}
