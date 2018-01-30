package com.optus.microservice.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SearchController {

	private static int KEYVALUEINDEX = 1;

	@RequestMapping("/counter-api")
	public ModelAndView welcomeMessage() {
		
		String welcomeMessage = "Hello... Welcome to Counter Micro Service!";
		return new ModelAndView("search", "message", welcomeMessage);
		
	}

	@RequestMapping(value = "/counter-api/searchAll", method = RequestMethod.GET,headers="Accept=application/json")
	public Map<String, Integer> searchAll(Model counts) {

		RestTemplate remoteServiceInvoker = new RestTemplate();
		Map<String, Integer> listOfWords = remoteServiceInvoker.getForObject("http://localhost:9002/loadFile", Map.class);
		return listOfWords;
		
	}
	
	/**
	 * Task 1: Search the following texts, which will return the counts respectively.
	 * @param queryString
	 * @return
	 */
	@RequestMapping(value = "/counter-api/search", method = RequestMethod.POST,headers="Accept=application/json")
	public Map<String, Integer> search(@RequestBody String queryString) {

		RestTemplate remoteServiceInvoker = new RestTemplate();

		// MOVE TO LOAD-DATA-SERVICE ///////////////
		List<String> keyWordList = new ArrayList<String>(); 
		List<String> KeyValueQuery = new ArrayList<String>(Arrays.asList(queryString.split(":")));
		if (!KeyValueQuery.isEmpty()) {
			keyWordList = new ArrayList(Arrays.asList(KeyValueQuery.get(KEYVALUEINDEX).split("\\W+")));
		}
		/////////////////////////////////////////////
		
		Map<String, Integer> counterMap = remoteServiceInvoker.getForObject("http://localhost:9002/wordCounter/"+ keyWordList, Map.class);

		return counterMap;
		
	}

	/**
	 * Task 2: Provide the top 20 (as Path Variable) Texts, which has the
	 * highest counts in the Sample Paragraphs provided.
	 * @param count
	 * @return
	 */
	@RequestMapping(value = "/counter-api/top/{count}", method = RequestMethod.GET,headers="Accept=application/json")
	public Map<String, Integer> wordsWithTopCount(@PathVariable("count") String count) {
		
		RestTemplate remoteServiceInvoker = new RestTemplate();
		Map<String, Integer> counterMap = remoteServiceInvoker.getForObject("http://localhost:9002/topWordsCounter/"+ count, Map.class);

		return counterMap;		
	}

}
