package com.example.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.domain.Sample;
import com.example.sample.service.SampleService;

@RestController
@RequestMapping("sample")
public class SampleController {

	@Autowired
	SampleService sampleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String hello() {
		return "Hello world!";
	}

	@RequestMapping(value = "select", method = RequestMethod.GET)
	public List<Sample> selectAll() {
		return sampleService.selectAll();
	}

	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public Sample insert() {
		return sampleService.insert();
	}

	@RequestMapping(value = "update", method = RequestMethod.GET)
	public List<Sample> update() {
		return sampleService.update();
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String deleteAll() {
		return sampleService.deleteAll();
	}

	@RequestMapping(value = "exception")
	public String exception() throws Exception {
		throw new Exception("Exception test.");
	}

}
