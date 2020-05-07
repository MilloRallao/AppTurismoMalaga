package com.example.turismomalagaapp.ui.Submenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.se.omapi.Session;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Properties;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turismomalagaapp.R;


public class ContactoFragment extends Fragment {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacto, container, false);
        context = getContext();

        Button login = (Button) view.findViewById(R.id.btn_submit);
        reciep = (EditText) view.findViewById(R.id.et_to);
        sub = (EditText) view.findViewById(R.id.et_sub);
        msg = (EditText) view.findViewById(R.id.et_text);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rec = reciep.getText().toString();
                subject = sub.getText().toString();
                textMessage = msg.getText().toString();

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");

                session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("proyectofctmalaga@gmail.com", "FCTmalaga1234");
                    }
                });

                pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();
            }
        });

        return view;
    }




    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            msg.setText("");
            sub.setText("");
            Toast.makeText(context.getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }
}

