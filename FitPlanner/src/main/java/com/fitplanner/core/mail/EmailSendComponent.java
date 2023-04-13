package com.fitplanner.core.mail;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * package     : com.fitplanner.core.mail
 * file        : EmailSendService
 * author      : choeuiseung
 * date        : 2022/10/28
 * description
 * =======================================================
 * DATE                   AUTHOR                    NOTE
 * =======================================================
 * 2022/10/28                choeuiseung                 최초 생성
 * =======================================================
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSendComponent {

    private final AmazonSimpleEmailService simpleEmailService;
    private final Environment env;

    private void setTemplate(final String tplName, final Map<String, Object> tplData) {

        StringBuilder templateHtml = new StringBuilder();

        Set<String> dataKeySet = tplData.keySet();

        templateHtml.append("<html>");

        templateHtml.append("   <head>");
        templateHtml.append("   </head>");

        templateHtml.append("   <body>");

        for(String dataKey : dataKeySet) {
            templateHtml.append("       <p>" + tplData.get(dataKey) + "</p>");
        }

        templateHtml.append("   </body>");

        templateHtml.append("</html>");

        Template template = new Template();

        template.setTemplateName(tplName);
        template.setSubjectPart("안녕하세요. FitPlanner 입니다.");
        template.setHtmlPart(templateHtml.toString());
        template.setTextPart(null);


        UpdateTemplateRequest request = new UpdateTemplateRequest().withTemplate(template);
        UpdateTemplateResult result = simpleEmailService.updateTemplate(request);

    }

    public SendTemplatedEmailResult send(final String receiver, final String tplName, final Map<String, Object> tplData) {

        final String tplDataJsonString = new Gson().toJson(tplData);

        Destination destination = new Destination().withToAddresses(receiver);

        setTemplate(tplName, tplData);

        SendTemplatedEmailRequest emailRequest = new SendTemplatedEmailRequest()
                .withDestination(destination)
                .withSource(env.getProperty("cloud.aws.credentials.from"))
                .withTemplate(tplName)
                .withTemplateData(tplDataJsonString);

        SendTemplatedEmailResult emailResult = simpleEmailService.sendTemplatedEmail(emailRequest);

        return emailResult;

    }

}
