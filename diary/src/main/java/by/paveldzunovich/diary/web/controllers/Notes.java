package by.paveldzunovich.diary.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.NoteService;
import by.paveldzunovich.diary.web.controllers.binders.PriorityBinder;
import by.paveldzunovich.diary.web.controllers.binders.UserBinder;

@Controller
@RequestMapping(value = "/note")
public class Notes {

	@Autowired
	private NoteService noteService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(User.class, new UserBinder());
		binder.registerCustomEditor(Priority.class, new PriorityBinder());
	}

	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public ModelAndView addNote(HttpServletRequest request,
			@ModelAttribute("note") @Valid Note note,
			BindingResult bindingResult) {
		ModelAndView view = new MainPage().get(request);

		if (!bindingResult.hasErrors()) {
			try {
				noteService.add(note);
			} catch (DaoException e) {
				bindingResult.rejectValue("id", "id.fail", "Cannot add note.");
			}
		}
		view.addAllObjects(bindingResult.getModel());
		return view;
	}

}
