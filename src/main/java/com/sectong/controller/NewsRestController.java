package com.sectong.controller;

import com.sectong.domain.News;
import com.sectong.domain.NewsCreateForm;
import com.sectong.message.Message;
import com.sectong.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1", name = "新闻API")
public class NewsRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewsRestController.class);
	private Message message = new Message();

	private NewsService newsService;

	@Autowired
	public NewsRestController(NewsService newsService) {
		this.newsService = newsService;
	}

	@ResponseBody
	@RequestMapping(value = "i/news/create", method = RequestMethod.POST)
	public ResponseEntity<Message> createNews(@Valid @RequestBody NewsCreateForm form, BindingResult bindingResult) {
		try {
			News news = newsService.create(form);
			message.setMsg(1, "新闻创建成功", news);
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			LOGGER.warn("create news error", e);
			message.setMsg(0, "创建新闻失败");
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "news/getNewsList", method = RequestMethod.GET)
	public ResponseEntity<Message> getNewsList(@RequestParam(defaultValue = "0") Long startid, Pageable p) {
		Page<News> news = newsService.getNewsList(startid, p);
		message.setMsg(1, "获取新闻列表成功", news);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

}
