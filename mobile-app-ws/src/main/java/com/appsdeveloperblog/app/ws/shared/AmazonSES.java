package com.appsdeveloperblog.app.ws.shared;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

import org.hibernate.cache.spi.support.RegionNameQualifier;

public class AmazonSES {
  // Este endereço deve ser verificado com Amazon SES.
  final String FROM = "pedrinhozenere99@gmail.com";

  // Assunto do email
  final String SUBJECT = "Ultimo passo para completar o registro com o Banco PhotoApp";

  // Corpo do Email
  final String HTMLBODY = "<a href='https://github.com/PedroZenere'>"
      + "<a href='http://localhost:8080/verification-service/email-verification.html?token=$tokenValue'>";

  // Corpo de email para clientes sem html
  final String TEXTBODY = "https://github.com/PedroZenere";

  public void verifyEmail(UserDto userDto) {

    AmazonSimpleEmailService client = AmazonSimpleEmailServiceAsyncClientBuilder.standard()
        .withRegion(Regions.US_EAST_1).build();

    String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
    String textBodyWithToken = TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());

    SendEmailRequest request = new SendEmailRequest()
        .withDestination(new Destination().withToAddresses(userDto.getEmail()))
        .withMessage(new Message()
            .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                .withText(new Content().withCharset("UTF-8").withData(textBodyWithToken)))
            .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
        .withSource(FROM);

    client.sendEmail(request);

    System.out.println("Email sent!");

  }
}
