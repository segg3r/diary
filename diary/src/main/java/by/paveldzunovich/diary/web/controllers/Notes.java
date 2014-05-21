package by.paveldzunovich.diary.web.controllers;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Like;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.LikeService;
import by.paveldzunovich.diary.services.ifaces.NoteService;
import by.paveldzunovich.diary.web.Attributes;
import by.paveldzunovich.diary.web.controllers.binders.PriorityBinder;
import by.paveldzunovich.diary.web.controllers.binders.ThemeBinder;
import by.paveldzunovich.diary.web.controllers.binders.UserBinder;

@Controller
@RequestMapping(value = "/note")
public class Notes {

	@Autowired
	private UserBinder userBinder;
	@Autowired
	private PriorityBinder priorityBinder;
	@Autowired
	private ThemeBinder themeBinder;
	@Autowired
	private MainPage mainPage;
	@Autowired
	private NoteService noteService;
	@Autowired
	private LikeService likeService;

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) {
		binder.registerCustomEditor(User.class, userBinder);
		binder.registerCustomEditor(Priority.class, priorityBinder);
		binder.registerCustomEditor(Theme.class, themeBinder);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addNote(HttpServletRequest request,
			@ModelAttribute("note") @Valid Note note,
			BindingResult bindingResult) {
		note.setPublished(new Date());
		if (!bindingResult.hasErrors()) {
			try {
				noteService.add(note);
			} catch (DaoException e) {
				bindingResult.rejectValue("id", "id.fail", "Cannot add note.");
			}
		}

		ModelAndView view = mainPage.get(request, note.getTheme());
		if (bindingResult.hasErrors()) {
			view.addAllObjects(bindingResult.getModel());
		}
		return view;
	}

	@RequestMapping(value = "/like/{id}")
	public ModelAndView likeNote(HttpServletRequest request,
			@PathVariable(value = "id") int noteId) throws DaoException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(Attributes.APPLICATION_USER);
		Note note = noteService.get(noteId);

		if (likeService.get(user, note) == null) {
			Like like = new Like(note, user);
			likeService.add(like);
		}

		return mainPage.get(request, note.getTheme());
	}

}
