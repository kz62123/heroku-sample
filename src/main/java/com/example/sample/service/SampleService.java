package com.example.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sample.domain.Sample;
import com.example.sample.domain.SampleRepository;

@Service
@Transactional
public class SampleService {

	@Autowired
	SampleRepository sampleRepository;

	public List<Sample> selectAll() {
		return sampleRepository.findAll();
	}

	public Sample insert() {
		Sample sample = new Sample();
		sample.setName("hoge");
		return sampleRepository.save(sample);
	}

	public List<Sample> update() {
		List<Sample> samples = sampleRepository.findAll();
		samples.forEach(sample -> sample.setName("fuga"));
		return sampleRepository.saveAll(samples);
	}

	public String deleteAll() {
		sampleRepository.deleteAll();
		return "All deleted.";
	}

}
