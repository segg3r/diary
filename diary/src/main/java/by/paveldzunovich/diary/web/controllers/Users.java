package by.paveldzunovich.diary.web.controllers;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.model.comparators.ThemesPopularityComparator;
import by.paveldzunovich.diary.services.ifaces.LikeService;
import by.paveldzunovich.diary.services.ifaces.ThemeService;
import by.paveldzunovich.diary.services.ifaces.UserService;
import by.paveldzunovich.diary.web.Attributes;
import by.paveldzunovich.diary.web.Pages;
import by.paveldzunovich.diary.web.controllers.viewbeans.Credentials;

@Controller
@RequestMapping(value = "/user")
public class Users {

	@Autowired
	private MainPage mainPage;
	@Autowired
	private UserService userService;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private ThemesPopularityComparator themesPopularityComparator;
	@Autowired
	private LikeService likeService;

	@RequestMapping(value = "/register")
	public String toRegister(ModelMap model) {
		model.addAttribute(Attributes.USER, new User());
		return Pages.REGISTER;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,
			@ModelAttribute(Attributes.USER) @Valid User user,
			BindingResult bindingResult) {
		if (user.getPassword() != null
				&& !user.getPassword().equals(user.getConfirmPassword())) {
			bindingResult.rejectValue("password", "password.notequal",
					"Wrong password confirmation");
		}
		if (!bindingResult.hasErrors()) {
			try {
				userService.add(user);

				HttpSession session = request.getSession(true);
				session.setAttribute(Attributes.APPLICATION_USER, user);
			} catch (DaoException e) {
				bindingResult.rejectValue("email", "email.wrong",
						e.getMessage());
			}
		}
		if (bindingResult.hasErrors()) {
			ModelAndView view = new ModelAndView(Pages.REGISTER);
			view.addAllObjects(bindingResult.getModel());
			return view;
		} else {
			return mainPage.get(request);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginWithCridentials(
			HttpServletRequest request,
			@ModelAttribute(Attributes.CREDENTIALS) @Valid Credentials credentials,
			BindingResult bindingResult) {
		try {
			User user = userService.get(credentials.getEmail(),
					credentials.getPassword());

			HttpSession session = request.getSession(true);
			session.setAttribute(Attributes.APPLICATION_USER, user);

			return mainPage.get(request);
		} catch (DaoException e) {
			bindingResult.rejectValue("email", "email.wrong", e.getMessage());

			ModelAndView view = mainPage.get(request);
			view.addAllObjects(bindingResult.getModel());

			return view;
		}
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute(Attributes.APPLICATION_USER, null);

		return mainPage.get(request);
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getUserData(HttpServletRequest request,
			@PathVariable("id") int userId) throws DaoException {
		User user = userService.get(userId);
		List<Theme> userThemes = themeService.getUserThemes(user);
		Collections.sort(userThemes, themesPopularityComparator);

		ModelAndView view = mainPage.get(request);
		view.addObject(Attributes.USER, user);
		view.addObject(Attributes.USER_THEMES, userThemes);
		view.addObject(Attributes.THEMES_FOUND_LIKES,
				likeService.getThemesLikes(userThemes));
		view.setViewName("user/info");
		return view;
	}

	@RequestMapping(value = "/search/{text}")
	public ModelAndView getUserSearchResult(HttpServletRequest request,
			@PathVariable("text") String text) throws DaoException {
		List<User> users = userService.search(text);

		ModelAndView view = mainPage.get(request);
		view.addObject(Attributes.USERS_FOUND, users);
		view.setViewName("user/search/result");
		return view;
	}
}
