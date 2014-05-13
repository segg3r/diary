package by.paveldzunovich.diary.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.UserService;
import by.paveldzunovich.diary.web.Attributes;
import by.paveldzunovich.diary.web.Pages;
import by.paveldzunovich.diary.web.controllers.viewbeans.Credentials;

@Controller
@RequestMapping(value = "/user")
public class Users {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register")
	public String toRegister(ModelMap model) {
		model.addAttribute(Attributes.USER, new User());
		return Pages.REGISTER;
	}

	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request,
			@ModelAttribute(Attributes.USER) @Valid User user,
			BindingResult bindingResult) {
		try {
			userService.add(user);

			HttpSession session = request.getSession(true);
			session.setAttribute(Attributes.APPLICATION_USER, user);

			return new MainPage().get(request);
		} catch (DaoException e) {
			bindingResult.rejectValue("email", "email.wrong", e.getMessage());

			ModelAndView view = new MainPage().get(request);
			view.addAllObjects(bindingResult.getModel());

			return view;
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

			return new MainPage().get(request);
		} catch (DaoException e) {
			bindingResult.rejectValue("email", "email.wrong", e.getMessage());

			ModelAndView view = new MainPage().get(request);
			view.addAllObjects(bindingResult.getModel());

			return view;
		}
	}

}