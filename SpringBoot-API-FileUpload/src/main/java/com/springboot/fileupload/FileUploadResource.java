package com.springboot.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.fileupload.bo.Customer;
import com.springboot.fileupload.reader.JsonDataReader;
import com.springboot.fileupload.repo.CustomerRepository;

@Controller
public class FileUploadResource {

	@Value("${upload.path}")
	private String path;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private JsonDataReader jsonDataReader;
	
	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file) throws IOException {

		if (!file.isEmpty()) {

			String fileName = file.getOriginalFilename();
			InputStream is = file.getInputStream();
			String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
			if (extension.equals("json")) {
				Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
				Customer customer = jsonDataReader.generateData(path + fileName);
				customerRepository.save(customer);
				return "redirect:/success.html";
			} else {
				return "redirect:/failure.html";
			}
		} else {

			return "redirect:/failure.html";
		}
	}
	
	@RequestMapping(value = "/getdata/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerRepository.findOne(id);
        return customer;
    }
	
	@RequestMapping(value = "/getdata", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Iterable<Customer> getData() {
        return customerRepository.findAll();
    }
}
