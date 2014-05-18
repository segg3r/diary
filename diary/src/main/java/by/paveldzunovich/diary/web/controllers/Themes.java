package by.paveldzunovich.diary.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.ThemeService;
import by.paveldzunovich.diary.web.Attributes;
import by.paveldzunovich.diary.web.controllers.binders.UserBinder;

@Controller
@RequestMapping(value = "/theme")
public class Themes {

	@Autowired
	private MainPage mainPage;
	@Autowired
	private UserBinder userBinder;
	@Autowired
	private ThemeService themeService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(User.class, userBinder);
	}

	@RequestMapping(value = "/add")
	public ModelAndView addTheme(HttpServletRequest request, @ModelAttribute("newTheme") Theme theme,
			BindingResult bindingResult) {
		try {
			themeService.add(theme);
		} catch (DaoException e) {
			bindingResult.rejectValue("name", "name.empty",
					"Error adding theme.");
		}
		
		ModelAndView view = mainPage.get(request);
		view.addAllObjects(bindingResult.getModel());
		return view;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getThemeNotes(HttpServletRequest request,
			@PathVariable(value = "id") int id) throws DaoException {
		Theme theme = themeService.getById(id);
		ModelAndView view = mainPage.get(request, theme);
		return view;
	}

	@RequestMapping(value = "/search/{text}")
	public ModelAndView searchThemes(HttpServletRequest request,
			@PathVariable(value = "text") String text) throws DaoException {
		List<Theme> themes = themeService.searchThemes(text);

		ModelAndView view = mainPage.get(request);
		view.setViewName("theme/result");
		view.addObject(Attributes.THEMES_FOUND, themes);
		return view;
	}
}
