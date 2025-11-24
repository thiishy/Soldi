package br.edu.fatecpg.soldi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmail(EmailDetails email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(remetente);
            mailMessage.setTo(email.destinatario());
            mailMessage.setText(email.corpoMensagem());
            mailMessage.setSubject(email.assunto());

            javaMailSender.send(mailMessage);
            System.out.println("Email enviado!");
        }

        catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getLocalizedMessage());
        }
    }
}
