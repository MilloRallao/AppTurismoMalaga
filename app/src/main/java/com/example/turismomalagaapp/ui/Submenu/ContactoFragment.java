package com.example.turismomalagaapp.ui.Submenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.turismomalagaapp.R;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ContactoFragment extends Fragment {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;
    Button login;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contacto, container, false);
        context = getContext();

        login = view.findViewById(R.id.btn_submit);
        reciep = view.findViewById(R.id.et_to);
        sub = view.findViewById(R.id.et_sub);
        msg = view.findViewById(R.id.et_text);
        reciep.addTextChangedListener(loginTextWatcher);
        sub.addTextChangedListener(loginTextWatcher);
        msg.addTextChangedListener(loginTextWatcher);
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

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String correoelectronicoPuesto = reciep.getText().toString().trim();
            String asuntoPuesto = sub.getText().toString().trim();
            String descripcionPuesta = msg.getText().toString().trim();

            login.setEnabled(!correoelectronicoPuesto.isEmpty() && !asuntoPuesto.isEmpty() && !descripcionPuesta.isEmpty() && comprobarCorreo());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public boolean comprobarCorreo() {
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // Se obtiene el texto introducido en EditText del correo
        String correo = reciep.getText().toString();
        // Se comprueba si el correo coincide con la expresión regular
        Matcher mather = pattern.matcher(correo);

        // Condional que informa si el correo es válido o no
        if (mather.find()) {
            return true;
        }
        else {
            return false;
        }
    }



    class RetreiveFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(rec));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("proyectofctmalaga@gmail.com"));
                message.setSubject(subject);
                String texto1 = "Correo Electrónico: " +rec +" Contexto: " +textMessage;
                message.setContent(texto1, "text/html; charset=utf-8");
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

