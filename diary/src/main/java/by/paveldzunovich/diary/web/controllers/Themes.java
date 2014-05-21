package by.paveldzunovich.diary.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import by.paveldzunovich.diary.model.Subscription;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.SubscriptionService;
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
	@Autowired
	private SubscriptionService subscriptionService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(User.class, userBinder);
	}

	@RequestMapping(value = "/add")
	public ModelAndView addTheme(HttpServletRequest request,
			@ModelAttribute("newTheme") @Valid Theme theme,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			try {
				themeService.add(theme);
			} catch (DaoException e) {
				bindingResult.rejectValue("name", "name.empty",
						"Error adding theme.");
			}
		}

		ModelAndView view = mainPage.get(request);
		if (bindingResult.hasErrors()) {
			view.addAllObjects(bindingResult.getModel());
		}
		return view;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getThemeNotes(HttpServletRequest request,
			@PathVariable(value = "id") int id) throws DaoException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(Attributes.APPLICATION_USER);

		Theme theme = themeService.getById(id);
		subscriptionService.readTheme(user, theme);
		ModelAndView view = mainPage.get(request, theme);
		return view;
	}

	@RequestMapping(value = "/search/{text}")
	public ModelAndView searchThemes(HttpServletRequest request,
			@PathVariable(value = "text") String text) throws DaoException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(Attributes.APPLICATION_USER);
		List<Theme> themes = themeService.searchThemes(user, text);

		ModelAndView view = mainPage.get(request);
		view.setViewName("theme/search/result");
		view.addObject(Attributes.THEMES_FOUND, themes);
		return view;
	}

	@RequestMapping(value = "/subscribe/{id}")
	public ModelAndView subscribe(HttpServletRequest request,
			@PathVariable(value = "id") int themeId) throws DaoException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(Attributes.APPLICATION_USER);
		Theme theme = themeService.getById(themeId);

		Subscription subscription = new Subscription(user, theme);
		subscriptionService.addSubscription(subscription);

		return mainPage.get(request, theme);
	}

	@RequestMapping(value = "/unsubscribe/{id}")
	public ModelAndView unsubscribe(HttpServletRequest request,
			@PathVariable(value = "id") int themeId) throws DaoException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(Attributes.APPLICATION_USER);
		Theme theme = themeService.getById(themeId);

		Subscription subscription = subscriptionService.get(user, theme);
		subscriptionService.deleteSubscription(subscription);

		return mainPage.get(request);
	}

	@RequestMapping(value = "/delete/{id}")
	public ModelAndView deleteTheme(HttpServletRequest request,
			@PathVariable(value = "id") int themeId) throws DaoException {
		Theme theme = themeService.getById(themeId);
		themeService.delete(theme);

		return mainPage.get(request);
	}

}
