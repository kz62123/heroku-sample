package com.example.sample.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.domain.Sample;
import com.example.sample.service.SampleService;
import com.paypal.ipn.IPNMessage;

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

	@PostMapping("paypal")
	public String paypal(HttpServletRequest request) throws IOException {
		System.out.println("paypal test");

		String result = request.getReader().lines().collect(Collectors.joining("\r\n"));
		System.out.println(result);
		request.getParameterMap().values().forEach(value -> {
			Arrays.asList(value).forEach(v -> {
				System.out.println(v);
			});
		});

		Map<String, String> configMap = new HashMap<>();

		// Endpoints are varied depending on whether sandbox OR live is chosen for mode
		configMap.put("mode", "sandbox");

		// Connection Information. These values are defaulted in SDK. If you want to override default values, uncomment it and add your value.
		// configMap.put("http.ConnectionTimeOut", "5000");
		// configMap.put("http.Retry", "2");
		// configMap.put("http.ReadTimeOut", "30000");
		// configMap.put("http.MaxConnection", "100");

		IPNMessage ipnlistener = new IPNMessage(request, configMap);

		// PyaPal 認証
		boolean isIpnVerified = ipnlistener.validate();
		System.out.println(isIpnVerified);
		if (isIpnVerified) {
			String transactionType = ipnlistener.getTransactionType();
			Map<String, String> map = ipnlistener.getIpnMap();

			System.out.println(transactionType);
			System.out.println(map);
		}

		return "paypal test";
	}

}
