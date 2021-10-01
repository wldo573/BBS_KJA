package com.human.app;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private SqlSession sqlSession;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String selectBBS(Model model) {
		//MyBatis호출해서 게시판목록 가져오기
		iBBS bbs=sqlSession.getMapper(iBBS.class);
		ArrayList<BBSrec> alBBS=bbs.getList();
		model.addAttribute("list_BBS",alBBS);
		return "list";
	}
	
	@RequestMapping(value="/view/{bbs_id}",method=RequestMethod.GET)
	public String selectOneBBS(@PathVariable("bbs_id") int bbs_id,Model model) {
		iBBS bbs=sqlSession.getMapper(iBBS.class);
		BBSrec post=bbs.getPost(bbs_id);
		System.out.println("bbs_id["+bbs_id+"]");
		model.addAttribute("post",post);
		return "view";
	}
	
	
	@RequestMapping(value="/new",method=RequestMethod.GET)
	public String brandNew() {
		return "new";//@ResponseBody없이 JSP페이지로 이동
	}
	
//	@RequestMapping(value="/update_view",method=RequestMethod.GET)
//	public String updateView() {
//		return "update";
//	}
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String insertBBS(HttpServletRequest hsr) {
		String pTitle = hsr.getParameter("title");
		String pContent = hsr.getParameter("content");
		String pWriter = hsr.getParameter("writer");
		String pPasscode = hsr.getParameter("passcode");
		System.out.println("title ["+pTitle+"] content ["+pContent+"] writer ["+pWriter+"] passcode ["+pPasscode+"]");
		//insert into DB table
		iBBS bbs =sqlSession.getMapper(iBBS.class);
		System.out.println("ok");
		bbs.writebbs(pTitle, pContent, pWriter, pPasscode);
		return "redirect:/list";//@ResponseBody없이 해당하는Request Mapping으로 이동
	}
	
	@RequestMapping(value="/update/{bbs_id}",method=RequestMethod.GET)
	public String updatebbs(@PathVariable("bbs_id") int bbs_id,Model model) {
		iBBS bbs=sqlSession.getMapper(iBBS.class);
		BBSrec rec=bbs.getPost(bbs_id);
		model.addAttribute("post",rec);
		return "update";
	}
	
	@RequestMapping(value="/update_view",method=RequestMethod.POST)
	public String updatebbs(HttpServletRequest hsr) {
		System.out.print(hsr.getParameter("bbs_id"));
		iBBS bbs=sqlSession.getMapper(iBBS.class);
		int bbs_id=Integer.parseInt(hsr.getParameter("bbs_id"));
		String title=hsr.getParameter("title");
		String content=hsr.getParameter("content");
		bbs.updatebbs(bbs_id, title, content);
		return "redirect:/view";
	}

	
	@RequestMapping(value="/delete/{bbs_id}",method=RequestMethod.GET)
	public String deletebbs(@PathVariable("bbs_id") int bbs_id) {
		System.out.println("bbs_id2["+bbs_id+"]");
		iBBS bbs=sqlSession.getMapper(iBBS.class);
		bbs.deletebbs(bbs_id);
		return "redirect:/list";
	}
	
}