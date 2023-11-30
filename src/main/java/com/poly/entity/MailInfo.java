package com.poly.entity;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailInfo {
	private String from;
	@NotBlank
	private String to;
	private String[] cc;
	private String[] bcc;
	@NotBlank
	private String subject;
	@NotBlank
	private String body;
	private String[] attachments;

	public MailInfo(String to, String subject, String body) {
		this.from = "Elise Shop <thaoltpps25649@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
