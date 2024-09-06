import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class Trial1 extends Applet implements MouseListener, ActionListener {
    TextField username, password, tf1;
    Label showPass, logo, loginCheck, questionLabel, questionSubmittedLabel;
    TextArea answerLabel, askQuestionTextArea;
    Button login, backButton, nextButton, askQuestionButton, prevButton, submitButton;
    Image img;
    boolean usernameClear = true, passClear = true;
    String nameOfTheUser, question, answer;
    int mainMenuQuestionsLoop = 0;
    int mainMenuQuestionsIndex = 0;
    List<String> questionsList = new ArrayList<String>();
    Button[] qButtons = new Button[10];

    Font F1 = new Font("Times New Roman", Font.BOLD, 28);
    Color c = new Color(98, 162, 93);// buttons
    Color cd = new Color(79, 130, 74);
    Color c2 = new Color(161, 199, 158);// Answer tab
    Color c3 = new Color(208, 227, 206);
    Color c3d = new Color(162, 199, 158);
    Color lightrect = new Color(162, 199, 158);
    Color darkrect = new Color(80, 130, 74);

    int tab = 1;

    public void init() {

        setBackground(Color.WHITE);
        loginButton();
        userTextField();
        passTextField();
        indusLogo();
        showPassLabel();
        // qaBackButton();

        username.addMouseListener(this);
        password.addMouseListener(this);
        showPass.addMouseListener(this);
        login.addMouseListener(this);
        login.addActionListener(this);
        setLayout(null);
    }

    public void paint(Graphics g) {
        if (tab == 1) {
            g.drawImage(img, 415, 25, this);
        } else if (tab == 2) {

            resize(900, 900);
            g.setColor(lightrect);
            g.drawRect(0, 0, 5000, 100);
            g.fillRect(0, 0, 5000, 100);
            g.setColor(darkrect);
            g.drawRect(0, 100, 200, 5000);
            g.fillRect(0, 100, 200, 5000);

            g.setColor(Color.black);
            g.setFont(F1);
            g.drawString("Hello " + nameOfTheUser, 2, 50);

        }
    }

    public void actionPerformed(ActionEvent e) {
        if (tab == 1) {
            if (e.getSource() == login) {
                repaint();
                checkLoginDetails();
            }
        } else if (tab == 2) {
            if (e.getSource() == nextButton) {
                nextButtonClicked();
            }

            if (e.getSource() == prevButton) {
                prevButtonClicked();
            }

            for (int i = 0; i < 5; i++) {
                if (e.getSource() == qButtons[i]) 
                {
                    questionAnswersMenu(qButtons[i]);
                }
            }

            if (e.getSource() == backButton) {
                backButtonClicked();
            }

            if (e.getSource() == askQuestionButton) {
                askQuestionMenu();
            }

            if (e.getSource() == submitButton) {
                submitButtonClicked();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == questionSubmittedLabel) {
            backButtonClicked();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == showPass && !password.getText().equals("Password")) {
            password.setEchoChar('*');
            showPass.setForeground(Color.BLACK);
        }
    }

    public void mouseExited(MouseEvent e) {

        if (e.getSource() == login) {
            login.setBackground(new Color(120, 108, 92));
        } else if (e.getSource() == nextButton) {
            nextButton.setBackground(new Color(208, 227, 206));
        } else if (e.getSource() == prevButton) {
            prevButton.setBackground(new Color(208, 227, 206));
        } else if (e.getSource() == askQuestionButton) {
            askQuestionButton.setBackground(new Color(208, 227, 206));
        } else if (e.getSource() == submitButton) {
            submitButton.setBackground(new Color(208, 227, 206));
        } else if (e.getSource() == backButton) {
            backButton.setBackground(new Color(208, 227, 206));
        }
        for (int i = 0; i < 5; i++) {
            if (e.getSource() == qButtons[i]) {
                qButtons[i].setBackground(new Color(98, 162, 93));
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getSource() == showPass && !password.getText().equals("Password")) {
            password.setEchoChar((char) 0);
            showPass.setForeground(new Color(120, 108, 92));
        }
    }

    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == login) {
            login.setBackground(new Color(60, 42, 30));
        } else if (e.getSource() == nextButton) {
            nextButton.setBackground(new Color(162, 199, 158));
        } else if (e.getSource() == prevButton) {
            prevButton.setBackground(new Color(162, 199, 158));
        } else if (e.getSource() == askQuestionButton) {
            askQuestionButton.setBackground(new Color(162, 199, 158));
        } else if (e.getSource() == submitButton) {
            submitButton.setBackground(new Color(162, 199, 158));
        } else if (e.getSource() == backButton) {
            backButton.setBackground(new Color(162, 199, 158));
        }
        for (int i = 0; i < 5; i++) {
            if (e.getSource() == qButtons[i]) {
                qButtons[i].setBackground(new Color(79, 130, 74));
            }
        }
    }

    public void loginButton() {
        login = new Button("Login");
        login.setBounds(190, 220, 100, 30);
        login.setFont(new Font("Serif", Font.BOLD, 12));
        login.setForeground(Color.WHITE);
        login.setBackground(new Color(120, 108, 92));
        add(login);

        login.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                login.setBackground(new Color(101, 53, 15));
            }
        });

        login.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                login.setBackground(new Color(120, 108, 92));
            }
        });

    }

    public void userTextField() {
        username = new TextField("Enrollment No.");
        username.setBounds(150, 100, 200, 30);
        add(username);

        username.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameClear == true) {
                    usernameClear = false;
                    username.setText("");
                }
            }
        });
    }

    public void passTextField() {
        password = new TextField("Password");
        password.setBounds(150, 150, 200, 30);
        add(password);

        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (passClear == true) {
                    passClear = false;
                    password.setText("");
                    password.setEchoChar('*');
                }
            }
        });
    }

    public void indusLogo() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("Indus_Logo.png"));
            img = myPicture.getScaledInstance(63, 50, Image.SCALE_SMOOTH);
        } catch (IOException e) {
        }
    }

    public void showPassLabel() {
        showPass = new Label("X");
        showPass.setBounds(360, 150, 30, 30);
        showPass.setFont(new Font("SauceCode Pro Nerd Font", Font.PLAIN, 25));
        showPass.setForeground(Color.BLACK);
        add(showPass);
    }

    void checkLoginDetails() {
        boolean checkUsername = false, checkPassword = false;

        try {
            File readData = new File("LoginDetails.csv");
            Scanner scn = new Scanner(readData);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                fields[1] = fields[1].replace("\"", "");

                if (fields[0].equals(username.getText())) {
                    checkUsername = true;
                    if (fields[1].equals(password.getText())) {
                        checkPassword = true;
                        nameOfTheUser = fields[2];
                        mainMenuQuestions();
                    }
                }
            }
        } catch (FileNotFoundException e) {
        }

        if (checkUsername == false) {
            loginCheck = new Label("Username not found! Try Again!");
            loginCheck.setBounds(150, 180, 200, 30);
            loginCheck.setForeground(Color.RED);
            add(loginCheck);
        }

        else if (checkUsername == true && checkPassword == false) {
            loginCheck = new Label("Incorrect Password! Try Again!");
            loginCheck.setBounds(150, 180, 200, 30);
            loginCheck.setForeground(Color.RED);
            add(loginCheck);
        }

        else if (checkUsername == true && checkPassword == true) {
            remove(loginCheck);
        }
    }

    void mainMenuQuestions() {
        removeAll();
        resize(900, 900);
        tab = 2;
        mainMenuPrevButton();
        mainMenuNextButton();
        mainMenuAskQuestion();
        readQuestionsFile();
        qaActionListener();

        int yAxis = 170;
        for (int i = mainMenuQuestionsLoop; i < 5; i++) {
            qButtons[i].setLabel(questionsList.get(i).replace("|", " "));
            qButtons[i].setBackground(c);
            qButtons[i].setBounds(225, yAxis, 650, 100);
            add(qButtons[i]);
            yAxis = yAxis + 130;
        }

        askQuestionButton.addActionListener(this);
    }

    void readQuestionsFile() {
        for (int i = 0; i < 5; i++) {
            qButtons[i] = new Button();
        }
        try {
            File readData = new File("Questions.csv");
            Scanner scn = new Scanner(readData);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                fields[0] = fields[0].replace("\"", "");
                questionsList.add(fields[0]);
            }
        } catch (FileNotFoundException e) {
        }
    }

    void nextButtonClicked() {
        add(prevButton);
        if (mainMenuQuestionsLoop + 5 <= questionsList.size())
            mainMenuQuestionsLoop += 5;

        mainMenuQuestionsIndex = mainMenuQuestionsLoop;
        for (int i = 0; i < 5; i++, mainMenuQuestionsIndex++) {
            if (mainMenuQuestionsIndex == questionsList.size() && mainMenuQuestionsIndex % 5 != 0) {
                int clear = mainMenuQuestionsIndex % 5;
                for (int j = clear; j < 5; j++) {
                    remove(qButtons[j]);
                }
                break;
            }
            qButtons[i].setLabel(questionsList.get(mainMenuQuestionsIndex).replace("|", " "));
        }

        if (mainMenuQuestionsIndex == questionsList.size())
            remove(nextButton);

    }

    void mainMenuNextButton() {
        nextButton = new Button("Next");
        nextButton.setBackground(c3);
        nextButton.setBounds(825, 820, 50, 50);
        add(nextButton);
        nextButton.addActionListener(this);
        nextButton.addMouseListener(this);
    }

    void mainMenuAskQuestion() {
        askQuestionButton = new Button("Ask Question");
        askQuestionButton.setBounds(780, 25, 100, 50);
        askQuestionButton.setBackground(c3);
        add(askQuestionButton);
        askQuestionButton.addMouseListener(this);
    }

    void mainMenuPrevButton() {
        prevButton = new Button("Prev");
        prevButton.setBounds(225, 820, 50, 50);
        prevButton.setBackground(c3);
        prevButton.addActionListener(this);
        prevButton.addMouseListener(this);
    }

    void prevButtonClicked() {
        add(nextButton);
        mainMenuQuestionsLoop -= 5;
        if (mainMenuQuestionsLoop == 0)
            remove(prevButton);

        if (mainMenuQuestionsIndex == questionsList.size()) {
            int clear = mainMenuQuestionsIndex % 5;
            for (int j = clear; j < 5; j++)
                add(qButtons[j]);
        }

        mainMenuQuestionsIndex = mainMenuQuestionsLoop;

        for (int i = 0; i < 5; i++, mainMenuQuestionsIndex++) {
            qButtons[i].setLabel(questionsList.get(mainMenuQuestionsIndex).replace("|", " "));
        }
    }

    void questionAnswersMenu(Button clickedButton) {
        removeAll();

        try {
            File readData = new File("Questions.csv");
            Scanner scn = new Scanner(readData);

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                fields[0] = fields[0].replace("\"", "");
                fields[0] = fields[0].replace("|", " ");
                fields[1] = fields[1].replace("\"", "");
                fields[1] = fields[1].replace("|", "\n");
                // System.out.println(fields[1]);
                if (fields[0].equals(clickedButton.getLabel())) {
                    question = clickedButton.getLabel();
                    answer = fields[1];
                    // questionLabel.setText(clickedButton.getLabel());
                    break;
                }
            }
        } catch (FileNotFoundException e) {
        }

        add(askQuestionButton);
        qaMenuQuestion();
        add(questionLabel);
        qaMenuAnswer();
        add(answerLabel);
        qaBackButton();
    }

    void qaBackButton() {
        backButton = new Button("Back");
        backButton.setBounds(220, 110, 50, 50);
        backButton.setBackground(c3);
        add(backButton);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

    }

    void qaMenuQuestion() {
        questionLabel = new Label(question);
        questionLabel.setAlignment(Label.CENTER);
        questionLabel.setBounds(225, 170, 650, 96);
        questionLabel.setBackground(c);
    }

    void qaActionListener() {
        for (int i = 0; i < 5; i++) {
            qButtons[i].addActionListener(this);
            qButtons[i].addMouseListener(this);

        }
    }

    void backButtonClicked() {
        removeAll();
        if (mainMenuQuestionsLoop == 0) {
            add(nextButton);
            for (int i = 0; i < 5; i++)
                add(qButtons[i]);
        }

        else if (mainMenuQuestionsIndex == questionsList.size()) {
            int clear = mainMenuQuestionsIndex % 5;
            for (int j = clear - 1; j >= 0; j--)
                add(qButtons[j]);
            add(prevButton);
        } else {
            for (int i = 0; i < 5; i++)
                add(qButtons[i]);
            add(prevButton);
            add(nextButton);
        }
        add(askQuestionButton);
    }

    void qaMenuAnswer() {
        answerLabel = new TextArea(answer, 0, 0, TextArea.SCROLLBARS_NONE);
        answerLabel.setEditable(false);
        // answerLabel.setFocusable(false);
        // answerLabel.setAlignment(Label.CENTER);
        answerLabel.setBounds(225, 290, 650, 585);
        answerLabel.setBackground(c2);
    }

    void askQuestion() {
        askQuestionTextArea = new TextArea("Question", 0, 0, TextArea.SCROLLBARS_NONE);
        askQuestionTextArea.setBounds(225, 170, 650, 700);
        askQuestionTextArea.setBackground(c);
        askQuestionTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                askQuestionTextArea.setText("");
            }
        });
    }

    void askQuestionMenu() {
        qaBackButton();
        submitQuestion();
        askQuestion();
        removeAll();
        add(askQuestionTextArea);
        add(backButton);
        add(submitButton);
    }

    void submitQuestion() {
        submitButton = new Button("Submit");
        submitButton.setBounds(800, 110, 80, 50);
        submitButton.setBackground(c3);
        submitButton.addActionListener(this);
        submitButton.addMouseListener(this);
    }

    void submitButtonClicked() {
        questionSubmitted();
        remove(askQuestionTextArea);
        add(questionSubmittedLabel);
        add(askQuestionTextArea);
        String question = askQuestionTextArea.getText();
        if (question.equals("Question") || question.equals("")) {
            questionSubmittedLabel.setText("Please enter valid question! Try again!");
        } else {
            question = question.replaceAll("\\n", "|");
            System.out.println(question);

            try {
                FileWriter addQuestion = new FileWriter("Questions.csv", true);
                addQuestion.append("\"" + question + "\"").append(",")
                        .append("\"No answer available\"").append("\n");
                addQuestion.flush();
                addQuestion.close();
            } catch (IOException e) {
                System.out.println("File Not found!");
            }
        }

        questionSubmittedLabel.addMouseListener(this);
        System.out.println(question);
    }

    void questionSubmitted() {
        questionSubmittedLabel = new Label("Your Question is Submitted Successfully!");
        questionSubmittedLabel.setBounds(400, 450, 300, 50);
        questionSubmittedLabel.setBackground(c3);
        questionSubmittedLabel.setAlignment(Label.CENTER);
    }

}

/*
 * <applet code="Trial1.class" width="500" height="300">
 * </applet>
 */
